package sorterapp.model;

import java.util.Random;

public class Generator {

    //creates an array with random numbers 
    public static int[] random_generator(int N) {
        Random rd = new Random();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
           arr[i] = rd.nextInt(N); 
        }
        return arr;
    }

    //creates an array with unique elements in the range 1 to n and then shuffles the values
    public static int[] shuffle_generator(int N) {
        Random rd = new Random();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
           arr[i] = i+1; 
        }
        		
		for (int i = 0; i < arr.length; i++) {
			int randomIndexToSwap = rd.nextInt(arr.length);
			int temp = arr[randomIndexToSwap];
			arr[randomIndexToSwap] = arr[i];
			arr[i] = temp;
		}
    return arr;
    }
    
    //creates an array with descending elements
    public static int[] descending_generator(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = N-i;
        }
        return arr;
}

}
