public class Item
{
	// attributes for each item
	private String name;
	private int numberOf;
	
	public Item(String name, int numberOf)
	{
		this.name = name;
		this.numberOf = numberOf;
	}
	
	/*
	 * Accessor method to get the name of the item
	 * 
	 * @return the name of the item
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * Accessor method to get the number of items in stock
	 * 
	 * @return the number of items in stock
	 */
	public int getNumberOf()
	{
		return numberOf;
	}
	
	/*
	 * Mutator method that increases the item's number of quantity
	 * 
	 * @param amountToAdd the amount to add to the item's number in stock
	 */
	public void addToNumberOf(int amountToAdd)
	{
		numberOf += amountToAdd;
	}
	
}	// end class