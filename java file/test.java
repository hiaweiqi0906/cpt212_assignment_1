import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class test {

    static int operation=0;
    public static void main(String[] args) {
        List<String> words = readFileIntoArray();
        String[] arr = new String[words.size()];
        arr = words.toArray(arr);
        // System.out.println(words.size());
        // String[] arr = { "test", "tesla", "thanks", "apple", "zoo", "queen", "haha" };
        // String[] arr = words.toArray();
        // System.out.print("Before sort: ");
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println("B---"+arr[i] + ", ");
        // }

        bubbleSort(arr);
        System.out.print("\nAfter sort: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("A---"+arr[i] + ", ");
        }
        System.out.println("Operation: "+operation);
    }
    
    static List<String> readFileIntoArray() {
        List<String> words = new ArrayList<String>();
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream("wordList.txt");
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                words.add(sc.nextLine());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    static void insertionSort(String[] arr) {
        operation=0;
        int n = arr.length;
        operation+=3;

        for (int j = 1; j < n; j++) {
            String key = arr[j];
            int i = j - 1;

            operation+=4;
            
            while ((i > -1) && arr[i].compareTo(key)>0) {
                arr[i + 1] = arr[i];
                i--;
                operation+=11;
            }
            arr[i + 1] = key;
            
            operation+=6;
        }
    }

    static void bubbleSort(String[] arr) {
        operation=0;
        int n = arr.length;
        String temp = arr[0];
        operation+=4;

        for (int i = 0; i < n; i++) {
            operation+=1;
            for (int j = 1; j < (n - i); j++) {
                operation+=5;
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    // swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;

                    operation+=9;
                }

                operation+=4;
            }

            operation+=3;
        }
    }
}