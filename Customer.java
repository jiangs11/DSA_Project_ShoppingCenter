
public class Customer
{
	private String name;
	private int timeInStore;
	private int numItems;
	
	public Customer(int timeInStore, String name)
	{
		this.name = name;
		this.timeInStore = timeInStore;
		numItems = 0;
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
	
	public void setTimeInStore(int time) 
	{
		timeInStore = time;
	}
}