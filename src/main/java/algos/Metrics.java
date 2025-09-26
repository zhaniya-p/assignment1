package algos;

public class Metrics {
    public long comparisons = 0;
    public long allocations = 0;
    public int maxDepth = 0;
    private int currentDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void compare() {
        comparisons++;
    }

    public void allocate() {
        allocations++;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    @Override
    public String toString() {
        return String.format("Comparisons=%d, Allocations=%d, MaxDepth=%d",
                comparisons, allocations, maxDepth);
    }
}
