import java.util.Arrays;
import java.util.Scanner;

public class CountingSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < 10; i++) {
            int num = -1; // initialize num to an invalid value
            while (num < 0 || num > 15) {
                System.out.println("Add element # " + (i + 1));
                num = sc.nextInt();
                if (num < 0 || num > 15) {
                    System.out.println("Invalid input. Please enter a number between 0 and 15.");
                }
            }
            arr[i] = num;
        }

        // not limiting the user input elements
        // for (int i = 0; i < 10; i++) {
        // System.out.println("Add element # " + (i + 1));
        // arr[i] = sc.nextInt();
        // }

        System.out.println("Original array: " + Arrays.toString(arr));
        countingSort(arr);

        System.out.println();
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        int[] output = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++; // increment the count of the current element in the count array
        }
        System.out.println();

        System.out.println("Count the number occurrences of each element of the array");
        System.out.println("Count array after step 1: " + Arrays.toString(count)); // print the count array after the
                                                                                   // first step
        System.out.print("                   Index: ");
        for (int i = 0; i <= max; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1]; // update each element in the count array by adding the previous element
        }

        System.out.println();
        System.out.println("Update each element in the count array by adding the previous element");
        System.out.println("Count array after step 2: " + Arrays.toString(count)); // print the count array after the
                                                                                   // second step
        System.out.print("                   Index:   ");
        for (int i = 0; i <= max; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i]; // place the current element in its correct position in the output array
            count[arr[i]]--; // decrement the count of the current element in the count array
        }

        System.out.println();
        System.out.println("Update each element of the count array by decrementing the count of current element");
        System.out.println("Count array after step 3: " + Arrays.toString(count)); // print the count array after the
                                                                                   // third step
        System.out.print("                   Index:   ");
        for (int i = 0; i <= max; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();

        System.out.println();
        System.out.println("Output array after step 3: " + Arrays.toString(output)); // print the output array after the
                                                                                     // third step

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i]; // copy the elements from the output array back to the original array
        }
    }

}
