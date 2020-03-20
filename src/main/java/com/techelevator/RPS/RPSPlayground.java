package com.techelevator.RPS;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RPSPlayground {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		String username = input.nextLine();
		
		int userWins = 0;
		int computerWins = 0;
		
		boolean continueGame = true;
		while (continueGame) {
			
			/*
			 * Retrieve user's desired throw
			 */
			System.out.println("Please enter a throw of rock, paper, or scissors: ");
			String userInput = input.nextLine();
			
			/*
			 * Generate throws for both the user and the computer
			 */		
			RPSThrow userThrow = new RPSThrow(username, userInput);
			int userOutput = userThrow.getThrowValue();
			
			/*
			 * Initiate a while loop to loop until a valid input is received
			 */
			boolean isValidInput = false;
			while (!isValidInput) {
				if (userOutput == 0) { // userOutput is 0 if a prewritten response is not received
					System.out.println("Invalid throw received, please enter a new throw: "); // prompt user to enter another throw
					userInput = input.nextLine();				
					userThrow = new RPSThrow(username, userInput); // generate a new throw for the user
					userOutput = userThrow.getThrowValue();
				} else {
					isValidInput = true; // the loop is broken if the user's input is valid
				}
			}
			
			/*
			 * Generate and store computer's throw
			 */
			RPSThrow computerThrow = new RPSThrow("Computer");
			int computerOutput = computerThrow.getThrowValue();
			
			Map <Integer, String> scoreMap = new HashMap<>(); // create map of possible scores and their respective winner
			scoreMap.put(1, username);
			scoreMap.put(2, username);
			scoreMap.put(-3, username);
			scoreMap.put(5, username);
			scoreMap.put(7, username);
			scoreMap.put(8, username);
			scoreMap.put(-1, "Computer");
			scoreMap.put(-2, "Computer");
			scoreMap.put(3, "Computer");
			scoreMap.put(-5, "Computer");
			scoreMap.put(-7, "Computer");
			scoreMap.put(-8, "Computer");
			scoreMap.put(0, "Nobody");
			
			/*
			 * Determine winner of round based on difference of throw scores
			 */
			int scoreDifference = userOutput - computerOutput;
			String winner = scoreMap.get(scoreDifference);
			if (winner.equals(username)) {
				userWins++;
			} else if (winner.equals("Computer")) {
				computerWins++;
			}
			System.out.println(winner + " is the victor this time.");
			System.out.println("Current score is " + username + ": " + userWins + 
					", Computer: " + computerWins + ".");
			System.out.println("Play again? (Y/N): ");
			String userConfirm = input.nextLine();
			
			/*
			 * Enter loop until valid input is received
			 */
			boolean breakLoop = false;
			while (!breakLoop) {
				if (userConfirm.equalsIgnoreCase("Y")) { // answering Y loops back to start of game
					breakLoop = true;
					continue;
				} else if (userConfirm.equalsIgnoreCase("N")) { // answering N breaks loop and exits game
					System.out.println("Thank you for playing!  This program was written by David Germaine, Jan. 2020 as part of the Tech Elevator Detroit[2] cohort.");
					continueGame = false;
					breakLoop = true;
				} else { // any other input returns invalid input
					System.out.println("Invalid input, please only use Y/N: ");
					userConfirm = input.nextLine();
				}
			}
		}
		input.close();
	}
}