package com.techelevator.Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Deck {
	private List<PlayingCard> listOfCards = new ArrayList<>();
	
	public Deck(Map<Character, Integer> charToValue) {
		for (String suit: new String[] {"Spades", "Hearts", "Diamonds", "Clubs"}) {
			for (char rank : new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'}) {
				// create new object
				int cardValue = charToValue.get(rank);
				PlayingCard currentCard = new PlayingCard(suit, rank, cardValue);
				// add to our list
				listOfCards.add(currentCard);
			}
		}
	}
	
	public Deck() {
		for (String suit: new String[] {"Spades", "Hearts", "Diamonds", "Clubs"}) {
			for (char rank : new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'}) {
				// create new object
				PlayingCard currentCard = new PlayingCard(suit, rank);
				// add to our list
				listOfCards.add(currentCard);
			}
		}
	}

	// randomly shuffles a list!
	public void shuffle() {
		Collections.shuffle(listOfCards);
	}
	
	// deals a single PlayingCard
	public PlayingCard deal() {
		if (listOfCards.size() != 0) {
			return listOfCards.remove(0);
		}
		return null;
	}

	public String toString() {
		return "[" + listOfCards + "]";
	}
	
}