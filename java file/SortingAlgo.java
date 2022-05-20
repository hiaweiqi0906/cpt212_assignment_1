import java.io.*;
import java.util.Scanner;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import java.util.ArrayList;
import java.util.List;

class SortingAlgo {

    static long operation = 0;
    static int limit = 142301;
    static int comparison = 0;
    static int swap = 0;

    // Store filename for best, worst and average case for comparative sorting algo
    static String filename[] = { "wordList.txt", "ascendingList.txt", "descendingList.txt" };

    public static void main(String[] args) {

        // Loop for 3 times to determine best, worst and average case for different sorting algo
        for (int countI = 0; countI < 3; countI++) {

            // Each loop will increase 10k words to sort
            for (int num = 10000; num <= 140000; num += 10000) {

                // Read all words in file, store to array
                List<String> words = readFileIntoArray(num, filename[countI]);
                String[] arr = new String[num];
                arr = words.toArray(arr);

                // Init operation=0 at each 10k words added
                operation = 0;

                // Uncomment different line of code below for different sorting algo
                bubbleSort(arr);
                // insertionSort(arr);
                // quickSort(arr, 0, num - 1);
                // radixSort(arr, '\'', 'z');

                System.out.println("n = " + num + ", Operation: " + operation);

            }
            System.out.println("--------------------------------------------\n");

        }

    }

