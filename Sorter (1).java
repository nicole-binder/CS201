/**
 * Sorter.java
 * Jeff Ondich, 2 Feb 2005
 * Modified 24 Feb 2014
 * 
 * Sorter is a collection of static methods for sorting arrays of integers.
 *
 * The main program generates a random permutation of the integers 0,...,N-1,
 * sorts it using the requested algorithm, and prints the number of
 * milliseconds the sorting required.
 *
 * See printUsageStatement for a description of the command-line syntax.
 */

import java.lang.NumberFormatException;
import java.util.ArrayList;

public class Sorter {
    /**
     * Parsing and validating command-line arguments is a pain in the neck, so this
     * program illustrates one approach to isolating the command-line pain. Instead
     * of parsing the command line in main, we defer it to parseCommandLine, which
     * returns null (if there's a problem with the command line) or a SorterArguments
     * object containing the information from the command line in an easy-to-use form.
     */
    private static class SorterArguments {
        public String algorithm;
        public int N;
        public boolean shouldPrintVerbosely;
    }

    /**
     * Parse the command-line arguments.
     * @return null if there's a command-line error of some kind. Otherwise, return
     * and return a SorterArguments object containing convenient forms of the
     * information stored in the arguments.
     */
    private static SorterArguments parseCommandLine(String[] args) {
        SorterArguments sorterArguments = new SorterArguments();

        // Check command-line length.
        if (args.length < 2 || args.length > 3) {
            return null;
        }

        // Parse the --verbose flag.
        sorterArguments.shouldPrintVerbosely = false;
        if (args.length == 3) {
           if (!args[2].equals("--verbose")) {
               return null;
           }
            sorterArguments.shouldPrintVerbosely = true;
        }

        // Parse the algorithm argument.
        sorterArguments.algorithm = args[0];
        if (!sorterArguments.algorithm.equals("selection")
                && !sorterArguments.algorithm.equals("insertion")
                && !sorterArguments.algorithm.equals("merge")
                && !sorterArguments.algorithm.equals("quick")
                && !sorterArguments.algorithm.equals("heap"))
        {
            return null;
        }

        // Parse the N argument.
        sorterArguments.N = 0;
        try {
            sorterArguments.N = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return null;
        }

        // Got here? Then the command line was valid, so we just return the collected arguments.
        return sorterArguments;
    }

    private static void printUsageStatement() {
        System.out.println("");
        System.out.println("Usage: java Sorter algorithm N [--verbose]");
        System.out.println("");
        System.out.println("   algorithm can be selection, insertion, merge, quick, shell, or heap");
        System.out.println("   N is an integer specifying the number of integers you want to shuffle and sort");
        System.out.println("   --verbose will cause the program to print the before and after arrays (don't use for large N)");
        System.out.println("");
    }

    public static void main(String[] args) {
        SorterArguments sorterArguments = parseCommandLine(args);
        if (sorterArguments == null) {
            printUsageStatement();
            return;
        }

        // Create a shuffled array of integers, print it if --verbose, time the
        // appropriate sorting algorithm, print the result if --verbose, and
        // finally, report the sorting time.
        int[] theIntegers = new int[sorterArguments.N];
        fillAndShuffle(theIntegers);

        if (sorterArguments.shouldPrintVerbosely) {
            printArray(theIntegers);
        }

        long startTime = System.currentTimeMillis();

        if (sorterArguments.algorithm.equals("selection")) {
            int count = selectionSort(theIntegers);
          	System.out.println("Comparison Count: " + count);
        } else if (sorterArguments.algorithm.equals("insertion")) {
            int count = insertionSort(theIntegers);
          	System.out.println("Comparison Count: " + count);
        } else if (sorterArguments.algorithm.equals("merge")) {
            int count = mergeSort(theIntegers);
          	System.out.println("Comparison Count: " + count);
        } else if (sorterArguments.algorithm.equals("quick")) {
           	int count = quickSort(theIntegers, 0, theIntegers.length - 1);
		System.out.println("Comparison Count: " + count);
        } else if (sorterArguments.algorithm.equals("heap")) {
            heapSort(theIntegers);
        }
        long endTime = System.currentTimeMillis();

        if (sorterArguments.shouldPrintVerbosely) {
            printArray(theIntegers);
        }

        System.out.format("%s for %d items: %d ms\n\n", sorterArguments.algorithm, sorterArguments.N, endTime - startTime);
    }

    /**
     * Generates a pseudo-random permutation of the integers from 0 to a.length - 1.
     * See p. 139 of "Seminumerical Algorithms, 2nd edition," by Donald Knuth.
     */
    public static void fillAndShuffle(int[] a) {
        // Fill the array with the integers from 0 to a.length - 1.
        int k;
        for (k = 0; k < a.length; k++) {
            a[k] = k;
        }

        // Shuffle.
        for (k = a.length - 1; k > 0; k--) {
            int swapIndex = (int)Math.floor(Math.random() * (k + 1));
            int temp = a[k];
            a[k] = a[swapIndex];
            a[swapIndex] = temp;
        }
    }

