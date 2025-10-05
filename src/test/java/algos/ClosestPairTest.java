package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class ClosestPairTest {

    @Test
    void testTwoPoints() {
        double[][] points = {{0,0}, {3,4}};
        double d = ClosestPair.closestPair(points);
        assertEquals(5.0, d, 1e-9);
    }

    @Test
    void testSmallSet() {
        double[][] points = {{0,0}, {1,1}, {2,2}, {1,2}};
        double d = ClosestPair.closestPair(points);
        assertTrue(d <= Math.sqrt(2));
    }

    @Test
    void testRandomPoints() {
        Random rand = new Random();
        double[][] points = new double[100][2];
        for (int i = 0; i < 100; i++) {
            points[i][0] = rand.nextDouble();
            points[i][1] = rand.nextDouble();
        }
        double d = ClosestPair.closestPair(points);
        assertTrue(d >= 0); 
    }
}
