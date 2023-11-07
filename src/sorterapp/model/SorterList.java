package sorterapp.model;
/* 
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Program: Sorter.java
* Description:
* Class housing a set of sorters, acting as the Model for the MVC
* architecture. It represents a collection of Sorters which are used by the 
* View through the Controller. 
*/
import java.util.*;

public class SorterList
{
    private List<Sorter> sorters;

    public SorterList()
    {
        sorters = new ArrayList<Sorter>();
    }
    
    /** Add a sorter to the list. */
    public void addSorter(Sorter inSorter)
    {
        sorters.add(inSorter);
    }

    /** Retrieve a copy of the sorters list. */
    public List<Sorter> getSorters()
    {
        // returns an unmodifiable Set so that changes are only made using appropriate functions
        return Collections.unmodifiableList(sorters);
    }

    // Sets the array field for each of the sorters in the list (to allow array type selection)
    public void setArray(int[] array)
    {
        for (Sorter sorter : sorters) {
            sorter.setArray(array); // pass the array to observers
        }
    }
    

    
}


