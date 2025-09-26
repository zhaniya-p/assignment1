package algos;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closestPair(Point2D.Double[] points, Metrics m) {
        Point2D.Double[] Px = Arrays.copyOf(points, points.length);
        Point2D.Double[] Py = Arrays.copyOf(points, points.length);
        Arrays.sort(Px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(Py, Comparator.comparingDouble(p -> p.y));
        return closest(Px, Py, m);
    }

    private static double closest(Point2D.Double[] Px, Point2D.Double[] Py, Metrics m) {
        m.enterRecursion();
        int n = Px.length;
        if (n <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++) {
                    m.compare();
                    min = Math.min(min, Px[i].distance(Px[j]));
                }
            m.exitRecursion();
            return min;
        }

        int mid = n / 2;
        final Point2D.Double midPoint = Px[mid]; // <--- make it final

        Point2D.Double[] Pyl = Arrays.stream(Py)
                .filter(p -> p.x <= midPoint.x)
                .toArray(Point2D.Double[]::new);

        Point2D.Double[] Pyr = Arrays.stream(Py)
                .filter(p -> p.x > midPoint.x)
                .toArray(Point2D.Double[]::new);

        double dl = closest(Arrays.copyOfRange(Px, 0, mid), Pyl, m);
        double dr = closest(Arrays.copyOfRange(Px, mid, n), Pyr, m);
        double d = Math.min(dl, dr);

        final double finalD = d; // also final for lambda
        Point2D.Double[] strip = Arrays.stream(Py)
                .filter(p -> Math.abs(p.x - midPoint.x) < finalD)
                .toArray(Point2D.Double[]::new);

        for (int i = 0; i < strip.length; i++)
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                m.compare();
                d = Math.min(d, strip[i].distance(strip[j]));
            }

        m.exitRecursion();
        return d;
    }
}
