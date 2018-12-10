import ADTs.ListArrayBased;
/*
 * The Line class represents the one of three different lines
 * that a customer may enter once they are done shopping.
 * 
 * @author Matthew Schofield & Steven Jiang
 * @version 2018.12.11
 */
public class Line
{
	// attributes for each line in the store
	private ListArrayBased<Customer>  line;
	private String name;
	
	/*
	 * Creates a Line object used for customer checking out
	 * 
	 * @param name the name of the one of three lines
	 */
	public Line(String name)
	{
		this.name = name;
		line = new ListArrayBased<>();
	}
	
	/*
	 * Accessor method to get how many customers are in the line
	 */
	public int getNumCustomers()
	{
		return line.size();
	}
	
	/*
	 * Mutator method to add a customer into the line
	 * 
	 * @param customer the customer to be added in line
	 */
	public void addCustomer(Customer customer)
	{
		line.add(line.size(), customer);
	}
	
	/*
	 * The customer that is currently checking out finishes
	 * 
	 * @return the customer that just finished checking out
	 */
	public Customer dequeueCustomer()
	{
		Customer result = line.get(0);
		line.remove(0);
		return result;
	}
	
	/*
	 * Checks to see which customer is currently checking out
	 * 
	 * @return the customer that is currently checking out
	 */
	public Customer peekNext()
	{
		return line.get(0);
	}
	
	/*
	 * Checks to see whether a line is empty or not
	 * 
	 * @return true if no customers in line, false if there is at least one
	 */
	public boolean isEmpty()
	{
		return line.size() == 0;
	}
	
	/*
	 * Accessor method to get the customer at specified position in the line
	 * 
	 * @param index the position in the line
	 */
	public Customer get(int index)
	{
		return line.get(index);
	}
	
	/*
	 * 
	 * 
	 * @return the information about all customers who are in a specific line
	 */
	public String toString()
	{
		String output = "  The following customer is in the " + name + " checkout line:\n";
		Customer customer;
		int lineSize = line.size();
		for(int i = 0; i < lineSize; i++)
		{
			customer = line.get(i);
			output += "\tCustomer " + customer.getName() + " has " + customer.getNumItems() + " items in the shopping basket.\n" ;
		}
		return output;
	}
	
}	// end Line