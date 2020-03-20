package com.techelevator.MorseCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MorseCodeTranslator {
	
	// key-from-value method borrowed from techiedelight.com
	public static <Keys, Values> Keys getKey(Map<Keys, Values> map, Values value) {
		for (Keys key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}
	
	public static String AlphaToMorse(String[] entry, Map<String, String> characters) {
		
		String morseHolder = ""; // placeholder string for final output
		for (int i = 0; i < entry.length; i++) { // for each word in the input string...
			morseHolder += characters.get(entry[i]) + " ";
		} // break for loop translating values
		return morseHolder;
	} // ends translating method
	
	public static String MorseToAlpha(String[] entry, Map<String, String> characters) {
		String alphaHolder = "";
		for (int i = 0; i < entry.length; i++) {
			alphaHolder += getKey(characters, entry[i]);
		}
		return alphaHolder;
	}

	public static void main(String[] args) {
		boolean moreInput = true;
		while (moreInput) {
			Map <String, String> characterMap = new HashMap<>();
			characterMap.put("a", ".-");
			characterMap.put("b", "-...");
			characterMap.put("c", "-.-.");
			characterMap.put("d", "-..");
			characterMap.put("e", ".");
			characterMap.put("f", "..-.");
			characterMap.put("g", "--.");
			characterMap.put("h", "....");
			characterMap.put("i", "..");
			characterMap.put("j", ".---");
			characterMap.put("k", "-.-");
			characterMap.put("l", ".-..");
			characterMap.put("m", "--");
			characterMap.put("n", "-.");
			characterMap.put("o", "---");
			characterMap.put("p", ".--.");
			characterMap.put("q", "--.-");
			characterMap.put("r", ".-.");
			characterMap.put("s", "...");
			characterMap.put("t", "-");
			characterMap.put("u", "..-");
			characterMap.put("v", "...-");
			characterMap.put("w", ".--");
			characterMap.put("x", "-..-");
			characterMap.put("y", "-.--");
			characterMap.put("z", "--..");
			characterMap.put("0", "-----");
			characterMap.put("1", ".----");
			characterMap.put("2", "..---");
			characterMap.put("3", "...--");
			characterMap.put("4", "....-");
			characterMap.put("5", ".....");
			characterMap.put("6", "-....");
			characterMap.put("7", "--...");
			characterMap.put("8", "---..");
			characterMap.put("9", "----.");
			characterMap.put(" ", "/");
			
			Scanner input = new Scanner(System.in);
			System.out.println("Enter message to be translated: "); // user prompt
			String userInput = input.nextLine(); // store user input
			System.out.println("Confirm if this message is in (M)orse code or (A)lphanumeric: "); // user prompt
			String userConfirm = input.nextLine(); // store user input
			
			if (userConfirm.equalsIgnoreCase("A")) {
				String[] alphaArray = userInput.split(""); // split input into individual letters
				String outputMorse = AlphaToMorse(alphaArray, characterMap);
				System.out.println(outputMorse);
			} else if (userConfirm.equalsIgnoreCase("M")) {
				String[] morseArray = userInput.split(" ");
				String outputAlpha = MorseToAlpha(morseArray, characterMap);
				System.out.println(outputAlpha);
			} else {
				System.out.println("ERROR: unrecognized language input");
			}
			System.out.println("Continue with a new message? (Y/N): ");
			String userContinue = input.nextLine();
			if (userContinue.equalsIgnoreCase("Y")) {
				continue;
			} else if (userContinue.equalsIgnoreCase("N")) {
				moreInput = false;
			} else {
				System.out.println("ERROR: unrecognized response, aborting program.");
				moreInput = false;
			}
			input.close();
		}
	}
}