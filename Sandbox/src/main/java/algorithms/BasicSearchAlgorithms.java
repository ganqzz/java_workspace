package algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class BasicSearchAlgorithms {

    // Linear Search
    public static int linearSearch(int[] arr, int key) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) return i; // 最初に見つかった時点で抜ける。
        }
        return -1;
    }

    // Binary Search
    public static int binarySearch(int[] arr, int key) {
        int first = 0;
        int last = arr.length - 1;
        while (first <= last) {
            int mid = (first + last) / 2;
            if (key > arr[mid]) {
                first = mid + 1;
            } else if (key < arr[mid]) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(i % 10 == 9 ? "\n" : "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final int size = 20;
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        Arrays.sort(numbers);
        display(numbers);

        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a number to search for: ");
        int num = input.nextInt();

        int found = binarySearch(numbers, num);
        if (found >= 0) {
            System.out.println("Found key at position " + found);
        } else {
            System.out.println("Key not found.");
        }

        found = linearSearch(numbers, num);
        if (found >= 0) {
            System.out.println("Found key at position " + found);
        } else {
            System.out.println("Key not found.");
        }
    }
}
