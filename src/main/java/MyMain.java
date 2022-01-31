import java.util.ArrayList;
import java.util.Arrays;

public class MyMain {
    // Note: Most of all the code you need to write in class today goes into
    // the main method (besides the warm-up problem)

    // ************
    // Warm-up problem: create random array
    // ************

    // Creates an array of random numbers, given some random number x
    // For example, if x == 10, it should make an array of 10 random
    // numbers between 0 and (10 - 1).

    // Examples:
    // makeRandomArray(5) => [0, 4, 2, 3, 1]
    // makeRandomArray(5) => [0, 2, 2, 1, 3]
    // makeRandomArray(1) => [0]
    public static int[] makeRandomArray(int x) {
        // YOUR CODE HERE
        return new int[0];
    }

    // *********
    // These methods all work already
    // *********

    // Duplicates an array
    public static int[] copyArray(int[] arr) {
        int[] arr2 = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }

        return arr2;
    }

    // Linear Search
    public static boolean linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return true;
            }
        }
        return false;
    }

    /// Wrapper Method
    public static boolean binarySearch(int[] arr, int num) {
        return binarySearchTR(arr, num, 0, arr.length-1);
    }

    // Tail recursive method
    public static boolean binarySearchTR(int[] arr, int num, int lowerBound, int upperBound) {

        int middleIndex = lowerBound + (upperBound - lowerBound)/2;

        if (lowerBound > upperBound) {
            return false;
        }
        // left half
        else if (arr[middleIndex] > num) {
            return binarySearchTR(arr, num, lowerBound, middleIndex-1);
        }
        // right half
        else if (arr[middleIndex] < num) {
            return binarySearchTR(arr, num, middleIndex+1, upperBound);
        }
        else {
            return true;
        }
    }

    // Insertion Sort
    public static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];

            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = current;
        }
    }

    // Merge method
    public static int[] merge(int[] arr1, int[] arr2) {
        // Indices to keep track of location in input and output arrays
        int idx1 = 0;
        int idx2 = 0;
        int outputIdx = 0;

        int[] output = new int[arr1.length + arr2.length];

        // While we have two values left to compare...
        // * Copy the smaller value into the output array
        // * Increment the appropriate indexes
        while (idx1 < arr1.length && idx2 < arr2.length) {
            if (arr2[idx2] < arr1[idx1]) {
                output[outputIdx] = arr2[idx2];
                idx2++;
            }
            else {
                output[outputIdx] = arr1[idx1];
                idx1++;
            }
            outputIdx++;
        }

        // Copy rest of array A if array arr2 finished first
        while (idx1 < arr1.length) {
            output[outputIdx] = arr1[idx1];
            idx1++;
            outputIdx++;
        }

        // Copy rest of array arr2 if array A finished first
        while (idx2 < arr2.length) {
            output[outputIdx] = arr2[idx2];
            idx2++;
            outputIdx++;
        }

        return output;
    }

    // Subarray
    public static int[] subArray(int[] arr, int begin, int end) {
        int[] output = new int[end-begin];

        for (int i = 0; i < output.length; i++) {
            output[i] = arr[i + begin];
        }

        return output;
    }

    // Carries out merge sort!
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        else {
            // Split it in halves
            int[] left = subArray(arr, 0, arr.length/2);
            int[] right = subArray(arr, arr.length/2, arr.length);

            // Sort each half
            left = mergeSort(left);
            right = mergeSort(right);

            // Merge the two sorted halves
            return merge(left, right);
        }
    }

    // *********
    // Main method: write timing code here!
    // *********

    public static void main(String[] args) {
        // A single trial of linear search vs. binary search
        int[] arr1 = makeRandomArray(10);
        arr1 = mergeSort(arr1);
        int[] arr2 = copyArray(arr1);

        long start1 = System.nanoTime();
        linearSearch(arr1, -1);
        long end1 = System.nanoTime();

        System.out.println("Linear search: " + (end1-start1)  + " ns");

        long start2 = System.nanoTime();
        binarySearch(arr2, -1);
        long end2 = System.nanoTime();

        System.out.println("Binary search: " + (end2-start2)  + " ns");

        // Running Multiple Trials
        long linearTotal = 0;
        long binaryTotal = 0;

        int numTrials = 10000;

        for (int i = 0; i < numTrials; i++) {
            // A single trial of linear search vs. binary search
            int[] arr1Trial = makeRandomArray(1000);
            arr1Trial = mergeSort(arr1Trial);
            int[] arr2Trial = copyArray(arr1Trial);

            long start1Trial = System.nanoTime();
            linearSearch(arr1Trial, -1);
            long end1Trial = System.nanoTime();

            linearTotal += (end1Trial-start1Trial);

            //System.out.println("Linear search: " + (end1Trial-start1Trial)  + " ns");

            long start2Trial = System.nanoTime();
            binarySearch(arr2Trial, -1);
            long end2Trial = System.nanoTime();

            binaryTotal += (end2Trial-start2Trial);

            //System.out.println("Binary search: " + (end2Trial-start2Trial)  + " ns");
        }

        System.out.println("Linear search: " + (linearTotal / numTrials)  + " ns on average");
        System.out.println("Binary search: " + (binaryTotal / numTrials)  + " ns on average");
    }
}