    /**
     * Prints the elements of the specified array, separated by spaces,
     * to standard output.
     */
    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Sorts the specified array in increasing order.
     */
    public static int insertionSort(int[] a) {
      	int compareCount = 0;
        for (int j = 1; j < a.length; j++) {
            int itemToInsert = a[j];
            int k;
          	compareCount++;
            for (k = j - 1; k >= 0 && itemToInsert < a[k]; k--) {
                a[k + 1] = a[k];
            }
            a[k + 1] = itemToInsert;
        }
      	return compareCount;
    }

    /**
     * Sorts the specified array in increasing order.
     */
    public static int selectionSort(int[] a) {
      	int comparisonCount = 0;
        for (int j = 0; j < a.length - 1; j++) {
            int indexOfMinimum = j;
            for (int k = j + 1; k < a.length; k++) {
              	comparisonCount++;
                if (a[k] < a[indexOfMinimum]) {
                    indexOfMinimum = k;
                }
            }

            int temp = a[indexOfMinimum];
            a[indexOfMinimum] = a[j];
            a[j] = temp;
        }
      	return comparisonCount;
    }

    /**
     * Sorts the specified array in increasing order.
     */
    public static void heapSort(int[] a) {
        assert (false) : "heapSort is not yet implemented.";
    }

    /**
     * Sorts the specified array in increasing order.
     * Quick sort code from http://www.algolist.net/Algorithms/Sorting/Quicksort
     */
 
    private static ArrayList<Integer> partition(int arr[], int left, int right) {
      int compareCount = 0;
      int i = left, j = right;
      int tmp;
      int pivot = arr[(left + right) / 2];
      
      while (i <= j) {
         compareCount++;
         while (arr[i] < pivot) {
                  i++;
            }
         compareCount++;
         while (arr[j] > pivot) {
                  j--;
                           }
            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
       }
      ArrayList<Integer> list = new ArrayList<Integer>();
      list.add(i);
      list.add(compareCount);
      return list;
 }
 
 public static int quickSort(int arr[], int left, int right) {
   ArrayList<Integer> list = partition(arr, left, right);
   int index = list.get(0);
     if (left < index - 1) {
             quickSort(arr, left, index - 1);
        }
        if (index < right) {
             quickSort(arr, index, right);
        }
     return list.get(1);
}


    /**
     * Sorts the specified array in increasing order.
     */
    public static int mergeSort(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int [] tempArray = new int[a.length];
        return mergeSort(a, tempArray, 0, a.length - 1);
    }

    /**
     * The recursive method that implements the mergesort algorithm. This
     * method has an ugly set of parameters, including that nasty tempArray
     * that has to be passed around so it's available when the merge
     * method needs it (to prevent merge from reallocating it over and over,
     * which could potentially screw up the N log N complexity of the algorithm).
     */
    private static int mergeSort(int[] a, int[] tempArray, int bottom, int top) {
      	int compareCount = 0;
      	int compare2 = 0;
        if (bottom < top) {
            int middle = (bottom + top) / 2;
            mergeSort(a, tempArray, bottom, middle);
            mergeSort(a, tempArray, middle + 1, top);
            compareCount = compareCount + merge(a, tempArray, bottom, middle, top);
        }
     	return compareCount;
    }

    /**
     * Merges two adjacent portions of a given array.
     * 
     * Preconditions:
     * (1) a[bottom],...,a[middle] are in increasing order
     * (2) a[middle+1],...,a[top] are in increasing order
     * (3) top > middle  and  middle >= bottom
     * 
     * Postconditions:
     * (1) a[bottom],...,a[top] compose the same set of
     *     numbers as before, but now in increasing order
     * (2) The rest of the array a[] is unchanged
     */
    private static int merge(int[] a, int[] tempArray, int bottom, int middle, int top) {
      	int compareCount = 0;
        int i = bottom;
        int j = middle + 1;
        int k = 0;

        // While both lists still have items in them, merge those
        // items into the temporary array temp[].
        while (i <= middle && j <= top) {
         	compareCount++;
            if (a[i] < a[j]) {
                tempArray[k] = a[i];
                i++;
            } else {
                tempArray[k] = a[j];
                j++;
            }
            k++;
        }
    
        // Once one of the lists has been exhausted, dump the other
        // list into the remainder of temp[].
        if (i > middle) {
            while (j <= top) {
                tempArray[k] = a[j];
                j++;
                k++;
            }
        } else {
            while (i <= middle) {
                tempArray[k] = a[i];
                i++;
                k++;
            }
        }

        // Copy tempArray[] into the proper portion of a[].
        for (i = bottom; i <= top; i++) {
            a[i] = tempArray[i - bottom];
        }
      	return compareCount;
    }
}