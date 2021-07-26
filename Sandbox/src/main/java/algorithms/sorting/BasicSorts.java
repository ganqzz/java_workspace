package algorithms.sorting;

public class BasicSorts {

    // Bubble Sort (最大値から決定していく)
    static void bubbleSort1(int[] arr) {
        int end = arr.length - 1;
        for (int i = end; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) { // 隣の値との比較
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Bubble Sort (最小値から決定していく)
    static void bubbleSort2(int[] arr) {
        int end = arr.length - 1;
        for (int i = 0; i < end; i++) {
            for (int j = end; j > i; j--) {
                if (arr[j - 1] > arr[j]) { // 隣の値との比較
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    // Selection Sort (min)
    static void minSort(int[] arr) {
        int size = arr.length;
        int end = arr.length - 1;
        for (int i = 0; i < end; i++) {
            int min = i; // 値ではなく、インデックスを入れる。
            int j = i + 1; // 0~i まではソート済み。
            while (j < size) {
                if (arr[min] > arr[j]) { // 最小値との比較
                    min = j;
                }
                j++;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        int[] a = data.clone();
        System.out.println("Before sorting: ");
        display(a);

        bubbleSort1(a);

        System.out.println("After sorting: ");
        display(a);

        a = data.clone();
        System.out.println("Before sorting: ");
        display(a);

        bubbleSort2(a);

        System.out.println("After sorting: ");
        display(a);

        a = data.clone();
        System.out.println("Before sorting: ");
        display(a);

        minSort(a);

        System.out.println("After sorting: ");
        display(a);

        a = data.clone();
        System.out.println("Before sorting: ");
        display(a);

        insertionSort(a);

        System.out.println("After sorting: ");
        display(a);
    }
}
