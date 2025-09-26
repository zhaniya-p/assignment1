package algos;

import java.util.Arrays;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics m) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, m);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics m) {
        m.enterRecursion();
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, m);
            m.exitRecursion();
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, buffer, left, mid, m);
        mergeSort(arr, buffer, mid + 1, right, m);
        merge(arr, buffer, left, mid, right, m);
        m.exitRecursion();
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics m) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        m.allocate();
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            m.compare();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }
        while (i <= mid) arr[k++] = buffer[i++];
        while (j <= right) arr[k++] = buffer[j++];
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
}