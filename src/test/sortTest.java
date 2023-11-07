package test;
import static org.junit.Assert.*;
import org.junit.Test;

import sorterapp.model.BubbleSorter;
import sorterapp.model.InsertionSorter;
import sorterapp.model.SelectionSorter;
import static org.junit.Assert.assertArrayEquals;

public class sortTest {

  private int[] arr = {5, 4, 3, 2, 1};
  private int[] expected = {1, 2, 3, 4, 5};
  
  @Test
  public void testInsertionSort() {
    int[] actual = arr.clone();
    InsertionSorter insert = new InsertionSorter(actual);
    insert.sort();
    assertArrayEquals(expected, insert.getSortedArr());
  }

  @Test
  public void testSelectionSort() {
    int[] actual = arr.clone();
    SelectionSorter selection = new SelectionSorter(actual);
    selection.sort();
    assertArrayEquals(expected, selection.getSortedArr());
  }

  @Test
  public void testBubbleSort() {
    int[] actual = arr.clone();
    BubbleSorter bubble = new BubbleSorter(actual);
    bubble.sort();
    assertArrayEquals(expected, bubble.getSortedArr());
  }
  
  @Test
  public void testSortWithEmptyArray() {
    int[] emptyArray = new int[0];
    InsertionSorter insert = new InsertionSorter(emptyArray);
    insert.sort();
    assertArrayEquals(new int[0],insert.getSortedArr());
    
    emptyArray = new int[0];
    SelectionSorter selection = new SelectionSorter(emptyArray);
    selection.sort();
    assertArrayEquals(new int[0], selection.getSortedArr());
    
    emptyArray = new int[0];
    BubbleSorter bubble = new BubbleSorter(emptyArray);
    bubble.sort();
    assertArrayEquals(new int[0], bubble.getSortedArr());
  }

  @Test
  public void testSortWithOneElementArray() {
    int[] oneElementArray = {1};
    InsertionSorter insert = new InsertionSorter(oneElementArray);
    insert.sort();
    assertArrayEquals(new int[]{1}, insert.getSortedArr());
    
    oneElementArray = new int[]{1};
    SelectionSorter selection = new SelectionSorter(oneElementArray);
    selection.sort();
    assertArrayEquals(new int[]{1}, selection.getSortedArr());
    
    oneElementArray = new int[]{1};
    BubbleSorter bubble = new BubbleSorter(oneElementArray);
    bubble.sort();
    assertArrayEquals(new int[]{1}, bubble.getSortedArr());
  }

  @Test(expected = NullPointerException.class)
  public void testMergeSortWithNullArray() {
   InsertionSorter insert = new InsertionSorter(null);
   insert.sort();
  }

  @Test(expected = NullPointerException.class)
  public void testSelectionSortWithNullArray() {
   SelectionSorter selection = new SelectionSorter(null);
   selection.sort();
  }

  @Test(expected = NullPointerException.class)
  public void testBubbleSortWithNullArray() {
   BubbleSorter bubble = new BubbleSorter(null);
   bubble.sort();
  }
}
