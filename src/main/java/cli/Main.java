package cli;

import algos.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 100000;
        Random rnd = new Random();
        Metrics metrics = new Metrics();

        int[] arr = rnd.ints(n, 0, 1_000_000).toArray();
        int[] arr2 = arr.clone();
        int[] arr3 = arr.clone();

        System.out.println("MergeSort:");
        metrics.reset();
        long t1 = System.nanoTime();
        MergeSort.sort(arr, metrics);
        long t2 = System.nanoTime();
        System.out.println(metrics + ", Time=" + (t2 - t1)/1e6 + "ms");

        System.out.println("QuickSort:");
        metrics.reset();
        t1 = System.nanoTime();
        QuickSort.sort(arr2, metrics);
        t2 = System.nanoTime();
        System.out.println(metrics + ", Time=" + (t2 - t1)/1e6 + "ms");

        System.out.println("Deterministic Select:");
        metrics.reset();
        t1 = System.nanoTime();
        int k = n / 2;
        int kth = DeterministicSelect.select(arr3, k, metrics);
        t2 = System.nanoTime();
        System.out.println(metrics + ", Time=" + (t2 - t1)/1e6 + "ms, k-th=" + kth);

        System.out.println("Closest Pair (2D):");
        metrics.reset();
        Point2D.Double[] points = new Point2D.Double[n];
        for (int i = 0; i < n; i++) points[i] = new Point2D.Double(rnd.nextDouble(), rnd.nextDouble());
        t1 = System.nanoTime();
        double minDist = ClosestPair.closestPair(points, metrics);
        t2 = System.nanoTime();
        System.out.println(metrics + ", Time=" + (t2 - t1)/1e6 + "ms, MinDist=" + minDist);
    }
}
