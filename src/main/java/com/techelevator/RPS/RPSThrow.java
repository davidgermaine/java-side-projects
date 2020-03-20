package com.techelevator.RPS;

public class RPSThrow {
	
	private int throwValue; // a throw has only one characteristic: what type of throw it is
	
	/*
	 * Constructor that randomly generates a throw value
	 */
	public RPSThrow(String userName) {
		int tempValue = (int)(Math.random() * 10);
		if (tempValue == 9) {
			this.throwValue = 9;
			System.out.println(userName + " has thrown Java...");
		} else if (tempValue <= 8 && tempValue >= 6) {
			this.throwValue = 4;
			System.out.println(userName + " has thrown Scissors...");
		} else if (tempValue <= 5 && tempValue >= 3) {
			this.throwValue = 2;
			System.out.println(userName + " has thrown Paper...");
		} else if (tempValue <= 2 && tempValue >= 0) {
			this.throwValue = 1;
			System.out.println(userName + " has thrown Rock...");
		}
	}
	
	/*
	 * Overloaded constructor that allows a string entry for a throw value
	 */
	public RPSThrow(String userName, String inputString) {
		if (inputString.equalsIgnoreCase("java")) {
			this.throwValue = 9;
			System.out.println(userName + " has thrown Java...");
		} else if (inputString.equalsIgnoreCase("scissors")) {
			this.throwValue = 4;
			System.out.println(userName + " has thrown Scissors...");
		} else if (inputString.equalsIgnoreCase("paper")) {
			this.throwValue = 2;
			System.out.println(userName + " has thrown Paper...");
		} else if (inputString.equalsIgnoreCase("rock")) {
			this.throwValue = 1;
			System.out.println(userName + " has thrown Rock...");
		}
	}
	
	/*
	 * Create getters and setters for class RPSThrow
	 */
	
	public void setThrowValue(int throwValue) { // method to manually set throw value
		this.throwValue = throwValue;
	}
	
	public int getThrowValue() { // method to return throw value
		return throwValue;
	}
	
}