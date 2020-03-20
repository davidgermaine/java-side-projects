package com.techelevator.Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		/*
		 * Initiate deck to values of Blackjack
		 */
		Map<Character, Integer> blackjackValues = new HashMap<>();
		blackjackValues.put('2', 2);
		blackjackValues.put('3', 3);
		blackjackValues.put('4', 4);
		blackjackValues.put('5', 5);
		blackjackValues.put('6', 6);
		blackjackValues.put('7', 7);
		blackjackValues.put('8', 8);
		blackjackValues.put('9', 9);
		blackjackValues.put('T', 10);
		blackjackValues.put('J', 10);
		blackjackValues.put('Q', 10);
		blackjackValues.put('K', 10);
		blackjackValues.put('A', 11);
		Deck blackjackDeck = new Deck(blackjackValues);
		blackjackDeck.shuffle();
		
		boolean playAgain = true;
		while (playAgain) {
			
			boolean playerBust = false;
			boolean dealerBust = false;
			/*
			 * Setup the player's hand
			 */
			List<PlayingCard> playerHand = new ArrayList<>(); // create list of cards
			playerHand.add(blackjackDeck.deal()); // add card
			playerHand.add(blackjackDeck.deal()); // add card
			System.out.println(playerHand); // print out list of cards
			int playerValue = 0; // declare integer score of hand
			playerValue = handScore(playerHand); // calculate score of hand
			System.out.println("Your hand is worth " + playerValue + " points.");
			
			/*
			 * Setup the dealer's hand
			 */
			List<PlayingCard> dealerHand = new ArrayList<>(); // create list of cards
			dealerHand.add(blackjackDeck.deal()); // add card
			System.out.println(dealerHand); // print out list of cards
			int dealerValue = 0; // declare integer score of hand
			dealerValue = handScore(dealerHand); // calculate score of hand
			System.out.println("The dealer's hand is worth " + dealerValue + " points.");
			
			if (playerValue == 21) {
				System.out.println("BLACKJACK!");
			} else {
				System.out.println("Would you like to (H)it or (S)tay?: ");
				String hitOrStay = input.nextLine();
				
				boolean userStay = false;
				while (!userStay) {
					
					if (hitOrStay.equalsIgnoreCase("S")) {
						userStay = true;
						boolean dealerDone = false;
						int count = 0;
						while (!dealerDone) {
							count++;
							dealerHand.add(blackjackDeck.deal());
							dealerValue = handScore(dealerHand);
							System.out.println(dealerHand);
							System.out.println("The dealer's hand is worth " + dealerValue + " points.");
							if (dealerValue == 21 && count == 1) {
								System.out.println("Dealer BLACKJACK!");
								dealerDone = true;
							} else if (dealerValue > 21) {
								setAcesToOne(dealerHand);
								dealerValue = handScore(dealerHand);
								if (dealerValue > 21) {
									System.out.println("Dealer BUSTED!");
									dealerBust = true;
									dealerDone = true;
									break;
								} else {
									System.out.println(dealerHand);
									System.out.println("The dealer's hand is worth " + dealerValue + " points.");
									if (dealerValue >= playerValue) {
										dealerDone = true;
									}
								}
							} else if (dealerValue >= playerValue) {
								dealerDone = true;
							}
						}
					} else if (hitOrStay.equalsIgnoreCase("H")) {
						playerHand.add(blackjackDeck.deal());
						playerValue = handScore(playerHand);
						System.out.println(playerHand);
						System.out.println("Your hand is worth " + playerValue + " points.");
						if (playerValue > 21) {
							setAcesToOne(playerHand);
							playerValue = handScore(playerHand);
							if (playerValue > 21) {
								System.out.println("BUSTED!");
								playerBust = true;
								break;
							} else {
								System.out.println(playerHand);
								System.out.println("Your hand is worth " + playerValue + " points.");
								System.out.println("Would you like to (H)it or (S)tay?: ");
								hitOrStay = input.nextLine();
							}
						} else {
							System.out.println("Would you like to (H)it or (S)tay?: ");
							hitOrStay = input.nextLine();
						}
					} else {
						System.out.println("Invalid input, please (H)it or (S)tay: ");
						hitOrStay = input.nextLine();
					}
				}
			}
			
			if (playerValue > dealerValue && playerBust == false) {
				System.out.println("Your " + playerValue + " points beats the dealer's " + dealerValue + " points.");
				System.out.println("You win!");
			} else if (playerValue < dealerValue && dealerBust == false){
				System.out.println("Your " + playerValue + " points loses to the dealer's " + dealerValue + " points.");
				System.out.println("You lose!");
			} else if (playerValue == dealerValue && playerBust == false) {
				System.out.println("Your " + playerValue + " points matches the dealer's " + dealerValue + " points.");
				System.out.println("The game is a draw");
			} else if (playerBust == true) {
				System.out.println("You lose!");
			} else if (dealerBust == true) {
				System.out.println("You win!");
			}
			
			boolean validReply = false;
			while (!validReply) {
				System.out.println("Play another round? (Y/N): ");
				String userInput = input.nextLine();
				
				if (userInput.equalsIgnoreCase("N")) {
					validReply = true;
					playAgain = false;
				} else if (userInput.equalsIgnoreCase("Y")) {
					System.out.println("\n\n\n\n\n\n\n\n\n\n");
					validReply = true;
				} else {
					System.out.println("ERROR: Invalid reply.");
				}
			}
		}
		input.close();
		System.out.println("Thank you for playing!  This program was written by David Germaine, Jan. 2020 as part of the Tech Elevator Detroit[2] cohort.");
	}
	
	public static int handScore(List<PlayingCard> hand) {
		int value = 0;
		for (PlayingCard card : hand) {
			value += card.getValue();
		}
		return value;
	}
	
	public static void setAcesToOne(List<PlayingCard> hand) {
		for (PlayingCard card : hand) {
			if (card.getValue() == 11) {
				card.setValue(1);
			}
		}
	}

}