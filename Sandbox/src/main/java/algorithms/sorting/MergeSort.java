package algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return; // break point

        // divide and conquer
        int mid = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(leftArr);
        mergeSort(rightArr);

        // merge
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftArr.length || j < rightArr.length) {
            if (i < leftArr.length && j < rightArr.length) {
                if (leftArr[i] < rightArr[j]) {
                    arr[k] = leftArr[i];
                    i++;
                } else {
                    arr[k] = rightArr[j];
                    j++;
                }
            } else if (i < leftArr.length) {
                // remaining of left
                arr[k] = leftArr[i];
                i++;
            } else {
                // remaining of right
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{5, 10, 1, 6, 2, 9, 3, 8, 7, 4};
        System.out.println("Before sorting: ");
        display(data);

        mergeSort(data);

        System.out.println();
        System.out.println("After sorting: ");
        display(data);
    }
}
