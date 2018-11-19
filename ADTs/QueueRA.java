package ADTs;

/**
 * Purpose: Data Structure and Algorithms Lab 6 Problem 2
 * Status: Complete and thoroughly tested
 * Last update: 10/10/18
 * Submitted:  10/11/18
 * Comment: Queue ADT implemented with a resizable array (updated for DEQ compatibility)
 * @author: Matthew Schofield
 * @version: 2018.10.10
 */
public class QueueRA<T> implements QueueInterface<T> {

    // Items in queue
    protected T[] items;
    // front of queue, use mod to get index
    protected int front;
    // back of queue, use mod to get index
    protected int back;
    // number of items in queue
    protected int numItems;

    /**
     * Constructor initializing everything
     */
    public QueueRA()
    {
        items = (T[]) new Object[1];
        front = 0;
        back = 0;
        numItems = 0;
    }

    /**
     * toString() method to represent the queue as a String
     * Front to Back
     */
    public String toString()
    {
        String output = "";
        int itemLen = items.length;
        for(int i = 0; i < numItems; i++)
        {
            output += " " + items[(itemLen+front+i)%itemLen];//(front+i)%itemLen
        }
        return output;
    }

    /**
     * Internal method to resize only if queue needs more space
     *
     * @param newLen, new length to make internal array which serves as the queue's backend
     */
    protected void resize(int newLen)
    {
        T[] newItems = (T[]) new Object[newLen];
        int itemLen = items.length;
        for(int i = 0; i < items.length; i++)
        {
            newItems[i] = items[(itemLen+front+i)%itemLen];
        }
        for(int i = items.length; i < newLen; i++)
        {
            newItems[i] = null;
        }
        items = newItems;
        front = 0;
        back = numItems;
    }

    /**
     * Boolean as to whether the queue is empty
     *
     * @return True if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    /**
     * Add item to the back of the queue
     *
     * @param newItem, item to add
     */
    @Override
    public void enqueue(T newItem) throws QueueException {
        int itemLen = items.length;
        if(numItems == itemLen) {
            resize(items.length + 1);
        }
        items[back%items.length] = newItem;
        back++;
        numItems++;
    }

    /**
     * Take out front item of queue, this dequeues (removes) it from the queue
     *
     * @return front item of queue
     */
    @Override
    public T dequeue() throws QueueException {
    	if(!isEmpty()) {
	        int itemLen = items.length;
	        T res = items[(itemLen+front)%itemLen];//
	        items[(itemLen+front)%itemLen] = null;//
	        front++;
	        numItems--;
	        return res;
    	}else {
    		throw new QueueException("QueueException on dequeue");
    	}
    }

    /**
     * Clear out, empty, dequeue all items in the queue
     * reset the queue
     */
    @Override
    public void dequeueAll() {
        items = (T[]) new Object[1];
        numItems = 0;
        front = 0;
        back = 0;
    }

    /**
     * Look at front (next) item in queue
     *
     * @return front item in queue
     */
    @Override
    public T peek() throws QueueException {
      if(!isEmpty()){
        return items[(items.length+front)%items.length];
      }else{
    	  throw new QueueException("Queue exception on peek");
      }
    }

}
