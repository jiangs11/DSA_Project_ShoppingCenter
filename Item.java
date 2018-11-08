
public class Item {

	private String name;
	private int numberOf;
	
	public Item(String name, int numberOf)
	{
		this.name = name;
		this.numberOf = numberOf;
	}
	
	public void addToNumberOf(int amountToAdd)
	{
		numberOf += amountToAdd;
	}
	
	public int getNumberOf()
	{
		return numberOf;
	}
	
	public String getName()
	{
		return name;
	}
}
