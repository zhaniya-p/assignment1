package algos;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics m) {
        return select(arr, 0, arr.length - 1, k, m);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics m) {
        m.enterRecursion();
        if (right - left + 1 <= 5) {
            insertionSort(arr, left, right, m);
            m.exitRecursion();
            return arr[left + k];
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight, m);
            int median = (i + subRight) / 2;
            swap(arr, left + numMedians, median);
            numMedians++;
        }

        int pivot = select(arr, left, left + numMedians - 1, numMedians / 2, m);
        int pivotIndex = partition(arr, left, right, pivot, m);
        int rank = pivotIndex - left;
        if (k == rank) return arr[pivotIndex];
        else if (k < rank) return select(arr, left, pivotIndex - 1, k, m);
        else return select(arr, pivotIndex + 1, right, k - rank - 1, m);
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics m) {
        int i = left;
        int j = right;
        while (i <= j) {
            while (arr[i] < pivot) { m.compare(); i++; }
            while (arr[j] > pivot) { m.compare(); j--; }
            if (i <= j) swap(arr, i++, j--);
        }
        return i - 1;
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
