package com.techelevator.RPS;

import java.util.Scanner;

public class RPSGameModes {

	public static void main(String[] args) {
		// Trying to implement a "Best of X" game mode into the RPSGame program
		Scanner input = new Scanner(System.in); // identifies user input as keyboard

		boolean playGame = true; // condition that the user wants to play the game
		while (playGame) { // while true...
			System.out.println("Welcome to RPS, now with a \"Best of X\" game mode!");
			System.out.print("How many games would you like to play the best of (e.g. best of 3, 5...): ");
			String bestOfString = input.nextLine();
			int bestOfInt = Integer.parseInt(bestOfString);
	
			boolean gameModeCheck = false; // variable to check if the game mode is a valid selection
			while (!gameModeCheck) {
				if (bestOfInt == (int)bestOfInt) {
					gameModeCheck = true;
					System.out.println("Initializing game to play a best of " + bestOfString + "...");
				} else {
					System.out.print("Invalid integer, please only enter an integer number of games: ");
					bestOfString = input.nextLine();
					bestOfInt = Integer.parseInt(bestOfString);
				}
		
			}
		
			boolean truthStatementGame = true;
			while (truthStatementGame) {
		
				int computerWinCounter = 0;
				int userWinCounter = 0;
				boolean truthStatementWin = false;
				while (!truthStatementWin) { // as long as truthStatement = false, the loop will continue
					
					// greet user and ask them to input a string for their throw
					System.out.print("Please enter your throw: "); // user prompt
					String userThrow = input.nextLine().toUpperCase(); // saves user input into variable "userThrow"
					int userConvert = 0; // variable stores the result of converting the user input into a score
					
					/*
					 * We can assign the user's string input to an integer "score" based on what string was entered.  
					 * For simplicity, we will assign the same integers to the same type of throw as we labeled 
					 * them for the computer.
					 * 
					 * WARNING: you CANNOT simply equate a string variable to a string (i.e. userThrow == "JAVA").
					 * The correct method in Java is variable.equals("STRING") (i.e. userThrow.equals("JAVA"))
					 */
					boolean userThrowCheck = false; // variable will check if the user input a valid response
					while (!userThrowCheck) { // use the NOT operator to ensure the loop runs only if the response is invalid
						if (userThrow.equals("JAVA")) { // JAVA is considered a trump card to play
							userConvert = 9; // JAVA == 9
							userThrowCheck = true;
						} else if (userThrow.equals("SCISSORS")) { // will only beat paper
							userConvert = 4; // SCISSORS == 4
							userThrowCheck = true;
						} else if (userThrow.equals("PAPER")) { // will only beat rock
							userConvert = 2; // PAPER == 2
							userThrowCheck = true;
						} else if (userThrow.equals("ROCK")) { // will only beat scissors
							userConvert = 1; // ROCK == 1
							userThrowCheck = true;
						} else { // print an error message and loop again
							System.out.print("Invalid input, please only input ROCK, PAPER, or SCISSORS: ");
							userThrow = input.nextLine().toUpperCase();
						}
					}
					
					/*
					 * The computer will choose a random double from 0-1 which is then converted into an
					 * integer from 0-9.  The "throw" of the computer will be based on this randomness.
					 */
					int computerThinking = (int)(Math.random() * 10); // declare variable to store computer's integer
					int computerConvert = 0;	// variable stores the result of converting the random integer into a score
					
					/*
					 * Once the computer has chosen an integer, the program needs to translate the integer into
					 * a "throw" to compare to the user's.
					 */
					if (computerThinking == 9) { // 1/10 chance computer throws JAVA
						computerConvert = 9; // let 9 = JAVA
						System.out.println("The computer has thrown JAVA...");
					} else if (computerThinking < 9 && computerThinking >= 6) { // 3/10 chance computer throws SCISSORS
						computerConvert = 4; // Let 4 = SCISSORS
						System.out.println("The computer has thrown SCISSORS...");
					} else if (computerThinking < 6 && computerThinking >= 3) { // 3/10 chance computer throws PAPER
						computerConvert = 2; // Let 2 = PAPER
						System.out.println("The computer has thrown PAPER...");
					} else if (computerThinking < 3 && computerThinking >= 0) { // 3/10 chance computer throws ROCK
						computerConvert = 1; // Let 1 = ROCK (default)
						System.out.println("The computer has thrown ROCK...");
					}
					
					/*
					 * Now that we've assigned "scores" to both the computer and user throws, we need a way to compare
					 * the two inputs and determine a winner.  One method is to make the scores of each throw unique in the
					 * manner that the difference between any two different scores does not equal the difference of another
					 * set.
					 * 
				 	* If using 3 possible inputs (e.g. rock, paper, scissors), there are 3 "winning" scenarios and one
				 	* "tying" scenario.  If using the method of differences, the tying result will always be 0.  In this
				 	* example, there are 6 "winning" scenarios, as one input is considered a "trump card" over the other three.
				 	* 
				 	* Because we assigned the same scores to the same type of throw for both computer and user, the
				 	* differences for a user win are simply the negative result of the the differences for a computer win.
				 	*/
					int difference = userConvert - computerConvert; // calculate the difference of user vs. computer score
					if (difference == 0) { // difference is zero only if both throws are the same
						System.out.println("The game is a draw.  Replaying round..."); // print result
					} else if ((difference == -1) || (difference == -2) || (difference == 3) || (difference == -5) ||
							(difference == -7) || (difference == -8)) { // these occur if the computer has a winning throw
						System.out.println("The computer has won."); // print result
						computerWinCounter++;
					} else if ((difference == 1) || (difference == 2) || (difference == -3) || (difference == 5) ||
							(difference == 7) || (difference == 8)) { // these occur if the user has a winning throw
						System.out.println("You have won."); // print result
						userWinCounter++;
					}
					System.out.println("The current score is Computer: " + computerWinCounter + ", User: " + userWinCounter);
					if (computerWinCounter > bestOfInt / 2) {
						System.out.println("The computer has won the game.  Better luck next time!");
						truthStatementWin = true;
					} else if (userWinCounter > bestOfInt / 2) {
						System.out.println("You have won and beaten the computer!");
						truthStatementWin = true;
					}
				}
				/*
				 * Now that the game has been decided, prompt user to play again so the code continues without needing
				 * to be rerun.  In this case, the most exclusive option is to stop the game, so we must find a way
				 * to break the while loop if the user decides to stop playing.
				 */
				System.out.print("Would you like to play again with the same rules? (Y/N): "); // prompt user to play again
				String userConfirm = input.nextLine().toUpperCase(); // store user input into userConfirm
				boolean userConfirmCheck = false; // variable will check to see if the user input is valid
				while (!userConfirmCheck) { // use the NOT operator to ensure the loop only runs while the response is invalid
					if (userConfirm.equals("N")) { // if user enters "N"...
						truthStatementGame = false; // set the while condition to false to break the loop
						userConfirmCheck = true;
						System.out.println("Returning to game mode selection...");
					} else if (userConfirm.equals("Y")) {
						userConfirmCheck = true;
						System.out.println("New game initiated...");
					} else { // print out an error message and loop again
						System.out.print("Invalid response, please only respond Y (yes) or N (no): ");
						userConfirm = input.nextLine();
					}
				}
			}
			System.out.print("Would you like to play a new game? (Y/N): "); // prompt user to play again
			String userConfirm2 = input.nextLine().toUpperCase(); // store user input into userConfirm
			boolean userConfirmCheck2 = false; // variable will check to see if the user input is valid
			while (!userConfirmCheck2) { // use the NOT operator to ensure the loop only runs while the response is invalid
				if (userConfirm2.equals("N")) { // if user enters "N"...
					playGame = false; // set the while condition to false to break the loop
					userConfirmCheck2 = true;
					System.out.println("Thank you for playing!  This program was written by David Germaine, Jan. 2020 as part of the Tech Elevator Detroit[2] cohort.");
				} else if (userConfirm2.equals("Y")) {
					userConfirmCheck2 = true;
					System.out.println("New game initiated...");
				} else { // print out an error message and loop again
					System.out.print("Invalid response, please only respond Y (yes) or N (no): ");
					userConfirm2 = input.nextLine();
				}
			}
		}
		input.close(); // closes the input device (keyboard) from inputting any more data
	}

}