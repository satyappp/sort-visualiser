package sorterapp.model;
/* 
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Program: Sorter.java
* Description:
* Abstract class used as the basis for the MVC architectural pattern's Model.
* To be extended by implementation of various algorithms for sorting 1D
* int arrays for use in the larger CUI/GUI based sorting software.
*/
import java.util.*;

public abstract class Sorter
{
    protected String name;
    protected String info;
    protected int[] unSortedArr;
    protected int[] sortedArr;
    protected ArrayList<SorterObserver> observers;
    protected boolean done;
    protected int currIdx;
    protected int swaps;

    public Sorter(int[] array)
    {
        unSortedArr = array;
        sortedArr = array.clone();
        done = false;
        currIdx = 0;
        observers = new ArrayList<SorterObserver>();
    }

    // Basic accessors
    public String getName(){ return name; }
    public String getInfo() {return info;}
    public int[] getUnsortedArr()  { return unSortedArr; }
    public int[] getSortedArr() { return sortedArr; }
    public boolean getDone()    { return done; }
    public int getCurrent() {return currIdx;}
    public int getSwaps() { return swaps; }
    
    /* Abstract method to be implemented by the child classes which extend Sorter */
    public abstract void sort();

    // Basics mutators
    public void setArray(int[] array)  
    {
        unSortedArr = array;
        sortedArr = array.clone(); // both arrays are set to the new input array to
    }
    public void setFinished()
    {
        done = true;
    }
    public void resetDone()
    {
        done = false;
    }
    public void resetSortedArray()
    { 
        sortedArr = unSortedArr.clone();
    }
    public void setCurrent(int i)
    {
        currIdx = i;
    }

    public void incrementSwaps()
    {
        swaps++;
    }

    // Observer pattern
    public void addObserver(SorterObserver inObs)
    {
        observers.add(inObs);
    }
    public void notifyObservers()
    {
        for (SorterObserver sorterObserver : observers) {
            sorterObserver.update(sortedArr); // pass the array to observers
            // (sorted array is initially unsorted)
        }
    }
    
}

