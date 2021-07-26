package algorithms.sorting;

import java.util.LinkedList;

public class RadixSort {

    private static void distribute(int[] arr, LinkedList<Integer>[] digits, String digitType) {
        for (int i = 0; i < arr.length; i++) {
            if (digitType.equals("ones")) {
                digits[arr[i] % 10].addLast(arr[i]);
            } else {
                digits[arr[i] / 10].addLast(arr[i]);
            }
        }
    }

    private static void collect(LinkedList<Integer>[] digits, int[] arr) {
        int i = 0;
        for (int digit = 0; digit < 10; digit++) {
            while (!digits[digit].isEmpty()) {
                arr[i++] = digits[digit].removeFirst();
            }
        }
    }

    private static void display(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            System.out.print(arr[i] + " ");
            if (++i % 10 == 0) // i += 1; i % 10
                System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final int size = 50;
        final int numQueues = 10;
        LinkedList<Integer>[] digits = new LinkedList[numQueues];
        for (int i = 0; i < numQueues; i++) {
            digits[i] = new LinkedList<>();
        }
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }

        display(numbers);

        distribute(numbers, digits, "ones");
        collect(digits, numbers);
        display(numbers);

        distribute(numbers, digits, "tens");
        collect(digits, numbers);
        display(numbers);
    }
}
