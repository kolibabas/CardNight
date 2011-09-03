package com.memeworks.cardnight;

/**
 * Class to manage all game states and actions.
 * @author kolibabas
 *
 */
public class GameManager {

	/** Count of players for this game. */
	public final int Player_Count;
	
	/** Current player */
	public int Current_Player;
	
	/** Players for this game. */
	public final Player[] Players;
	
	/** Decks for this game. */
	public final CardContainer[] Decks;
	
	/**
	 * Constructs a new game instance
	 * @param player_count Count of players for this game.
	 * @param deck_count Count of decks for this game.
	 * @param include_jokers Whether to include jokers in this game.
	 */
	public GameManager(int player_count, int deck_count, boolean include_jokers)
	{
		Current_Player = 0;
		Player_Count = player_count;
		Players = new Player[Player_Count];
		Decks = new CardContainer[deck_count];
		
		GeneratePlayers();
		GenerateCards(include_jokers);
	}

	/**
	 *  Creates and initializes all players for the current game.
	 */
	private void GeneratePlayers() {
		
		for (int i = 0; i < Player_Count; i++)
		{
			if (CardNight.Current_Mode == CardNight.MODE_HOTSEAT)
			{
				Players[i] = new Player("Player" + (i + 1));
			}
			else
			{
				Players[i] = new Player(BluetoothManager.Get_Player_Name(i));
			}
		}
	}

	/**
	 * Generate all cards for this game. Cards always start in the decks.
	 * @param include_jokers Whether to include jokers in the generation
	 */
	private void GenerateCards(boolean include_jokers) {
		
		//For each deck
		for (int deck = 0; deck < Decks.length; deck++)
		{
			Decks[deck] = new CardContainer();
			
			//For each suit
			for (int suit = 0; suit < CardNight.SUIT_COUNT; suit++)
			{
				//For each card
				for(int card_value = (include_jokers) ? 0 : 1; card_value < 14; card_value++)
				{
					Decks[deck].add(new Card(false, card_value, suit, deck));
				}
			}
		}
	}
	
	/**
	 * Moves a card from one container to another
	 * @param card The card to move (uniquely identified)
	 * @param source The source container
	 * @param destination The destination container
	 */
	public void Move_Card(Card card, CardContainer source, CardContainer destination)
	{
		//Only move if source actually has the card
		if (source.contains(card))
		{
			source.remove(card);
			destination.add(card);
		}
	}
}
