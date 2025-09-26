package algos;

import java.util.Random;

public class QuickSort {
    private static final int CUTOFF = 16;
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics m) {
        quickSort(arr, 0, arr.length - 1, m);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics m) {
        m.enterRecursion();
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, m);
            m.exitRecursion();
            return;
        }

        int pivotIndex = left + rand.nextInt(right - left + 1);
        swap(arr, pivotIndex, right);
        int pivot = arr[right];

        int i = left;
        for (int j = left; j < right; j++) {
            m.compare();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);

        if (i - left < right - i) {
            quickSort(arr, left, i - 1, m);
            quickSort(arr, i + 1, right, m);
        } else {
            quickSort(arr, i + 1, right, m);
            quickSort(arr, left, i - 1, m);
        }
        m.exitRecursion();
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                m.compare();
                if (arr[j] <= key) break;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }
}
