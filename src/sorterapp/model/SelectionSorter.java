package sorterapp.model;
/* 
* Program: SelectionSorter.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
* Extension of the 'Sorter' abstract class using the selection sort algorithm
*/

public class SelectionSorter extends Sorter
{

    public SelectionSorter(int[] array)
    {
        super(array);
        name = "Selection Sort";
        info = """
        Selection sort is an in-place comparison sorting algorithm which algorithm divides the
        input list into two parts: a sorted sublist of items which is built up from left to right at the
        front (left) of the list and a sublist of the remaining unsorted items that occupy the rest of
        the list. Initially, the sorted sublist is empty and the unsorted sublist is the entire input list. 
        The algorithm proceeds by finding the smallest (or largest, depending on sorting order) 
        element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted 
        element (putting it in sorted order), and moving the sublist boundaries one element to the right.

       Time Complexity: Worst Case: O(n*n) Average Case: O(n*logn) Best case: O(n*logn)""";
    }
    
    public void sort()
    {
        for (int x = 0; x < sortedArr.length - 1; x++){
            int index = x;
            for (int y = x + 1; y < sortedArr.length; y++){
                if (sortedArr[y] < sortedArr[index]){
                    index = y;
                    setCurrent(y);
                    notifyObservers(); // Event occurred
                }
            }
            int smallNumber = sortedArr[index];
            sortedArr[index] = sortedArr[x];
            sortedArr[x] = smallNumber;
            notifyObservers(); // Event occurred
        }
        resetSortedArray(); // Reset the array, to allow re-runs
        setFinished(); // toggle the done classfield to true
    }
}

