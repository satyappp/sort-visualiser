package sorterapp.model;
/* 
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Program: Sorter.java
* Description:
* Interface for the Observer pattern. Implemented by concrete observers, which
* are used to facilitate communication between the model and view in MVC. 
* Event propagation is achieved through calling the oberver's notify function.
*/

public interface SorterObserver
{
    void update(int[] array);
}


