import ADTs.ListArrayBased;
import ADTs.MyListReferenceBased;

public class Line
{
	// attributes for each line in the store
	private ListArrayBased<Customer>  line;
	private String name;
	
	public Line(String name)
	{
		this.name = name;
		line = new ListArrayBased<>();
	}
	
	public int getNumCustomers()
	{
		return line.size();
	}
	
	public void addCustomer(Customer customer)
	{
		line.add(line.size(), customer);
	}
	
	public Customer dequeueCustomer()
	{
		Customer res = line.get(0);
		line.remove(0);
		return res;
	}
	
	public Customer peekNext()
	{
		return line.get(0);
	}
	
	public boolean isEmpty()
	{
		return line.size() == 0;
	}
	
	public Customer get(int index)
	{
		return line.get(index);
	}
	
	public String toString()
	{
		String output = "  The following customer is in the " + name + " checkout line:\n";
		Customer customer;
		for(int i = 0; i < line.size(); i++)
		{
			customer = line.get(i);
			output += "\tCustomer " + customer.getName() + " has " + customer.getNumItems() + " items in the shopping basket.\n" ;
		}
		return output;
	}
}