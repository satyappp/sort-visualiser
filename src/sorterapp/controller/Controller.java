package sorterapp.controller;
/* 
* Program: controller.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
 * The controller of the sorter application's MVC architecture. The responsibilities of this 
 * class are light; it acts like a wrapper class, a thin layer of separation between the View and the Model and
 * facilitating the flow of information and event notification while ensuring separation of concerns.
 */
import sorterapp.model.*;
import java.util.*;

public class Controller
{
    private SorterList list;
    //private JPanel visualiser;

    public Controller(SorterList inList, int[] array)
    {
        list = inList;
        // Add sorters to the list to house the algorithms (Strategy Patter Expandability)
        list.addSorter(new BubbleSorter(array));
        list.addSorter(new InsertionSorter(array.clone()));
        list.addSorter(new SelectionSorter(array.clone()));
    }
    
    /** Used by the UI to obtain sorters to display. */
    public List<Sorter> getSorters()
    {
        return list.getSorters();
    }

    // Iterative wrapper for observer pattern's subject
    public void addObserver(SorterObserver inObs)
    {
        for (Sorter sorter : getSorters()) 
        {
            sorter.addObserver(inObs);
        }
    }
    // Wrapper for observer pattern's subject
    public void setArray(int[] array)
    {
        list.setArray(array);
    }
}

