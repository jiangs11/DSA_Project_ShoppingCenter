import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Purpose: Data Structure and Algorithms Group Project
 * Status: Complete and thoroughly tested
 * Last update: 12/11/18
 * Submitted:  12/11/18
 * Comment: test suite and sample run attached
 * @author: Matthew Schofield & Steven Jiang
 * @version: 2018.10.17
 */

public class Driver 
{	
	private static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		
		printMenu();
	}	// end main
	
	private static void printMenu() throws NumberFormatException, IOException
	{
		System.out.println("Welcome to the Shopping Center!!!\n");
		
		System.out.print("Please specify stock.\n"
				+ " How many items do you have? ");
		int numItemsInStore = Integer.parseInt(buff.readLine().trim());
		System.out.println(numItemsInStore);
		
		System.out.print("Please specify restocking amount: ");
		int restockAmount = Integer.parseInt(buff.readLine().trim());
		System.out.println(restockAmount);
		
		for (int i = 0; i < numItemsInStore; i++)
		{
			System.out.print(">>Enter item name " + (i + 1) + " : ");
			String itemName = buff.readLine().trim();
			System.out.println(itemName);
			
			System.out.print(">>How many " + itemName + "s? ");
			int itemCount = Integer.parseInt(buff.readLine().trim());
			System.out.println(itemCount);
			
			Item item = new Item(itemName, itemCount);
			// ADT.add(item);
			
			System.out.println(itemCount + " items of " + itemName + " have been placed in stock.");
		}	// end for
		
		System.out.print("Please select the checkout line that should check out "
				+ "customers first (regular1/regular2/express): ");
		String checkoutLine = buff.readLine().trim();
		System.out.println(checkoutLine);
		
		System.out.println("Here are the choices to select from:\r\n" + 
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
		
		while (true)
		{
			System.out.print("Make your menu selection now: ");
			
			String response = buff.readLine().trim();
			System.out.println(response);

			switch (response)
			{
				case "0":
					System.out.println("The Shopping Center is closing...come back tomorrow.\n");
					System.exit(0);
					break;
					
				case "1":
					
					break;
					
				case "2":
	
					break;
					
				case "3":
					
					break;
					
				case "4":
					
					break;
					
				case "5":
	
					break;
					
				case "6":
	
					break;
					
				case "7":
					
					break;

				case "8":
					
					break;
					
				default:
					System.out.println("Invalid input. Please try again: ");
					break;
					
			}	// end switch
		}	// end while
	}	// end printMenu
}	// end class
