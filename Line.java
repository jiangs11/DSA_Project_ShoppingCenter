import ADTs.QueueRA;

public class Line
{
	// attributes for each line in the store
	private int numCustomers;
	private QueueRA<Customer>  lineQueue;
	
	public Line()
	{
		numCustomers = 0;
		lineQueue = new QueueRA<>();
	}
	
	public int getNumCustomers()
	{
		return numCustomers;
	}
	
	public void enqueueCustomer(Customer customer)
	{
		lineQueue.enqueue(customer);
		numCustomers++;
	}
	
	public Customer dequeueCustomer()
	{
		numCustomers--;
		return lineQueue.dequeue();
	}
	
	public Customer peekNext()
	{
		return lineQueue.peek();
	}
	
	public boolean isEmpty()
	{
		return lineQueue.isEmpty();
	}
	
}