
public class Customer 
{
	private String name;
	private int timeInStore;
	private Cart cart;
	
	public Customer(int timeInStore, String name)
	{
		this.name = name;
		this.timeInStore = timeInStore;
		cart = new Cart();
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
		return cart.getNumItems;
	}
	
	public void incTimeInStore() 
	{
		timeInStore--;
	}
}
