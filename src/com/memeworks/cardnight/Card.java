package com.memeworks.cardnight;

/**
 * Defines a single playing card.
 * @author kolibabas
 *
 */
public class Card {
	
	/** Value from 0 (Joker) to 13 (King)*/ 
	public int Value;
	
	/** Enumerated suit value */
	public int Suit;
	
	/** Which deck this card belongs to */
	public int Deck;
	
	/** Current face up / down state of this card */
	public boolean Face_Up;
	
	/** Globally Unique Identifer for this card <deck><Value>of<suit */
	public String GUID;
	
	/**
	 * Creates a new Card. Deck+Value+Suit must be unique across all cards.
	 * @param value The face value of the card from 0 (Joker) to 13 (King).
	 * @param suit The suit of the card, SPADES, CLUBS, DIAMONDS, HEARTS
	 * @param deck Which deck this card belongs to
	 */
	public Card(boolean face_up, int value, int suit, int deck)
	{
		Face_Up = face_up;
		Value = value;
		Suit = suit;
		Deck = deck;
		
		GUID = "deck" + Deck + Value + "of" + Suit;
	}
	
	/**
	 * Turns this card over.
	 */
	public void Turn_Over()
	{
		Face_Up = !Face_Up;
	}
}
