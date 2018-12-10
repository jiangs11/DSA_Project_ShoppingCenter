package ADTs.List;

/**
 * Purpose: Data Structure and Algorithms Midterm
 * Status: Complete and thoroughly tested
 * Last update: 10/14/18
 * Submitted:  10/14/18
 * Comment: test suite and sample run attached
 * @author: Matthew Schofield, Dr. H
 * @version: 2018.10.14
 */
// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterface<T>
{
    boolean isEmpty();
    int size();
    void add(int index, T item)
    throws ListIndexOutOfBoundsException;
    T get(int index)
    throws ListIndexOutOfBoundsException;
    void remove(int index)
    throws ListIndexOutOfBoundsException;
    void removeAll();
}  // end ListInterface