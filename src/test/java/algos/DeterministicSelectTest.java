package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 9, 4, 3};
        int kth = DeterministicSelect.select(arr.clone(), 2); // 2-й по порядку
        assertEquals(3, kth);
    }

    @Test
    void testLargeArray() {
        int[] arr = new Random().ints(1000, -1000, 1000).toArray();
        int[] copy = arr.clone();
        java.util.Arrays.sort(copy);
        int k = 500;
        int kth = DeterministicSelect.select(arr.clone(), k);
        assertEquals(copy[k], kth);
    }
}
