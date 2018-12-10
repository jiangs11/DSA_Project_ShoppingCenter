/*
 * The Customer class represents each and every individual person 
 * that enters the shopping center.
 * 
 * @author Matthew Schofield & Steven Jiang
 * @version 2018.12.11
 */
public class Customer 
{
	// attributes for each customer
	private String name;
	private int timeInStore;
	private int numItems;
	private String state;
	
	/*
	 * Creates a Customer object to enter the shopping center
	 * 
	 * @param name the name of the customer
	 */
	public Customer(String name)
	{
		this.name = name;
		this.timeInStore = 0;
		this.numItems = 0;
		this.state = "Browsing";
	}
	
	/*
	 * Creates a Customer object
	 * 
	 * @param name the name of the customer
	 * @param numItems the number of items that the customer currently has
	 */
	public Customer(String name, int numItems)
	{
		this.name = name;
		this.timeInStore = 0;
		this.numItems = numItems;
		this.state = "Browsing";
	}
	
	/*
	 * Accessor method to get the state that the customer is in: browsing or checkout
	 * 
	 * @return the state that the customer is in
	 */
	public String getState()
	{
		return state;
	}
	
	/*
	 * Mutator method that changes the state that the customer is in, 
	 * from browsing to checkout
	 */
	public void joinCheckout()
	{
		state = "CheckOut";
	}
	
	/*
	 * Accessor method to get the customer's name
	 * 
	 * @return the name of the customer
	 */
	public String getName()
	{
		return name;
	}

	/*
	 * Accessor method to get how long the customer has spent in the store
	 * 
	 * @return the time that the customer has spent in the store
	 */
	public int getTimeInStore()
	{
		return timeInStore;
	}
	
	/*
	 * Accessor method to get the number of items in the customer's cart
	 * 
	 * @return the number of items in the customer's cart
	 */
	public int getNumItems()
	{
		return numItems;
	}
	
	/*
	 * Mutator method that increases the customer's number of items
	 * 
	 * @param amountToAdd the amount to add to the customer's current number of items
	 */
	public void addToNumItems(int amountToAdd)
	{
		numItems += amountToAdd;
	}
	
	/*
	 * Mutator method that increases the customer's spent in the store
	 */
	public void incTimeInStore() 
	{
		timeInStore++;
	}
	
}	// end Customer