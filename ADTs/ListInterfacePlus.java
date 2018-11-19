package ADTs;

import java.util.Iterator;
import java.util.ListIterator;
/**
 * Purpose: Data Structure and Algorithms Lab 4 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 10/1/18
 * Submitted:  10/2/18
 * Comment: List interface to be implemented, FOR Extra Credit
 * @author: Matthew Schofield, Dr. H
 * @version: 2018.10.2
 */
// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterfacePlus
{
  boolean isEmpty();
  int size();
  void add(int index, Object item) 
                  throws ListIndexOutOfBoundsException;
  Object get(int index) 
                    throws ListIndexOutOfBoundsException;
  void remove(int index) 
                     throws ListIndexOutOfBoundsException;
  void removeAll();
  // Added for Extra Credit
  Iterator iterator();
  ListIterator listIterator();
  ListIterator listIterator(int index);
}  // end ListInterfacePlus