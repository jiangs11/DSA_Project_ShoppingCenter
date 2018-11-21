import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ADTs.*;

/**
 * Purpose: Data Structure and Algorithms Group Project
 * Status: Complete and thoroughly tested
 * Last update: 12/11/18
 * Submitted:  12/11/18
 * Comment: test suite and sample run attached
 * @author: Matthew Schofield & Steven Jiang
 * @version: 2018.12.11
 */

public class Driver 
{	
	// Reader for taking in user input
	private static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		printMenu();
	}	// end main
	
	private static void printMenu() throws NumberFormatException, IOException
	{
		ListArrayBased<Customer> customerList = new ListArrayBased<Customer>();
		ListArrayBased<Item> itemList = new ListArrayBased<Item>();
		Line regular1 = new Line();
		Line regular2 = new Line();
		Line express = new Line();
		
		System.out.println("Welcome to the Shopping Center!!!\n");
		
		System.out.print("Please specify stock.\n"
				+ " How many items do you have? ");
		int numItemsInStore = Integer.parseInt(buff.readLine().trim());
		System.out.println(numItemsInStore);
		
		System.out.print("Please specify restocking amount: ");
		int restockAmount = Integer.parseInt(buff.readLine().trim());
		System.out.println(restockAmount);
		
		// loop to add each item to the list
		for (int i = 0; i < numItemsInStore; i++)
		{
			System.out.print(">>Enter item name : ");
			String itemName = buff.readLine().trim();
			System.out.println(itemName);
			
			System.out.print(">>How many " + itemName + "s? ");
			int itemCount = Integer.parseInt(buff.readLine().trim());
			System.out.println(itemCount);
			
			Item item = new Item(itemName, itemCount);
			itemList.add(i, item);
			
			System.out.println(itemCount + " items of " + itemName + " have been placed in stock.");
		}	// end for
		
		System.out.print("Please select the checkout line that should check out customers first (regular1/regular2/express):");
		String currentLine= buff.readLine();
		System.out.println(currentLine);
		
		int currentLineTurn = 0;
		
		System.out.println("\nHere are the choices to select from:\r\n" + 
				"	 0.Close the Shopping Center.\r\n" + 
				"	 1.Customer enters Shopping Center.\r\n" + 
				"	 2.Customer picks an item and places it in the shopping cart.\r\n" + 
				"	 3.Customer removes an item from the shopping cart.\r\n" + 
				"	 4.Customer is done shopping.\r\n" + 
				"	 5.Customer checks out.\r\n" + 
				"	 6.Print info about customers who are shopping.\r\n" + 
				"	 7.Print info about customers in checkout lines.\r\n" + 
				"	 8.Print info about items at or below re-stocking level.\r\n" + 
				"	 9.Reorder an item.");
		System.out.print("Make your menu selection now: ");
		
		while (true)
		{
			String response = buff.readLine().trim();
			System.out.println(response);

			switch (response)
			{
				// close the shopping center
				case "0":
					System.out.println("The Shopping Center is closing...come back tomorrow.\n");
					System.exit(0);
					break;
					
				// customer enters shopping center
				case "1":
					addCustomer(customerList);
					break;
					
				// customer picks an item and places it in the shopping cart
				case "2":
					addItemToCart(customerList, itemList);
					incTimeForAllCustomers(customerList);
					break;
					
				// customer removes an item from the shopping cart
				case "3":
					removeItemFromCart(customerList);
					incTimeForAllCustomers(customerList);
					break;
					
				// customer is done shopping
				case "4":
					customerDoneShopping(customerList, regular1, regular2, express, currentLine);
					break;
					
				// customer checks out
				case "5":
					customerChecksOut(currentLineTurn, regular1, regular2, express, customerList);
					break;
					
				// print info about customers who are shopping
				case "6":
					printShopping(customerList);
					break;
					
				// print info about customers in checkout lines
				case "7":
					printCheckingOut(regular1, regular2, express);
					break;
				
				// print info about items at or below re-stocking level
				case "8":
					printRestockingItems(itemList, restockAmount);
					break;
					
				// reorder an item
				case "9":
					reorderAnItem(itemList);
					break;
					
				// input other than 0-9
				default:
					System.out.println("Invalid input. Please try again: ");
					break;
					
			}	// end switch
			System.out.println();
			System.out.print("You know the choices! Select one: ");
			
		}	// end while
	}	// end printMenu
	
	/*
	private static void chooseStartingLine() throws IOException
	{
		System.out.print("Please select the checkout line that should check out "
				+ "customers first (regular1/regular2/express):\n");
		String checkoutLine = buff.readLine().trim().toLowerCase();
		System.out.println(checkoutLine);
		
		switch(checkoutLine) 
		{
			case "regular1":
				regular1.e
				break;
				
			case "regular2":
				startingLine = 1;
				break;
				
			case "express": 
				startingLine = 2;
				break;
				
			default:
				break;
		}
	}*/
	
	// returns true if customer isn't in the store
	private static boolean searchName(ListArrayBased<Customer> customerList, String customerName)
	{
		boolean searching = true;
		int index = 0;
		int size = customerList.size();
		while (searching && index < size) 
		{
			if ((customerList.get(index)).getName().equals(customerName))
			{
				searching = false;
			}
			index++;
		}
		return searching;
	}
	
	// returns true if item isn't in the store
	private static int searchItem(ListArrayBased<Item> itemList, String itemName)
	{
		int index = -1;
		int len = itemList.size();
		for(int i = 0; i < len; i++)
		{
			if(itemList.get(i).getName().equals(itemName))
			{
				index = i;
				break;
			}
		}
		return index;
	}
	
	// increments time in store for each customer
	private static void incTimeForAllCustomers(ListArrayBased<Customer> customerList)
	{
		int size = customerList.size();
		
		for (int i = 0; i < size; i++)
		{
			customerList.get(i).incTimeInStore();
		}
	}
	
	// case 1
	private static void addCustomer(ListArrayBased<Customer> customerList) throws IOException
	{
		boolean notFound;
		do {
			System.out.print(">>Enter customer name : ");
			String name = buff.readLine().trim();
			System.out.println(name);
			notFound = searchName(customerList, name);
			if (notFound)
			{
				customerList.add(0, new Customer(name));
				System.out.println("Customer " + name + " is now in the Shopping Center.\n");
			}
			else
			{
				System.out.println("Customer " + name + " is already in the Shopping Center!\n");
			}
		}while(!notFound);
	}
	
	// case 2
	private static void addItemToCart(ListArrayBased<Customer> customerList, ListArrayBased<Item> itemList) throws IOException
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!\n");
		}
		else
		{
			boolean found;
			Customer customer = null;
			do {
				System.out.print(">>Enter customer name : ");
				String customerName = buff.readLine().trim();
				System.out.println(customerName);
				
				int size = customerList.size();
				found = false;
				for (int index = 0; index < size && !found; index++)
				{
					if (customerList.get(index).getName().equals(customerName))
					{
						customer = customerList.get(index);
						if(customer.getState().equals("CheckOut")) {
							System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						}else {
							found = true;
						}
					}
				}
			}while(!found);
				
				System.out.print(">>What item does " + customer.getName() + " want?");
				String itemWanted = buff.readLine().trim();
				System.out.println(itemWanted);
				
				int itemExists = searchItem(itemList, itemWanted);
				if (itemExists == -1 || itemList.get(itemExists).getNumberOf() == 0)
				{
					System.out.println("No " + itemWanted + " in stock.");
				}
				else
				{
					int len = itemList.size();
					for(int i = 0; i < len; i++)
					{
						if(itemList.get(i).getName().equals(itemWanted)) {
								itemList.get(i).addToNumberOf(-1);
								customer.addToNumItems(1);
								System.out.println("Customer " + customer.getName() + 
									" has now " + customer.getNumItems() + " item in the shopping cart.\n");
								break;
						}
					}
				}
					
					// end for
			
		}	// end else
	}
	
	//case 3
	private static void removeItemFromCart(ListArrayBased<Customer> customerList) throws IOException
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!\n");
		}
		else
		{
			System.out.print(">>Enter customer name : ");
			String customerName = buff.readLine().trim();
			System.out.println(customerName);
			
			int size = customerList.size();
			boolean found = false;
			for (int index = 0; index < size && !found; index++)
			{
				Customer customer = customerList.get(index);
				if (customer.getName().equals(customerName))
				{
					customer.addToNumItems(-1);
					System.out.println("Customer " + customer.getName() + 
						" has now " + customer.getNumItems() + " item in the shopping cart.\n");
					found = true;
				}
			}	// end for
		}	// end else
	}
	
	// case 4
	private static void customerDoneShopping(ListArrayBased<Customer> customerList, Line regular1, 
			Line regular2, Line express, String tieBreakerLine) throws IOException
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo customers in the Shopping Center!\n");
		}
		else
		{
			int longestTimeCustomerIndex = 0;
			Customer longestTimeCustomer = customerList.get(0);
			int len = customerList.size();
			for(int i = 1; i < len; i++)
			{
				if(customerList.get(i).getTimeInStore() > longestTimeCustomer.getTimeInStore())
				{
					longestTimeCustomerIndex = i;
					longestTimeCustomer = customerList.get(longestTimeCustomerIndex);
				}
			}
			customerList.remove(longestTimeCustomerIndex);
			
			if (longestTimeCustomer.getNumItems() != 0)
			{				
				if (longestTimeCustomer.getNumItems() <= 4)
				{
					int expressSize = express.getNumCustomers();
					int regular1Size = regular1.getNumCustomers();
					int regular2Size = regular2.getNumCustomers();
					if(expressSize >= 2*regular1Size || expressSize >= 2*regular2Size)
					{
						if(regular1Size < regular2Size){
							regular1.enqueueCustomer(longestTimeCustomer);
							System.out.println("After " + longestTimeCustomer.getTimeInStore() + " minutes in Shopping Center customer " + longestTimeCustomer.getName() + " with " + longestTimeCustomer.getNumItems() + " items is now in first checkout line.");
						}else
						{
							regular2.enqueueCustomer(longestTimeCustomer);
							System.out.println("After " + longestTimeCustomer.getTimeInStore() + " minutes in Shopping Center customer " + longestTimeCustomer.getName() + " with " + longestTimeCustomer.getNumItems() + " items is now in second checkout line.");
						}
					}else {
						express.enqueueCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore() + " minutes in Shopping Center customer " + longestTimeCustomer.getName() + " with " + longestTimeCustomer.getNumItems() + " items is now in express checkout line.");
						
					}
				}else {
					if(regular1.getNumCustomers() < regular2.getNumCustomers()){
						regular1.enqueueCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore() + " minutes in Shopping Center customer " + longestTimeCustomer.getName() + " with " + longestTimeCustomer.getNumItems() + " items is now in first checkout line.");
						
					}else
					{
						regular2.enqueueCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore() + " minutes in Shopping Center customer " + longestTimeCustomer.getName() + " with " + longestTimeCustomer.getNumItems() + " items is now in second checkout line.");
						
					}
				}
			}
			else
			{
				System.out.print("Should customer " + longestTimeCustomer.getName() + " leave or keep on shopping? Leave?(Y/N):");
				String option = buff.readLine().trim().toUpperCase();
				System.out.println(option);
				if(option.equals("N"))
				{
					Customer reEnteredCustomer = new Customer(longestTimeCustomer.getName());
					customerList.add(customerList.size(), reEnteredCustomer);
					System.out.println("Customer " + reEnteredCustomer.getName() + " with 0 items returned to shopping.");
				}else {
					System.out.println(longestTimeCustomer.getName() + " has left the store.");
				}
			}
		}
	}
	
	// case 5
	private static void customerChecksOut(int cLT, Line regular1, Line regular2, Line expressLine, ListArrayBased<Customer> customerList) throws IOException
	{
		if(regular1.isEmpty() && regular2.isEmpty() && expressLine.isEmpty()) {
			System.out.println("\tNo customers in any line.");
		}else {
			int currentLineTurn = cLT;
			Line line = null;
			do {
				switch(currentLineTurn)
				{
					// Express
					case 0:
						line = expressLine;
						break;
					// Regular1
					case 1:
						line = regular1;
						break;
					// Regular2
					case 2:
						line = regular2;
						break;
				}
				currentLineTurn = (currentLineTurn + 1) %3;
			}while(line.isEmpty());
				
			Customer customer = line.dequeueCustomer();;
			
			System.out.print("Should customer " + customer.getName() + " check out or keep on shopping? Checkout?(Y/N):");
			String choice = buff.readLine().trim().toUpperCase();
			System.out.println(choice);
			if(choice.equals("N")) {
				Customer rentered = new Customer(customer.getName(), customer.getNumItems());
				customerList.add(customerList.size(), rentered);
				System.out.println("Customer " + rentered.getName() + " with " + customer.getNumItems() + " items returned to shopping.");
			}else {
				System.out.println("Customer " + customer.getName() + " is now leaving the Shopping Center.");
			}
			
		}
	}
		
	// case 6
	private static void printShopping(ListArrayBased<Customer> customerList)
	{
		int sizeOfList = customerList.size();
		if (sizeOfList == 0)
		{
			System.out.println("\tNo customers are in the Shopping Center!\n");
		}
		else
		{
			System.out.println("\tThe following " + sizeOfList + " customers are in the Shopping Center:");
			//StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < sizeOfList; i++)
			{
				Customer customer = customerList.get(i);
				System.out.println("Customer " + customer.getName() + " with " + customer.getNumItems() + 
						" items present for " + customer.getTimeInStore() + " minutes");
			}
		}
	}
	
	// case 7
	private static void printCheckingOut(Line regular1, Line regular2,Line express)
	{
		if (regular1.isEmpty())
		{
			System.out.println("\tNo customers are in the first checkout line!");
		}
		else
		{
			System.out.println(regular1.toString());
		}
		if (regular2.isEmpty())
		{
			System.out.println("\tNo customers are in the second checkout line!");
		}
		else
		{
			System.out.println(regular2.toString());
		}
		if (express.isEmpty())
		{
			System.out.println("\tNo customers are in the express checkout line!\n");
		}
		else
		{
			System.out.println(express.toString());
		}
	}
	
	// case 8
	private static void printRestockingItems(ListArrayBased<Item> itemList, int restockAmount)
	{
		int itemSize = itemList.size();
		System.out.println("Items at re-stocking level:");
		for (int i = 0; i < itemSize; i++)
		{
			Item item = itemList.get(i);
			if (item.getNumberOf() <= restockAmount)
			{
				System.out.println(item.getName() + " with " + item.getNumberOf() + " items.\n");
			}
		}
	}
	
	// case 9
	private static void reorderAnItem(ListArrayBased<Item> itemList) throws IOException
	{
		System.out.print("Enter item name to be re-ordered : ");
		String itemToBeReordered = buff.readLine().trim();
		System.out.println(itemToBeReordered);
		
		int indexOfItem = searchItem(itemList, itemToBeReordered);
		if(indexOfItem == -1)
		{
			System.out.println(itemToBeReordered + " is not in stock!");
		}else {
			System.out.print("Enter number of " + itemToBeReordered + " to be re-ordered : ");
			int numberToReorder = Integer.parseInt(buff.readLine().trim());
			System.out.println(numberToReorder);
			
			itemList.get(indexOfItem).addToNumberOf(numberToReorder);
			
			System.out.println("Stock has now " + itemList.get(indexOfItem).getNumberOf() + " " + itemToBeReordered);	
		}
	}
	
}	// end class