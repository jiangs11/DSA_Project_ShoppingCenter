/*
 * The Item class represents individual types of item that the
 * user specifies to be in the shopping center.
 * 
 * @author Matthew Schofield & Steven Jiang
 * @version 2018.12.11
 */
public class Item
{
	// attributes for each item
	private String name;
	private int numberOf;
	
	/*
	 * Creates an Item object that customers may add or remove from their cart
	 * 
	 * @param name 	   the name of the item
	 * @param numberOf the initial quantity that the item has
	 */
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
	
}	// end Item