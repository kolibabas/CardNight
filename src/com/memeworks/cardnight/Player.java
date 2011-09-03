package com.memeworks.cardnight;

public class Player {
	
	/** Name of this player */
	public final String Name;
	
	/** This player's hand */
	public CardContainer Hand;
	
	/** This player's personal space on the table */
	public CardContainer Personal_Space;

	//TODO Bluetooth address/identifier;
	
	/**
	 * Creates a new player
	 * @param name The player's name
	 */
	public Player(String name) {
		Name = name;
		
		Hand = new CardContainer();
		Personal_Space = new CardContainer();
	}

}
