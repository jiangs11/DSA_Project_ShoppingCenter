package ADTs;

/**
 * Purpose: Data Structure and Algorithms MidTerm
 * Status: Complete and thoroughly tested
 * Last update: 10/15/18
 * Submitted:  10/15/18
 * Comment: To act as an ArrayBased implementation of the List ADT
 * @author: Matthew Schofield
 * @version: 2018.10.15
 */
public class ListArrayBased<T> implements ListInterface<T> {

	 protected static final int MAX_LIST = 3;

    protected T[] items;  // an array of list items
    protected int numItems;  // number of items in list

    @SuppressWarnings("unchecked")
	public ListArrayBased()
    {
        items = (T[]) new Object[MAX_LIST];
        numItems = 0;
    }
    
    /**
     * Reverses the list
     */
    public void reverse()
    {
        for(int i = 0; i < numItems/2; i++)
		{
			Object temp = items[numItems-1-i];
			items[numItems-1-i] =  items[i];	
			items[i] = (T) temp;	
		}
    }

    /**
     * toString method to be able to easily print the list
     * 
     * @return a String representation of the list
     */
    public String toString()
    {
        String output = "";
        for(int i = 0; i < numItems; i++)
        {
            output += " " + items[i].toString();
        }
        return output;
    }
    
    @SuppressWarnings("unchecked")
	private void resize()
    {    	
        T[] old = items;
        items = (T[]) new Object[(int)(old.length*1.5 + 1)];
        for(int i = 0; i < numItems; i++)
        {
            items[i] = old[i];
        }
    }
	
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public void add(int index, T item) throws ListIndexOutOfBoundsException {
		if (numItems == items.length)
        {
            resize();
        }
		if (index >= 0 && index <= numItems)
        {
            for (int pos = numItems-1; pos >= index; pos--) 
            {
                items[pos+1] = items[pos];
            }
            items[index] = item;
            numItems++;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }
	}

	@Override
	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }
	}

	@Override
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems)
        {
            for (int pos = index+1; pos < numItems; pos++) 
            {
                items[pos-1] = items[pos];
            } 
            numItems--;
            items[numItems] = null;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeAll() {
		items = (T[]) new Object[MAX_LIST];
        numItems = 0;
	}

}
