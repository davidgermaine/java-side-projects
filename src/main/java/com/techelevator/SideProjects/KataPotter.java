package com.techelevator.SideProjects;

import java.util.ArrayList;
import java.util.List;

public class KataPotter {
	
	public double getDiscount(int[] shoppingBasket) {
		/*
		 * Exception if the basket is null
		 */
		if (shoppingBasket == null) {
			return 0;
		}
		
		/*
		 * Create a list to hold the number of unique books per round of investigation
		 */
		List <Integer> bookCounts = new ArrayList<>();
		double totalPrice = 0;
		boolean whileStatement = true;
		
		/*
		 * Use a while loop to iterate through the shoppingBasket
		 * until all books have been removed from the basket and
		 * add the count of unique books to the list after reaching
		 * the end of the basket
		 */
		while (whileStatement) {
			int numberOfBooksForDiscount = 0;
			for (int i = 0; i < shoppingBasket.length; i++) {
				if (shoppingBasket[i] > 0) {
					numberOfBooksForDiscount++;
					shoppingBasket[i]--;
				}
			}
			if (numberOfBooksForDiscount == 0) {
				whileStatement = false;
			}
			bookCounts.add(numberOfBooksForDiscount);
		}
		
		/*
		 * Exception for the case of the combination of counts of 5 and 3:
		 * a combination of 5 and 3 is actually cheaper when read as
		 * two sets of 4
		 */
		while (bookCounts.contains(5) && bookCounts.contains(3)) {
			bookCounts.remove(bookCounts.indexOf(5));
			bookCounts.remove(bookCounts.indexOf(3));
			totalPrice += 51.20;
		}
		
		/*
		 * As 5 and 3 is the only exception for a cheaper combination,
		 * read the remaining counts as is once all pairings of 5 and 3
		 * have been removed from the list.
		 */
		for (int count : bookCounts) {
			if (count == 1) {
				totalPrice += 8;
			} else if (count == 2) {
				totalPrice += 15.2;
			} else if (count == 3) {
				totalPrice += 21.6;
			} else if (count == 4) {
				totalPrice += 25.6;
			} else if (count == 5) {
				totalPrice += 30;
			}
		}
		return totalPrice;
	}

}
