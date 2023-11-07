package sorterapp.model;
/* 
* Program: BubbleSorter.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
* Extension of the 'Sorter' abstract class using the bubble sort algorithm
*/
//import java.util.*;
public class BubbleSorter extends Sorter
{

    public BubbleSorter(int[] array)
    {
        super(array);
        name = "Bubble Sort";
        info = """
        Bubble sort is a simple sorting algorithm that repeatedly steps through the input list element 
        by element, comparing the current element with the one after it, swapping their values if needed. 
        These passes through the list are repeated until no swaps had to be performed during a pass,
        meaning that the list has become fully sorted.   

        Time Complexity: Worst Case: O(n^2) Average Case: O(n*logn) Best case: O(n*logn)""";    
    }
    
    public void sort()
    {
        notifyObservers(); // Event occurred (START)
        int n = sortedArr.length;
        int tmp = 0;
        for(int x=0; x < n; x++){
            for(int y=1; y < (n-x); y++){
                if(sortedArr[y-1] > sortedArr[y]){
                    tmp = sortedArr[y-1];
                    sortedArr[y-1] = sortedArr[y];
                    sortedArr[y] = tmp;
                    setCurrent(y);
                    notifyObservers(); // Event occurred (SWAP)
                    incrementSwaps();
                }
            }
        }
        resetSortedArray(); // Reset the array, to allow re-runs
        setFinished(); // toggle the done classfield to true
    }
}

