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

	public static void main(String[] args) throws IOException, NumberFormatException
	{
		System.out.println("Welcome to the Shopping Center!!!\n");
		
		
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
	}
}
