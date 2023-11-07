package sorterapp.model;
/* 
* Program: InsertionSorter.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
* Extension of the 'Sorter' abstract class using the insertion sort algorithm
*/

public class InsertionSorter extends Sorter
{
    public InsertionSorter(int[] array)
    {
        super(array);
        name = "Insertion Sort";
        info = """
        Insertion sort is a simple sorting algorithm that builds the final sorted array (or list) one
        item at a time by comparisons. Insertion sort iterates, consuming one input element each 
        repetition, and grows a sorted output list. At each iteration, insertion sort removes one 
        element from the input data, finds the location it belongs within the sorted list, and inserts
        it there. It repeats until no input elements remain.

        Time Complexity: Worst Case: O(n*n) Average Case: O(n*logn) Best case: O(n*logn)""";
    }

    public void sort()
    {
        for (int x = 1; x < sortedArr.length; x++) {
            int current = sortedArr[x];
            int y = x - 1;
            while(y >= 0 && current < sortedArr[y]) {
                sortedArr[y+1] = sortedArr[y];
                y--;
                notifyObservers(); // Event occurred (SWAP)
                incrementSwaps();
            }
        sortedArr[y+1] = current;
        currIdx = current;
        }
        resetSortedArray(); // Reset the array, to allow re-runs
        setFinished(); // toggle the done classfield to true
    }
}

