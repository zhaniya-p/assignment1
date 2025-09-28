package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class QuickSortTest {

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 8, 1, 3};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
    }

    @Test
    void testLargeArray() {
        int[] arr = new Random().ints(1000, -1000, 1000).toArray();
        int[] copy = arr.clone();
        QuickSort.sort(arr);
        java.util.Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }
}