    /**
     * To write all sorted strings in lexicographic order to files
     * 
     * Arguments:
     *     arr: sorted array to write
     * 
     * Return:
     *     -
     */
    static void writeToFile(String[] arr) throws Exception {
        FileWriter writer = new FileWriter(
                "D:\\Assignment\\CPT212 YEAR 2 SEM 2\\CPT212_Assignment_1\\java file\\descendingList.txt");
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            writer.write(arr[i] + "\n");
        }
        writer.close();
    }

    /**
     * Read all strings in file, store to array
     * 
     * Arguments:
     *     arrLimit: max number of strings to read
     *     dest: file destination to be read
     * 
     * Return: 
     *     words: array of words stored from file
     */
    static List<String> readFileIntoArray(int arrLimit, String dest) {
        List<String> words = new ArrayList<String>();
        int count = 0;
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(dest);
            Scanner sc = new Scanner(fis); // file to be scanned

            // returns true if there is another line to read && not exceed arrLimit
            while (sc.hasNextLine() && count < arrLimit) {
                count++;
                words.add(sc.nextLine());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * Perform bubble sort
     * 
     * Arguments:
     *     arr: arr to be sorted
     *      
     * Return: 
     *     -
     */
    static void bubbleSort(String[] arr) {

        // Init all required var for bubble sort
        operation = 0;
        int n = arr.length;
        String temp = arr[0];
        operation += 4; 

        // Perform bubble sort
        for (int i = 0; i < n; i++) {
            operation += 1;
            for (int j = 1; j < (n - i); j++) {
                operation += 5;
                if (arr[j - 1].compareTo(arr[j]) > 0) {

                    // swap elements if not in correct order
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;

                    operation += 9;
                }
                operation += 4;
            }
            operation += 3;
        }
    }

    /**
     * Perform insertion sort
     * 
     * Arguments:
     *     arr: arr to be sorted
     *      
     * Return: 
     *     -
     */
    static void insertionSort(String[] arr) {
        operation = 0;
        int n = arr.length;
        operation += 3;

        // Perform insertion sort
        for (int j = 1; j < n; j++) {
            String key = arr[j];
            int i = j - 1;

            operation += 4;

            while ((i > -1) && arr[i].compareTo(key) > 0) {
                arr[i + 1] = arr[i];
                i--;
                operation += 11;
            }
            arr[i + 1] = key;

            operation += 6;
        }
    }

    /**
     * Perform swapping for two elements in an arr
     * 
     * Arguments:
     *     arr: arr to perform swapping
     *     i, j: indices of elements to be swapped
     *      
     * Return: 
     *     -
     */
    static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        operation += 7;
    }

    /**
     * Perform partition for quick sort
     * 
     * Arguments:
     *     arr: arr to be partitioned
     *     low: starting index to be partitioned
     *     high: ending index to be partitioned
     *      
     * Return: 
     *     i+1: the postion of the pivot in the arr
     */
    static int partition(String[] arr, int low, int high) {

        // Different ways to init pivot, uncomment each for different cases

        // 1. Random pivot for avg case
        Random rand = new Random();
        int int_random = rand.nextInt(high); // index of pivot

        // 2. Middle pivot for best case
        /*
        int_random = (high+low)/2;
        */
        
        // 3. Right pivot for worst case(for sorted arr)
        /*
        int_random = high-1;
        */

        String pivot = arr[int_random];
        String temp = "";
        
        int i = (low - 1);

        operation += 5;

        // Perform swapping and decide where to partition 
        for (int j = low; j < high; j++) {

            // If current element is smaller than the pivot
            operation += 3;
            comparison++;
            if (arr[j].compareTo(pivot) < 0) {
                // Increment index of smaller element
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swap++;
                operation += 10;
            }
        }
        // swap(arr, i + 1, high);
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swap++;
        operation += 16;
        return (i + 1);
    }

    /**
     * Perform quick sort
     * 
     * Arguments:
     *     arr: arr to be partitioned
     *     low: starting index to be partitioned
     *     high: ending index to be partitioned
     *      
     * Return: 
     *     i+1: the postion of the pivot in the arr
     */
    static void quickSort(String[] arr, int low, int high) {
        operation += 1;
        if (low < high) {

            // pi is partitioning index, arr[p] is now at right place
            try {
                int pi = partition(arr, low, high);

                // Separately sort elements before partition and after partition
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
                operation += 6;
            } catch (Exception e) {
                System.out.println("Here gt an error");
            }
        }
    }

    /**
     * Perform bucket sort for radix sort
     * 
     * Arguments:
     *     arr: arr to be sorted
     *     index: index of strings to be sorted
     *     lower: smallest char in arr 
     *     upper: largest char in arr 
     *      
     * Return: 
     *     -
     */
    public static void countingSort(String[] arr, int index, char lower, char upper) {
        // Array to store occurence of all char
        int[] countArray = new int[(upper - lower) + 2]; 
        String[] tempArray = new String[arr.length];
        operation += 1;

        // Set all countArray element = 0
        for (int i = 0; i < countArray.length; i++) {
            countArray[i] = 0;
            operation += 5;
        }

        operation += 1;

        // Increase count for char at index
        for (int i = 0; i < arr.length; i++) {
            operation += 4;
            int charIndex = 0;
            if (arr[i].length() - 1 >= index && arr[i].charAt(index) <= 122) {
                charIndex = ((arr[i].charAt(index) - lower) + 1);
                operation += 5;
            }
            countArray[charIndex]++;
            operation += 7;
        }

        // Sum up countArray;countArray will hold last index for the char at eachstrings index
        operation += 1;
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];

            operation += 8;
        }

        operation += 3;
        for (int i = arr.length - 1; i >= 0; i--) {
            int charIndex = 0;
            operation += 4;
            if (arr[i].length() - 1 >= index && arr[i].charAt(index) <= 122) {
                charIndex = (arr[i].charAt(index) - lower) + 1;
                operation += 5;
            }
            tempArray[countArray[charIndex] - 1] = arr[i];
            countArray[charIndex]--;

            operation += 12;
        }

        operation += 1;
        for (int i = 0; i < tempArray.length; i++) {
            arr[i] = tempArray[i];
            operation += 6;
        }
    }

    /**
     * Perform radix sort
     * 
     * Arguments:
     *     arr: arr to be sorted
     *     lower: smallest char in arr 
     *     upper: largest char in arr 
     *      
     * Return: 
     *     -
     */
    public static void radixSort(String[] arr, char lower, char upper) {
        int maxIndex = 0;

        operation += 2;
        // Search for length of longest string
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() - 1 > maxIndex) {
                maxIndex = arr[i].length() - 1;
            }

            operation += 3;
        }

        // Perform bucket sort for radix sort, increase index each iteration
        for (int i = maxIndex; i >= 0; i--) {
            countingSort(arr, i, lower, upper);
        }
    }

}