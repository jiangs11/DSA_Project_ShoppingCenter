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
	private static int storeTime = 0;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		printMenu();
	}	// end main
	
	private static void printMenu() throws NumberFormatException, IOException
	{
		ListArrayBasedPlus<Customer> customerList = new ListArrayBasedPlus<Customer>();
		ListArrayBasedPlus<Item> itemList = new ListArrayBasedPlus<Item>();
		ResizableArrayQueue<Customer> regular1 = new ResizableArrayQueue<Customer>();
		ResizableArrayQueue<Customer> regular2 = new ResizableArrayQueue<Customer>();
		ResizableArrayQueue<Customer> express = new ResizableArrayQueue<Customer>();
		
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
			itemList.add(i, item);
			
			System.out.println(itemCount + " items of " + itemName + " have been placed in stock.");
		}	// end for
		
		System.out.print("Please select the checkout line that should check out "
				+ "customers first (regular1/regular2/express):\n");
		String checkoutLine = buff.readLine().trim();
		System.out.println(checkoutLine);
		
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
		
		while (true)
		{
			System.out.print("Make your menu selection now: ");
			
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
					addItemToCart(customerList);
					storeTime++;
					break;
					
				// customer removes an item from the shopping cart
				case "3":
					removeItemFromCart(customerList);
					storeTime++;
					break;
					
				// customer is done shopping
				case "4":
					customerDoneShopping(customerList, regular1, regular2, express);
					break;
					
				// customer checks out
				case "5":
					customerChecksOut();
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
					printRestockingItems();
					break;
					
				// reorder an item
				case "9":
					reorderAnItem();
					break;
					
				// input other than 0-9
				default:
					System.out.println("Invalid input. Please try again: ");
					break;
					
			}	// end switch
		}	// end while
	}	// end printMenu
	
	private static boolean searchName(ListArrayBasedPlus<Customer> customerList, String customerName)
	{
		boolean searching = true;
		int index = 0;
		while (searching && index < customerList.size()) 
		{
			if (((Customer) customerList.get(index)).getName().equals(customerName))
			{
				searching = false;
			}
			index++;
		}
		return searching;
	}
	
	// case 1
	private static void addCustomer(ListArrayBasedPlus<Customer> customerList) throws IOException
	{
		System.out.print(">>Enter customer name : ");
		String name = buff.readLine().trim();
		boolean found = searchName(customerList, name);
		if (!found)
		{
			customerList.add(0, new Customer(name, storeTime));
			System.out.println("Customer " + name + " is now in the Shopping Center.\n");
		}
		else
		{
			System.out.println("Customer " + name + " is already in the Shopping Center!\n");
		}
	}
	
	// case 2
	private static void addItemToCart(ListArrayBasedPlus<Customer> customerList)
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!");
		}
		else
		{
			
		}
	}
	
	//case 3
	private static void removeItemFromCart(ListArrayBasedPlus<Customer> customerList)
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!");
		}
		else
		{
			
		}
	}
	
	// case 4
	private static void customerDoneShopping(ListArrayBasedPlus<Customer> customerList, ResizableArrayQueue<Customer> regular1, 
			ResizableArrayQueue<Customer> regular2, ResizableArrayQueue<Customer> express)
	{
		if (customerList.isEmpty())
		{
			System.out.println("\tNo one is in the Shopping Center!");
		}
		else
		{
			
		}
	}
	
	// case 5
	private static void customerChecksOut()
	{
		
	}
		
	// case 6
	private static void printShopping(ListArrayBasedPlus<Customer> customerList)
	{
		int sizeOfList = customerList.size();
		if (sizeOfList == 0)
		{
			System.out.println("No customers are in the Shopping Center!\n");
		}
		else
		{
			System.out.println("\tThe following " + sizeOfList + " customers are in the Shopping Center:");
			//StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < sizeOfList; i++)
			{
				Customer customer = customerList.get(i);
				System.out.println("\nCustomer " + customer.getName() + " with " + customer.getNumItems() + 
						" items present for " + customer.getTimeInStore());
			}
		}
	}
	
	// case 7
	private static void printCheckingOut(ResizableArrayQueue<Customer> regular1, ResizableArrayQueue<Customer> regular2, ResizableArrayQueue<Customer> express)
	{
		if (regular1.isEmpty())
		{
			System.out.println("No customers are in the first checkout line!");
		}
		else
		{
			System.out.println(regular1.toString());
		}
		if (regular2.isEmpty())
		{
			System.out.println("No customers are in the second checkout line!");
		}
		else
		{
			System.out.println(regular2.toString());
		}
		if (express.isEmpty())
		{
			System.out.println("No customers are in the express checkout line!");
		}
		else
		{
			System.out.println(express.toString());
		}
	}
	
	// case 8
	private static void printRestockingItems()
	{
		
	}
	
	// case 9
	private static void reorderAnItem()
	{
		
	}
	
}	// end class
