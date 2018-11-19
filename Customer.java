
public class Customer 
{
	private String name;
	private int timeInStore;
	private int numItems;
	
	public Customer(String name)
	{
		this.name = name;
		this.timeInStore = 0;
		this.numItems = 0;
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
