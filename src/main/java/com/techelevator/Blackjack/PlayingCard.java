package com.techelevator.Blackjack;

public class PlayingCard {
	// Define characteristics
	// Data members
	private String suit;
	private char rank;
	private int value;
	
	public PlayingCard(String suit, char rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public PlayingCard(String suit, char rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public char getRank() {
		return rank;
	}

	public void setRank(char rank) {
		this.rank = rank;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		return "[" + suit + ", " + rank + ", " + value + "]";
	}

}