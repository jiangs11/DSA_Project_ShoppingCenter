package ADTs;

import java.util.Iterator;
import java.util.ListIterator;



//Please note that this code is slightly different from the textbook code 
//to reflect the fact that the Node class is implemented using data encapsulation


//****************************************************
//Reference-based implementation of ADT list.
//****************************************************
public class MyListReferenceBased implements ListInterfacePlus 
{
	//reference to linked list of items
	private Node head; 
	
	public MyListReferenceBased() 
	{
		head = null;
	}  // end default constructor
	
	public boolean isEmpty() 
	{
		return size() == 0;
	}  // end isEmpty
	
	public int size() 
	{
		int numItems = 0;
		if(head != null) {
			Node current = head;
			while(current != null)
			{
				current = current.getNext();
				numItems += 1;
			}
		}
		return numItems;
	}  // end size
	
	public String toString()
	{
		String result = "";
		if(head != null) {
			Node current = head;
			while(current != null)
			{
				result += " " + current.getItem();
				current = current.getNext();
			}
		}
		return result;
	}
	private Node find(int index) 
	{
	//--------------------------------------------------
	//Locates a specified node in a linked list.
	//Precondition: index is the number of the desired
	//node. Assumes that 0 <= index <= numItems 
	//Postcondition: Returns a reference to the desired 
	//node.
	//--------------------------------------------------
	Node curr = head;
	for (int skip = 0; skip < index; skip++) 
	{
	 curr = curr.getNext();
	} // end for
	return curr;
	} // end find
	
	public Object get(int index) 
	           throws ListIndexOutOfBoundsException 
	{
	if (index >= 0 && index < size()) 
	{
	 // get reference to node, then data in node
	 Node curr = find(index);
	 Object dataItem = curr.getItem();
	 return dataItem;
	} 
	else 
	{
	 throw new ListIndexOutOfBoundsException(
	                "List index out of bounds exception on get");
	} // end if
	} // end get
	
	public void add(int index, Object item)
	             throws ListIndexOutOfBoundsException 
	{
	if (index >= 0 && index < size()+1) 
	{
	 if (index == 0) 
	 {
	   // insert the new node containing item at
	   // beginning of list
	   Node newNode = new Node(item, head);
	   head = newNode;
	 } 
	 else 
	 {
	   Node prev = find(index-1);
	   // insert the new node containing item after 
	   // the node that prev references
	   Node newNode = new Node(item, prev.getNext());
	   prev.setNext(newNode);
	 } // end if
	} 
	else 
	{
	 throw new ListIndexOutOfBoundsException(
	               "List index out of bounds exception on add");
	} // end if
	}  // end add
	
	public void remove(int index) 
	              throws ListIndexOutOfBoundsException 
	{
	if (index >= 0 && index < size()) 
	{
	 if (index == 0) 
	 {
	   // delete the first node from the list
	   head = head.getNext();
	 } 
	 else 
	 {
	   Node prev = find(index-1);
	   // delete the node after the node that prev
	   // references, save reference to node
	   Node curr = prev.getNext(); 
	   prev.setNext(curr.getNext());
	 } // end if
	} // end if
	else 
	{
	 throw new ListIndexOutOfBoundsException(
	              "List index out of bounds exception on remove");
	} // end if
	}   // end remove
	
	public void removeAll() 
	{
	// setting head to null causes list to be
	// unreachable and thus marked for garbage 
	// collection
	head = null;
	} // end removeAll

	public Iterator iterator()
	{
		return new LRBIterator();
	}
	
	public ListIterator listIterator()
	{
		return new LRBListIterator();
	}
	
	public ListIterator listIterator(int index)
	{
		return new LRBListIterator(index);
	}
	private class LRBIterator implements Iterator{

		Node curNode;
		
		LRBIterator()
		{
			curNode = head;
		}
		
		@Override
		public boolean hasNext() {
			
			return curNode != null;
		}

		@Override
		public Object next() {
			Object res = curNode.getItem();
			curNode = curNode.getNext();
			return res;
		}
		
	}
	
	private class LRBListIterator implements ListIterator{

		int curInd;
		Node curNode;
		
		LRBListIterator(int index)
		{
			curInd = index;
			curNode = find(index);
		}
		
		LRBListIterator()
		{
			curInd = -1;
			curNode = null;
		}
		
		@Override
		public boolean hasNext() {
			boolean res;
			if(head == null) {
				res = false;
			}else if(head != null && curInd == -1) {
				res = true;
			}else {
				res = curNode.getNext() != null;				
			}
			return res;
		}

		@Override
		public Object next() {
			if(curNode == null) {
				curNode = head;
			}else {  
				curNode = curNode.getNext();	
			}
			curInd += 1;
			return curNode.getItem();
		}

		@Override
		public void add(Object objectToAdd) {
			MyListReferenceBased.this.add(curInd+1, objectToAdd);
		}

		@Override
		public boolean hasPrevious() {
			return curInd != 0;
		}

		@Override
		public int nextIndex() {
			return curInd + 1;
		}

		@Override
		public Object previous() {
			curInd -= 1;
			curNode = find(curInd);
			return find(curInd).getItem();
		}

		@Override
		public int previousIndex() {
			return curInd-1;
		}

		@Override
		public void remove() {
			MyListReferenceBased.this.remove(curInd);			
		}

		@Override
		public void set(Object obj) {
			curNode.setItem(obj);			
		}
		
	}
	
} // end ListReferenceBased

