
public class Customer 
{
	private String name;
	private int timeInStore;
	private int numItems;
	private String state;
	
	public Customer(String name)
	{
		this.name = name;
		this.timeInStore = 0;
		this.numItems = 0;
		this.state = "Browsing";
	}
	
	public Customer(String name, int numItems)
	{
		this.name = name;
		this.timeInStore = 0;
		this.numItems = numItems;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void joinCheckout()
	{
		state = "CheckOut";
	}
	
	public String getName()
	{
		return name;
	}

	public int getTimeInStore()
	{
		return timeInStore;
	}
	
	public int getNumItems()
	{
		return numItems;
	}
	
	public void addToNumItems(int amountToAdd)
	{
		numItems += amountToAdd;
	}
	
	public void incTimeInStore() 
	{
		timeInStore++;
	}
}
