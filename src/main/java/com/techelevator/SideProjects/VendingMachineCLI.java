package com.techelevator.SideProjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = 
		{ MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = 
		{ PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH };

	private Menu mainMenu;
	private Menu purchaseMenu;

	public VendingMachineCLI(Menu mainMenu, Menu purchaseMenu) {
		this.mainMenu = mainMenu;
		this.purchaseMenu = purchaseMenu;
	}

	public void runMainMenu(List<VendableItem> inventoryList) {
		boolean shouldLoop = true;
		while (shouldLoop) {
			String choice = (String) mainMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			switch (choice) {
			case MAIN_MENU_OPTION_DISPLAY_ITEMS: {
				printInventory(inventoryList);
				break;
			}
			case MAIN_MENU_OPTION_PURCHASE: {
				runPurchaseMenu(inventoryList);
				break;
			}
			case MAIN_MENU_OPTION_EXIT: {
				shouldLoop = false;
				break;
			}
			}
		}
	}
	
	public void runPurchaseMenu(List<VendableItem> inventoryList) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		FileWriter logFile = null;
		try {
			logFile = new FileWriter("log.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter write = new PrintWriter(logFile);
		
		double customerAccount = 0; // convert double
		boolean shouldLoop = true;
		while (shouldLoop) {
			System.out.printf("Current Money Provided: $%4.2f\n", customerAccount);
			String choice = (String) purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			
			switch (choice) {
			case PURCHASE_MENU_OPTION_FEED_MONEY: {
				double previousBalance = customerAccount;
				customerAccount = getCustomerAccount(customerAccount);
				double difference = customerAccount - previousBalance;
				Date date = new Date();
				String dateLine = dateFormat.format(date);
				String logLine = String.format(dateLine + "\t%20s: $%4.2f \tUpdated Balance: $%4.2f\n", "FEED MONEY",
						difference, customerAccount);
				write.append(logLine);
				break;
			}
			case PURCHASE_MENU_OPTION_SELECT_PRODUCT: {
				printInventory(inventoryList);
				VendableItem itemSelection = getUserSelection(inventoryList);
				if (checkCustomerAccount(customerAccount, itemSelection.getPrice())) {
					itemSelection.vendProduct();
					double previousBalance = customerAccount;
					customerAccount -= itemSelection.getPrice();
					System.out.printf("\nProduct: %-20s Cost: $%4.2f \tRemaining Balance: $%6.2f\n", 
							itemSelection.getProduct(), itemSelection.getPrice(), customerAccount);
					System.out.println(itemSelection.getYumMessage(itemSelection.getProductType()) + "\n");
					Date date = new Date();
					String dateLine = dateFormat.format(date);
					String logLine = String.format(dateLine + "\t%20s: $%4.2f \tUpdated Balance: $%4.2f\n", 
							itemSelection.getProduct(), previousBalance, customerAccount);
					write.append(logLine);
				} else {
					System.out.println("Insufficient funds");
				}
				break;
			}
			case PURCHASE_MENU_OPTION_FINISH: {
				int customerAccountRounded = (int)Math.round(customerAccount * 100);
				int quartersReturned = customerAccountRounded / 25;
				customerAccountRounded -= (quartersReturned * 25);
				int dimesReturned = (customerAccountRounded / 10);
				customerAccountRounded -= (dimesReturned * 10);
				int nickelsReturned = (customerAccountRounded / 5);
				
				System.out.printf("Returning $%4.2f as " + quartersReturned + " quarters, "
						+ dimesReturned + " dimes, and " + nickelsReturned + " nickels.", customerAccount);
				shouldLoop = false;
				break;
			}
			}
		}
		write.close();
	}
	
	private static boolean checkCustomerAccount(double customerAccount, double price) { // convert double
		if (customerAccount >= price) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("resource")
	private static VendableItem getUserSelection(List<VendableItem> inventoryList) {
		Scanner userInput = new Scanner(System.in);
		VendableItem userSelection = null;
		while(userSelection == null) {
			System.out.println("Enter ID of item to dispense: ");
			String id = userInput.nextLine();
	
			for (VendableItem product : inventoryList) {
				if (product.getSlotID().equalsIgnoreCase(id)) {
					if (product.getStockCount() > 0) {
						userSelection = product;
						continue;
					} else if (product.getStockCount() == 0) {
						System.out.println("Out of stock");
						continue;
					}
				}
			}
			if (userSelection == null) {
				System.out.println("Not an item");
			}
		}
		return userSelection;
	}
	
	@SuppressWarnings("resource")
	private static double getCustomerAccount(double customerAccount) { // convert double
		Scanner input = new Scanner(System.in);
		double newAccountBalance = 0; // convert double
		while (newAccountBalance == 0) {
			System.out.println("Enter a whole dollar amount to withdraw from your account: ");
			String userInput = input.nextLine();
			double inputMoney = Double.parseDouble(userInput);
			try {
				newAccountBalance = customerAccount + inputMoney; // convert double
			} catch (NumberFormatException e) {
				System.out.println("NaN entered, please don't be an idiot");
			}
		}
		return newAccountBalance;
	}
	
	private static File getFile() throws FileNotFoundException {
		String path = "vendingmachine.csv";
		File inputFile = new File(path);
		if (inputFile.exists() == false) {
			System.out.println(path + " does not exist");
			System.exit(0);
		} else if (inputFile.isFile() == false) {
			System.out.println(path + " is not a valid file");
			System.exit(0);
		}
		return inputFile;
	}
	
	@SuppressWarnings("resource")
	private static List<VendableItem> convertFileToList(File inputFile) throws FileNotFoundException {
		List<VendableItem> inventoryList = new ArrayList<>();
		Scanner fileScanner = new Scanner(inputFile);
		
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String[] dataArray = line.split("\\|");
			VendableItem product = new VendableItem(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
			inventoryList.add(product);
		}
		return inventoryList;
	}
	
	private static void printInventory(List<VendableItem> inventory) {
		for (VendableItem product : inventory) {
			System.out.printf("%-4s %20s %6.2f %14s %3d\n", product.getSlotID(), product.getProduct(), 
					product.getPrice(), product.getProductType(), product.getStockCount());
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = getFile();
		List<VendableItem> inventoryList = convertFileToList(inputFile);
		Menu mainMenu = new Menu(System.in, System.out); // System.in = keyboard inputs, System.out = console screen
		Menu purchaseMenu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(mainMenu, purchaseMenu);
		cli.runMainMenu(inventoryList);
	}
}
