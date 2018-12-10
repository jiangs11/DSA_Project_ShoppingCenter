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
		Line regular1 = new Line("first");
		Line regular2 = new Line("second");
		Line express = new Line("express");
		
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
		String currentLine = buff.readLine().toLowerCase().trim();
		System.out.println(currentLine);

		int currentLineTurn = -1;
		switch (currentLine)
		{
			// Express
			case "express":
				currentLineTurn = 0;
				break;
			// Regular1
			case "regular1":
				currentLineTurn = 1;
				break;
			// Regular2
			case "regular2":
				currentLineTurn = 2;
				break;
		}
		
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
					addCustomer(customerList, regular1, regular2, express);
					break;
					
				// customer picks an item and places it in the shopping cart
				case "2":
					addItemToCart(customerList, itemList);
					break;
					
				// customer removes an item from the shopping cart
				case "3":
					removeItemFromCart(customerList, regular1, regular2, express);
					break;
					
				// customer is done shopping
				case "4":
					customerDoneShopping(customerList, regular1, regular2, express, currentLine);
					break;
					
				// customer checks out
				case "5":
					currentLineTurn = customerChecksOut(currentLineTurn, regular1, regular2, express, customerList);
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
	 *  Helper method that searches through the list, containing all the customers in the store.
	 *  We want to check whether a customer, who is about to enter the store, is unique. 
	 *  
	 *  @param customerList the list containing all the customers in the store
	 *  @param customerName the name of the customer to check whether they are in the store already
	 *  @return             true if unique customer, false if already in the store
	 */
	private static boolean searchName(ListArrayBased<Customer> customerList, String customerName)
	{
		// local variables
		boolean uniqueCustomer = true;
		int index = 0;
		int customerSize = customerList.size();
		
		// loops through the collection until a customer is not unique or we reach the end
		while (uniqueCustomer && index < customerSize) 
		{
			// checks the specified customer with each customer item in the list
			if ((customerList.get(index)).getName().equals(customerName))
			{
				// customer is not unique
				uniqueCustomer = false;
			}	// end if
			// increment index
			index++;
		}	// end while
		return uniqueCustomer;
	}	// end searchName()
	
	/*
	 *  Helper method that searches through the list, containing all the items in the store.
	 *  We want to check whether an item is actually in the store. 
	 *  
	 *  @param itemList the list containing all the items in the store
	 *  @param itemName the name of the item to check whether it is in the store
	 *  @return         the index where the item is found or -1 if not found
	 */
	private static int searchItem(ListArrayBased<Item> itemList, String itemName)
	{
		// local variables
		int index = -1;
		int itemSize = itemList.size();
		boolean found = false;
		
		// loops through the collection until an item is found or we reach the end
		for (int i = 0; i < itemSize && !found; i++)
		{
			// checks the specified item with each item in the list
			if (itemList.get(i).getName().equals(itemName))
			{
				// grab the desired item index and exit the loop
				index = i;
				found = true;
			}	// end if
			// else, increment
		}	// end for
		return index;
	}	// end searchItem()
	
	/*
	 *  Helper method that loops through the list, containing all the customers in the store.
	 *  We want to increment the time spent in store for each customer by 1.
	 *  
	 *  @param customerList the list containing all the customers in the store
	 */
	private static void incTimeForAllCustomers(ListArrayBased<Customer> customerList)
	{
		// local variable
		int customerSize = customerList.size();
		
		// loops through the customer collection until we reach the end
		for (int i = 0; i < customerSize; i++)
		{
			// increments each customers' time spent in store by 1
			customerList.get(i).incTimeInStore();
		}	// end for
	}	// end incTimeForAllCustomers()
	
	/*
	 *  Method that loops through the list, containing all the customers in the store.
	 *  We want to increment the time spent in store for each customer by 1.
	 *  
	 *  @param customerList the list containing all the customers in the store
	 */
	private static void addCustomer(ListArrayBased<Customer> customerList, Line regular1, Line regular2, Line express) throws IOException
	{
		boolean notFound;
		do 
		{
			// prompts user for input to insert customer into the store
			System.out.print(">>Enter customer name : ");
			String customerName = buff.readLine().trim();
			System.out.println(customerName);
			
			// call helper method to check uniqueness of customer
			notFound = searchName(customerList, customerName);
			// only insert if customer name was unique
			if (notFound)
			{
				// Check lines
				int reg1Size = regular1.getNumCustomers();
				int reg2Size = regular2.getNumCustomers();
				int expressSize = express.getNumCustomers();
				boolean foundInLine = false;
				for(int i = 0; i < reg1Size && !foundInLine; i++)
				{
					// grab the customer at current index
					Customer customer = regular1.get(i);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						foundInLine = true;
					}
				}
				for(int i = 0; i < reg2Size && !foundInLine; i++)
				{
					// grab the customer at current index
					Customer customer = regular2.get(i);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						foundInLine = true;
					}
				}
				for(int i = 0; i < expressSize && !foundInLine; i++)
				{
					// grab the customer at current index
					Customer customer = express.get(i);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						foundInLine = true;
					}
				}
				if(!foundInLine)
				{
					// inserts customer into list
					customerList.add(customerList.size(), new Customer(customerName));
					System.out.println("Customer " + customerName + " is now in the Shopping Center.");
				}else {
					System.out.println("Customer " + customerName + " is already in the Shopping Center!");					
					notFound = false;
				}
			}
			else
			{
				System.out.println("Customer " + customerName + " is already in the Shopping Center!");
			}
		}
		// keep looping if customer is already in the list
		while(!notFound);
	}	// end addCustomer()
	
	/*
	 *  Method that increments a customer's number of items in cart and decreases
	 *  that items' amount left in stock by 1
	 *  
	 *  @param customerList the list containing all the customers in the store
	 *  @param itemList 	the list containing all the items in the store
	 */
	private static void addItemToCart(ListArrayBased<Customer> customerList, ListArrayBased<Item> itemList) throws IOException
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!");
		}
		else
		{
			boolean found;
			Customer customer = null;
			do 
			{
				// prompts user for customer name
				System.out.print(">>Enter customer name : ");
				String customerName = buff.readLine().trim();
				System.out.println(customerName);
				
				int customerSize = customerList.size();
				found = false;
				// loops through the collection until we found the customer or we reach the end
				for (int index = 0; index < customerSize && !found; index++)
				{
					// checks the specified customer with each customer item in the list
					if (customerList.get(index).getName().equals(customerName))
					{
						// grab the customer at current index
						customer = customerList.get(index);
						System.out.println(customer.getState());
						// checks whether if the customer is still shopping or checking out
						if (customer.getState().equals("CheckOut"))
						{
							System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						}
						// customer is still shopping
						else
						{
							// the customer that was grabbed is what we want
							found = true;
						}
					}	// end if
				}	// end for
			}	// end do 
			while(!found);
				
			// prompts the user for item name
			System.out.print(">>What item does " + customer.getName() + " want? ");
			String itemWanted = buff.readLine().trim();
			System.out.println(itemWanted);
			
			// checks if the specified item exists
			int itemExists = searchItem(itemList, itemWanted);
			
			if (itemExists == -1)
			{
				// item out of stock
				System.out.println("No " + itemWanted + "s in Shopping Center.");			}
			else if (itemList.get(itemExists).getNumberOf() == 0)
			{
				// item does not exist
				System.out.println("No " + itemWanted + "s in stock.");
			}
			else
			{
				// local variables
				int itemSize = itemList.size();
				boolean searching = true;
				
				// loops through the item collection until we reach the end
				for (int i = 0; i < itemSize && searching; i++)
				{
					// checks the specified item with each item in the list
					if (itemList.get(i).getName().equals(itemWanted)) 
					{
						// decreases the item's stock amount by 1
						itemList.get(i).addToNumberOf(-1);
						// increases increases number of items by 1
						customer.addToNumItems(1);
						System.out.println("Customer " + customer.getName() + 
							" has now " + customer.getNumItems() + " items in the shopping cart.");
						// exit loop
						searching = false;
						// increase time spent in store for all customers by 1
						incTimeForAllCustomers(customerList);
					}	// end if
				}	// end for
			}	// end inner else
		}	// end outter else
	}	// end addItemToCart()
	
	/*
	 *  Method that loops through the list, containing all the customers in the store.
	 *  We want to increment the time spent in store for each customer by 1.
	 *  
	 *  @param customerList the list containing all the customers in the store
	 */
	private static void removeItemFromCart(ListArrayBased<Customer> customerList, Line regular1, 
			Line regular2, Line express) throws IOException
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!");
		}
		else
		{
			boolean found = false;
			do {
				// prompts user for customer name
				System.out.print(">>Enter customer name : ");
				String customerName = buff.readLine().trim();
				System.out.println(customerName);
				
				// local variables
				int customerSize = customerList.size();
				
				
				// loops through the item collection until we reach the end
				for (int index = 0; index < customerSize && !found; index++)
				{
					// grab the customer at current index
					Customer customer = customerList.get(index);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						// decrements the customers' number of items in cart by 1
						customer.addToNumItems(-1);
						System.out.println("Customer " + customer.getName() + 
							" has now " + customer.getNumItems() + " item in the shopping cart.");
						
						// increase time spent in store for all customers by 1
						incTimeForAllCustomers(customerList);
						// exit loop
						found = true;
					}	// end if
				}	// end for
				
				// Check lines
				int reg1Size = regular1.getNumCustomers();
				int reg2Size = regular2.getNumCustomers();
				int expressSize = express.getNumCustomers();
				boolean foundInLine = false;
				for(int i = 0; i < reg1Size && !foundInLine; i++)
				{
					// grab the customer at current index
					Customer customer = regular1.get(i);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						foundInLine = true;
					}
				}
				for(int i = 0; i < reg2Size && !foundInLine; i++)
				{
					// grab the customer at current index
					Customer customer = regular2.get(i);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						foundInLine = true;
					}
				}
				for(int i = 0; i < expressSize && !foundInLine; i++)
				{
					// grab the customer at current index
					Customer customer = express.get(i);
					
					// checks the specified customer with each customer in the list
					if (customer.getName().equals(customerName))
					{
						System.out.println("Customer " + customer.getName() + " is in a checkoutline !");
						foundInLine = true;
					}
				}
				if(foundInLine)
				{
					System.out.println("Customer " + customerName + " is in a checkoutline !");
				}
				
			}while(!found);
		}	// end else
	}	// end removeItemFromCart()
	
	// NEED COMMENTS
	private static void customerDoneShopping(ListArrayBased<Customer> customerList, Line regular1, 
			Line regular2, Line express, String tieBreakerLine) throws IOException
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo customers in the Shopping Center!");
		}
		else
		{
			int longestTimeCustomerIndex = 0;
			Customer longestTimeCustomer = customerList.get(0);
			int customerSize = customerList.size();
			for (int i = 1; i < customerSize; i++)
			{
				if (customerList.get(i).getTimeInStore() >= longestTimeCustomer.getTimeInStore())
				{
					if (customerList.get(i).getTimeInStore() == longestTimeCustomer.getTimeInStore()) {
						if(longestTimeCustomer.getNumItems() == 0){
							longestTimeCustomerIndex = i;
							longestTimeCustomer = customerList.get(longestTimeCustomerIndex);
						}
					}else {
						longestTimeCustomerIndex = i;
						longestTimeCustomer = customerList.get(longestTimeCustomerIndex);
					}
				}
			}
			customerList.remove(longestTimeCustomerIndex);
			
			if (longestTimeCustomer.getNumItems() != 0)
			{	
				longestTimeCustomer.joinCheckout();
				if (longestTimeCustomer.getNumItems() <= 4)
				{
					int expressSize = express.getNumCustomers();
					int regular1Size = regular1.getNumCustomers();
					int regular2Size = regular2.getNumCustomers();
					if(expressSize == 0) {
						express.addCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore()
							+ " minutes in Shopping Center customer " + longestTimeCustomer.getName() 
							+ " with " + longestTimeCustomer.getNumItems()
							+ " items is now in express checkout line.");
						
					}else if((expressSize > 2 * regular1Size) || (expressSize > 2 * regular2Size))
					{
						if (regular1Size <= regular2Size)
						{
							regular1.addCustomer(longestTimeCustomer);
							System.out.println("After " + longestTimeCustomer.getTimeInStore() 
								+ " minutes in Shopping Center customer " + longestTimeCustomer.getName()
								+ " with " + longestTimeCustomer.getNumItems() 
								+ " items is now in first checkout line.");
						}
						else
						{
							regular2.addCustomer(longestTimeCustomer);
							System.out.println("After " + longestTimeCustomer.getTimeInStore()
								+ " minutes in Shopping Center customer " + longestTimeCustomer.getName()
								+ " with " + longestTimeCustomer.getNumItems()
								+ " items is now in second checkout line.");
						}
					}
					else 
					{
						express.addCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore()
							+ " minutes in Shopping Center customer " + longestTimeCustomer.getName() 
							+ " with " + longestTimeCustomer.getNumItems()
							+ " items is now in express checkout line.");
					}
				}
				else
				{
					if (regular1.getNumCustomers() <= regular2.getNumCustomers())
					{
						regular1.addCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore() 
							+ " minutes in Shopping Center customer " + longestTimeCustomer.getName() 
							+ " with " + longestTimeCustomer.getNumItems()
							+ " items is now in first checkout line.");
					}
					else
					{
						regular2.addCustomer(longestTimeCustomer);
						System.out.println("After " + longestTimeCustomer.getTimeInStore() 
							+ " minutes in Shopping Center customer " + longestTimeCustomer.getName()
							+ " with " + longestTimeCustomer.getNumItems() 
							+ " items is now in second checkout line.");
					}
				}
			}
			else
			{
				System.out.print("Should customer " + longestTimeCustomer.getName() + " leave or keep on shopping? Leave?(Y/N):");
				String option = buff.readLine().trim().toUpperCase();
				System.out.println(option);
				if (option.equals("N"))
				{
					Customer reEnteredCustomer = new Customer(longestTimeCustomer.getName());
					customerList.add(customerList.size(), reEnteredCustomer);
					System.out.println("Customer " + reEnteredCustomer.getName() + " with 0 items returned to shopping.");
				}
				else 
				{
					System.out.println(longestTimeCustomer.getName() + " has left the store.");
				}
			}
		}
	}	// end customerDoneShopping
	
	/*
	 *  
	 *  
	 *  @param clt			the line
	 *  @param regular1 
	 *  @param regular2 
	 *  @param expressLine
	 *  @param customerList the list containing all the customers in the store
	 */
	private static int customerChecksOut(int cLT, Line regular1, Line regular2, Line expressLine, 
			ListArrayBased<Customer> customerList) throws IOException
	{
		int res;
		// checks if there are no customers in any of the three checkout lines
		if (regular1.isEmpty() && regular2.isEmpty() && expressLine.isEmpty())
		{
			System.out.println("\tNo customers in any line.");
			res = cLT;
		}
		else 
		{
			int currentLineTurn = cLT;
			Line line = null;
			do 
			{
				switch (currentLineTurn)
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
				currentLineTurn = (currentLineTurn + 1) % 3;
			}	// end do
			while(line.isEmpty());
				
			
			Customer customer = line.dequeueCustomer();;
			
			System.out.print("Should customer " + customer.getName() + " check out or keep on shopping? Checkout?(Y/N):");
			String choice = buff.readLine().trim().toUpperCase();
			System.out.println(choice);
			if (choice.equals("N")) 
			{
				Customer rentered = new Customer(customer.getName(), customer.getNumItems());
				customerList.add(customerList.size(), rentered);
				System.out.println("Customer " + rentered.getName() + " with " + customer.getNumItems() + " items returned to shopping.");
			}
			else 
			{
				System.out.println("Customer " + customer.getName() + " is now leaving the Shopping Center.");
			}
			res = currentLineTurn;
		}	// end outter else
		return res;
	}	// end customerChecksOut()
		
	/*
	 * 	Method that prints all the customers in the store that are currently shopping.
	 * 
	 *  @param customerList the list containing all the customers in the store
	 */
	private static void printShopping(ListArrayBased<Customer> customerList)
	{
		int customerSize = customerList.size();
		
		// checks if there are customers in the store
		if (customerSize == 0)
		{
			System.out.println("\tNo customers are in the Shopping Center!");
		}
		else
		{
			System.out.println("\tThe following " + customerSize + " customers are in the Shopping Center:");
			// loops through the collection until we reach the end
			for (int i = 0; i < customerSize; i++)
			{
				// grabs the customer at the current index
				Customer customer = customerList.get(i);
				// displays all the information about that customer
				System.out.println("\tCustomer " + customer.getName() + " with " + customer.getNumItems() + 
						" items present for " + customer.getTimeInStore() + " minutes");
			}	// end for
		}	// end else
	}	// end printShopping()
	
	/*
	 * 	Method that prints all the customers in each collection that are at the checkout lines.
	 * 
	 *  @param regular1    the first regular checkout line
	 *  @param regular2    the second regular checkout line
	 *  @param expressLine the express checkout line
	 */
	private static void printCheckingOut(Line regular1, Line regular2, Line express)
	{	
		// checks if the first regular checkout line is empty
		if (regular1.isEmpty())
		{
			System.out.println("\tNo customers are in the first checkout line!");
		}
		else
		{
			// prints all customers at the first regular checkout line
			System.out.println(regular1.toString());
		}
		// checks if the second regular checkout line is empty
		if (regular2.isEmpty())
		{
			System.out.println("\tNo customers are in the second checkout line!");
		}
		else
		{
			// prints all customers at the second regular checkout line
			System.out.println(regular2.toString());
		}
		// checks if the express checkout line is empty
		if (express.isEmpty())
		{
			System.out.println("\tNo customers are in the express checkout line!");
		}
		else
		{
			// prints all customers at the express checkout line
			System.out.println(express.toString());
		}
	}	// end printCheckingOut()
	
	/*
	 *  Method that prints all the items in the collection that are at or below the restocking amount
	 *  
	 *  @param itemList 	 the list containing all the items in the store
	 *  @param restockAmount the amount that the user specified for when to restock an item
	 */
	private static void printRestockingItems(ListArrayBased<Item> itemList, int restockAmount)
	{
		int itemSize = itemList.size();
		System.out.println("\tItems at re-stocking level:");
		// loops through the item collection until we reach the end
		for (int i = 0; i < itemSize; i++)
		{
			// grabs the item at the current index
			Item item = itemList.get(i);
			// checks if that item is at or below the restocking amount
			if (item.getNumberOf() <= restockAmount)
			{
				System.out.println("\t" + item.getName() + " with " + item.getNumberOf() + " items.");
			}	// end if
		}	// end for
	}	// end printRestockingItems()
	
	/*
	 *  Method that increases specified item's quantity, in stock, by specified quantity.
	 *  
	 *  @param itemList the list containing all the items in the store
	 */
	private static void reorderAnItem(ListArrayBased<Item> itemList) throws IOException
	{
		// prompts user for item to be re-ordered
		System.out.print("Enter item name to be re-ordered : ");
		String itemToBeReordered = buff.readLine().trim();
		System.out.println(itemToBeReordered);
		
		// checks whether the specified item exists
		int indexOfItem = searchItem(itemList, itemToBeReordered);
		if (indexOfItem == -1)
		{
			System.out.println(itemToBeReordered + " is not in stock!");
		}
		else 
		{
			// prompts user for quantity to be added to the item's stock
			System.out.print("Enter number of " + itemToBeReordered + " to be re-ordered : ");
			int numberToReorder = Integer.parseInt(buff.readLine().trim());
			System.out.println(numberToReorder);
			
			// adds the quantity to the item's stock
			itemList.get(indexOfItem).addToNumberOf(numberToReorder);
			
			System.out.println("Stock has now " + itemList.get(indexOfItem).getNumberOf() + " " + itemToBeReordered + "s.");	
		}	// end else
	}	// end reorderAnItem()
	
}	// end class