import java.io.*;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import java.util.ArrayList;
import java.util.List;

class test {

    static int operation = 0;
    static int limit = 142301;

    public static void main(String[] args) {
        List<String> words = readFileIntoArray();
        String[] arr = new String[142301];
        arr = words.toArray(arr);

        quickSort(arr, 0, 142300);
        //radixSort(arr, '\'', 'z');
        // System.out.print("\nAfter sort: ");
        // for (int i = 0; i < arr.length; i++) {
        // System.out.println("A---" + arr[i] + ", ");
        // }
        System.out.println("Operation: " + operation);
        // int x = 'z' - 96;
        // System.out.println(x);
    }

    static List<String> readFileIntoArray() {
        List<String> words = new ArrayList<String>();
        int count = 0;
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream("wordList.txt");
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine() && count < limit) {
                count++;
                words.add(sc.nextLine());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    static void insertionSort(String[] arr) {
        operation = 0;
        int n = arr.length; // assign, function call, arithmetic op, access array, comparison
        operation += 3;

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

    static void bubbleSort(String[] arr) {

        operation = 0;
        int n = arr.length;
        String temp = arr[0];
        operation += 4;

        for (int i = 0; i < n; i++) {
            operation += 1;
            for (int j = 1; j < (n - i); j++) {
                operation += 5;
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    // swap elements
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

    static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        operation += 7;
    }

    /*
     * This function takes last element as pivot, places
     * the pivot element at its correct position in sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right
     * of pivot
     */
    static int partition(String[] arr, int low, int high) {

        // last index as pivot
        String pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        operation += 5;

        for (int j = low; j < high; j++) {

            // If current element is smaller
            // than the pivot
            operation += 2;

            if (arr[j].compareTo(pivot) < 0) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
                operation += 3;
            }
        }
        swap(arr, i + 1, high);
        operation += 7;
        return (i + 1);
    }

    /*
     * The main function that implements QuickSort
     * arr[] --> Array to be sorted,
     * low --> Starting index,
     * high --> Ending index
     */
    static void quickSort(String[] arr, int low, int high) {
        operation+=1;
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);
            
            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            operation+=6;
        }
    }

    // static void countingSort(int a[],int n,int div){
    // // Making a count array of size 10, for counting
    // // the frequency of digits of array elements.
    // int count[]=new int[10];

    // // Initializing all of its
    // // entries with 0.
    // for(int i=0;i<10;i++)
    // count[i]=0;

    // // Increasing count of kth
    // // digit of a[i].
    // for(int i=0;i<n;i++)
    // count[(a[i]/div)%10]++;

    // // Updating count[i] such that count[i] now contains
    // // actual position of this digit in temp[].
    // //cumulative frequency
    // for(int i=1;i<10;i++)
    // count[i]+=count[i-1];

    // // Making a temporary array for
    // // storing the output.
    // int temp[]=new int[n];

    // // Building the temporary array.
    // for(int i=n-1;i>-1;i--){
    // temp[count[(a[i]/div)%10]-1]=a[i];
    // count[(a[i]/div)%10]--;
    // }

    // // Updating the elements in array.
    // for(int i=0;i<n;i++)
    // a[i]=temp[i];
    // }

    // // Radix Sort function
    // static void radixSort(int a[], int n){
    // // Finding the maximum element
    // // of the array.
    // int max=0;
    // for(int i=0;i<n;i++)
    // if(a[i]>max)
    // max=a[i];

    // // For each digit in max, calling
    // // the countingSort for ith digit.
    // for(int div=1;max/div>0;div*=10){
    // countingSort(a,n,div);
    // }
    // }

    public static void countingSort(String[] arr, int index, char lower, char upper) {
        int[] countArray = new int[(upper - lower) + 2];
        String[] tempArray = new String[arr.length];
        operation+=1;
        for(int i=0; i<countArray.length; i++){
            countArray[i]=0;
            operation+=5;
        }

        operation+=1;
        // increase count for char at index
        for (int i = 0; i < arr.length; i++) {
            operation+=4;
            int charIndex=0;
            if(arr[i].length() - 1 >= index){
                charIndex = ((arr[i].charAt(index) - lower) + 1);
                operation+=5;
            }
            countArray[charIndex]++;
            operation+=7;
        }

        // sum up countArray;countArray will hold last index for the char at each
        // strings index
        operation+=1;
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];

            operation+=8;
        }

        operation+=3;
        for (int i = arr.length - 1; i >= 0; i--) {
            int charIndex=0;
            operation+=4;
            if(arr[i].length() - 1 >= index){
                charIndex = (arr[i].charAt(index) - lower) + 1;
                operation+=5;
            }
            tempArray[countArray[charIndex] - 1] = arr[i];
            countArray[charIndex]--;


            operation+=12;
        }

        operation+=1;
        for (int i = 0; i < tempArray.length; i++) {
            arr[i] = tempArray[i];
            operation+=6;
        }
    }

    public static void radixSort(String[] arr, char lower, char upper) {
        operation=0;
        int maxIndex = 0;

        operation+=2;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() - 1 > maxIndex) {
                maxIndex = arr[i].length() - 1;
            }

            operation+=3;
        }

        System.out.print(maxIndex);

        for (int i = maxIndex; i >= 0; i--) {
            countingSort(arr, i, lower, upper);
        }
    }

}