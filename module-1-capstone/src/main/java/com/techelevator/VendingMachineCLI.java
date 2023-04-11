package com.techelevator;

import com.techelevator.core.*;
import com.techelevator.reporting.SalesReport;
import com.techelevator.util.TELog;
import com.techelevator.view.Menu;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMachineCLI {

//main menu properties
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT = "EXIT";
	private static final String REPORT = "Sales Report";
	private static final String BILLS = "BILLS";
	private static final String COINS = "COINS";
	private static final String BACK = "BACK";

	//sub-menu properties
	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] SUB_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION, "java"};

	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE, EXIT, REPORT};
    private static final  String[] FEED_MONEY_OPTION ={BILLS,COINS,BACK};
	private final Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	public void run() throws IOException {
		TELog.log("Start vending machine !");
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		Inventory newInventory = new Inventory(System.currentTimeMillis());
		SalesReport report = new SalesReport();
		String exit=null;
		try {
				while (!"EXIT".equals(exit)) {
					String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
					 // log the choice
				switch (choice) {
					case MAIN_MENU_OPTION_DISPLAY_ITEMS:
						newInventory.displayProduct();
						TELog.log("Displaying product...");
				continue;
					case MAIN_MENU_OPTION_PURCHASE:
						// Transaction starting ...
					  CashCreditHandler credit = new CashCreditHandler(LocalDateTime.now(),
									new Transaction(System.currentTimeMillis(), LocalDateTime.now()));
						TELog.log("Transaction : #"+credit.getTransaction().getId()+" status " +
									credit.getTransaction().getStatus());
				do {

					// Transaction pending...

				double balance = credit.getRemainingBalance();
				System.out.printf("Current Money Provided: $ %.2f",balance);
					String subChoice = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
				credit.getTransaction().setStatus(Status.PENDING);
				TELog.log("Transaction : #" + credit.getTransaction().getId() + " " +
							credit.getTransaction().getStatus() + " Customer option selection...");
				TELog.log(subChoice); // log the choice
				switch (subChoice) {
					    case FEED_MONEY:
					String MoneyOption = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTION);
				switch (MoneyOption){
						case BILLS:
						Bills billChoice = (Bills) menu.getChoiceFromOptions(Bills.values());
						credit.feedMoneyWithBill(billChoice);
				continue;
						case COINS:
						Coins CoinChoice = (Coins) menu.getChoiceFromOptions(Coins.values());
						credit.feedMoneyWithCoin(CoinChoice);
				continue;
						case BACK:
				continue;
				}
						case SELECT_PRODUCT:

				// purchase operations
				Product product =  menu.getChoiceFromSelectProductOption(newInventory.getProduct(), newInventory);

				if(newInventory.getInventoryByProduct(product)==0) {
					System.err.println("Sorry !, " + product.getName() + " is out of stock");
					TELog.log(product.getName() + " is out of stock");
				break;
				}
				double creditBalance = credit.getCreditBalance(product);
				if(creditBalance<0) {
				System.err.printf("You need a balance of $%.2f please add more Credit",creditBalance *- 1);
					TELog.log("failed selection of : "+product.getName() +
								" need more credit :" + currency.format(creditBalance *- 1));
				System.out.println(" ");
				System.out.println(" ");
				break;
				} else {
				Map<Product, Integer> cart = new HashMap<>();
				cart.put(product, 1);
				credit.getTransaction().addPurchase(new Purchase(cart, credit.getTransaction()));
				newInventory.updateQuantity(product,1);
					TELog.log(product.getCode()+" "+product.getName()+ " " +
						currency.format(product.getPrice()) + " " + currency.format(creditBalance));
						report.addSales(product,1);
				continue;
				}
						case FINISH_TRANSACTION:

				// closing Transaction...
				credit.getTransaction().dispenseProduct();
				System.out.println(" ");
				System.out.printf("Remaining balance is : $%.2f",credit.getRemainingBalance());
				System.out.println(" ");
				System.out.println("Please take your change");
				credit.changeDispense();
					TELog.log("GIVE CHANGE " + currency.format(credit.getRemainingBalance()) + " "
							+ currency.format(0));
				credit.getTransaction().close();
					TELog.log("Transaction : #"+credit.getTransaction().getId() + " status " +
							credit.getTransaction().getStatus());
					}
				} while(!credit.getTransaction().isClose());
				continue;
						case EXIT:
							System.out.println("Have a nice day!");
							exit=EXIT;
							TELog.log("Vending Machine Turn off");
							break;
						case REPORT:
							report.generateReport(newInventory.getProduct());
							TELog.log("Sales report generating...");
							System.out.println(" Sales report have been generated...");
							TELog.log("Sales report generating done...");
					}
				}
			}catch(Exception e) {
				System.out.println("Option menu error :" + e.getMessage());
				TELog.log("Option menu error :" + e.getMessage());
			}
		}
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}


	}
