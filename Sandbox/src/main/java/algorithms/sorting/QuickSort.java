package algorithms.sorting;

public class QuickSort {

    /**
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int temp;
        int pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (j > left) quickSort(arr, left, j);
        if (i < right) quickSort(arr, i, right);
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

        quickSort(data, 0, data.length - 1);

        System.out.println();
        System.out.println("After sorting: ");
        display(data);
    }
}
