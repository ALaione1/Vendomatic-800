package com.techelevator.view;

import com.techelevator.core.Inventory;
import com.techelevator.core.Product;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	private final PrintWriter out;
	private final Scanner in;
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			         displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);

		}
		return choice;
	}
	public Product getChoiceFromSelectProductOption(Product[] options, Inventory inventory) {
		Product choice = null;
		while (choice == null) {
			          displayProductOption(options,inventory);
			choice =  getProductChoiceInput(options);
		}
		return choice;
	}
	private Product getProductChoiceInput(Product[] options) {
		Product choice = null;
		String userInput = in.nextLine();
		int selectedOption = Integer.parseInt(userInput);
		for(Product product : options) {
			if(selectedOption == product.getCode()){
				choice=product;
				break;
			}
		}

		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid Product option ***" +
					System.lineSeparator());

		}
		return choice;
	}
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.parseInt(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length-1; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
	private void displayProductOption(Product[] options, Inventory inventory) {
		out.println(" ");
		for(Product product : options){
		out.println(product.getCode() + ") |  " + product.getName() + " | Price : " + product.getPrice() +  " |  Remain : "
				+ inventory.getInventoryByProduct(product));
		}
		out.print(System.lineSeparator() + "Please choose a Product (Ex : 101) >>> ");
		out.flush();
	}


}
