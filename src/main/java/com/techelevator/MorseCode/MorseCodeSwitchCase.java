package com.techelevator.MorseCode;

import java.util.Scanner;

public class MorseCodeSwitchCase {
	
	public static String AlphaToMorse(String[] entry) {
		
		String morseHolder = ""; // placeholder string for final output
		for (int i = 0; i < entry.length; i++) { // for each word in the input string...
			String[] letterArray = entry[i].split(""); // split word into individual letters
			
			for (int j = 0; j < letterArray.length; j++) { // for each letter from the split words...
				switch (letterArray[j].toLowerCase()) { // use switch case to equate letters to Morse code
					case "a": morseHolder += ".- ";
						break;
					case "b": morseHolder += "-... ";
						break;
					case "c": morseHolder += "-.-. ";
						break;
					case "d": morseHolder += "-.. ";
						break;
					case "e": morseHolder += ". ";
						break;
					case "f": morseHolder += "..-. ";
						break;
					case "g": morseHolder += "--. ";
						break;
					case "h": morseHolder += ".... ";
						break;
					case "i": morseHolder += ".. ";
						break;
					case "j": morseHolder += ".--- ";
						break;
					case "k": morseHolder += "-.- ";
						break;
					case "l": morseHolder += ".-.. ";
						break;
					case "m": morseHolder += "-- ";
						break;
					case "n": morseHolder += "-. ";
						break;
					case "o": morseHolder += "--- ";
						break;
					case "p": morseHolder += ".--. ";
						break;
					case "q": morseHolder += "--.- ";
						break;
					case "r": morseHolder += ".-. ";
						break;
					case "s": morseHolder += "... ";
						break;
					case "t": morseHolder += "- ";
						break;
					case "u": morseHolder += "..- ";
						break;
					case "v": morseHolder += "...- ";
						break;
					case "w": morseHolder += ".-- ";
						break;
					case "x": morseHolder += "-..- ";
						break;
					case "y": morseHolder += "-.-- ";
						break;
					case "z": morseHolder += "--.. ";
						break;
					case "1": morseHolder += ".---- ";
						break;
					case "2": morseHolder += "..--- ";
						break;
					case "3": morseHolder += "...-- ";
						break;
					case "4": morseHolder += "....- ";
						break;
					case "5": morseHolder += "..... ";
						break;
					case "6": morseHolder += "-.... ";
						break;
					case "7": morseHolder += "--... ";
						break;
					case "8": morseHolder += "---.. ";
						break;
					case "9": morseHolder += "----. ";
						break;
					case "0": morseHolder += "----- ";
						break;
				} // breaks switch statement
			} // break for loop transcribing letters
			morseHolder += " / ";
		} // break for loop splitting words
		return morseHolder;
	} // ends translating method
	
	public static String MorseToAlpha(String[] entry) {
		
		String alphaHolder = ""; // placeholder string for final output
		for (int i = 0; i < entry.length; i++) { // for each word in the input string...
			String[] signalArray = entry[i].split(" "); // split word into individual letters
			
			for (int j = 0; j < signalArray.length; j++) { // for each letter from the split words...
				switch (signalArray[j]) { // use switch case to equate letters to Morse code
					case ".-": alphaHolder += "a";
						break;
					case "-...": alphaHolder += "b";
						break;
					case "-.-.": alphaHolder += "c";
						break;
					case "-..": alphaHolder += "d";
						break;
					case ".": alphaHolder += "e";
						break;
					case "..-.": alphaHolder += "f";
						break;
					case "--.": alphaHolder += "g";
						break;
					case "....": alphaHolder += "h";
						break;
					case "..": alphaHolder += "i";
						break;
					case ".---": alphaHolder += "j";
						break;
					case "-.-": alphaHolder += "k";
						break;
					case ".-..": alphaHolder += "l";
						break;
					case "--": alphaHolder += "m";
						break;
					case "-.": alphaHolder += "n";
						break;
					case "---": alphaHolder += "o";
						break;
					case ".--.": alphaHolder += "p";
						break;
					case "--.-": alphaHolder += "q";
						break;
					case ".-.": alphaHolder += "r";
						break;
					case "...": alphaHolder += "s";
						break;
					case "-": alphaHolder += "t";
						break;
					case "..-": alphaHolder += "u";
						break;
					case "...-": alphaHolder += "v";
						break;
					case ".--": alphaHolder += "w";
						break;
					case "-..-": alphaHolder += "x";
						break;
					case "-.--": alphaHolder += "y";
						break;
					case "--..": alphaHolder += "z";
						break;
					case ".----": alphaHolder += "1";
						break;
					case "..---": alphaHolder += "2";
						break;
					case "...--": alphaHolder += "3";
						break;
					case "....-": alphaHolder += "4";
						break;
					case ".....": alphaHolder += "5";
						break;
					case "-....": alphaHolder += "6";
						break;
					case "--...": alphaHolder += "7";
						break;
					case "---..": alphaHolder += "8";
						break;
					case "----.": alphaHolder += "9";
						break;
					case "-----": alphaHolder += "0";
						break;
				} // breaks switch statement
			} // break for loop transcribing letters
			alphaHolder += " ";
		} // break for loop splitting words
		return alphaHolder;
	} // ends translating method
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter message to be translated: "); // user prompt
		String userInput = input.nextLine(); // store user input
		System.out.println("Confirm if this message is in (M)orse code or (A)lphanumeric: "); // user prompt
		String userConfirm = input.nextLine(); // store user input
		
		if (userConfirm.equals("A")) {
			String[] alphaArray = userInput.split(" "); // split input into individual words
			String outputMorse = AlphaToMorse(alphaArray);
			System.out.println(outputMorse);
			String[] morseArray = outputMorse.split(" / ");
			System.out.println(MorseToAlpha(morseArray)); // run translator method
		} else if (userConfirm.contentEquals("M")) {
			String[] morseArray = userInput.split(" / "); // split input into individual words
			String outputAlpha = MorseToAlpha(morseArray);
			System.out.println(outputAlpha);
			String[] alphaArray = outputAlpha.split(" ");
			System.out.println(AlphaToMorse(alphaArray)); // run translator method
		}
		input.close();
	}
}