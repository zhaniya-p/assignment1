## Assignment 1 – Divide and Conquer Algorithms

## 1. Learning Goals
- Implement classical divide-and-conquer algorithms with safe recursion patterns.
- Analyze running-time recurrences using the Master Theorem and Akra–Bazzi intuition.
- Validate results with measurements (time, recursion depth, comparisons, allocations).
- Maintain a clean GitHub history with issues, branches, and commits.

---

## 2. Architecture
- All algorithms are implemented in the `algos` package.
- Metrics (comparisons, allocations, recursion depth) are tracked by the `Metrics` class and updated during recursion.
- `cli.Main` runs all algorithms on the same inputs and prints results to the console.
- Recursion depth is controlled as follows:
  - QuickSort — always recurse into the smaller partition, handle the larger one iteratively.
  - MergeSort — uses cutoff for small `n` (switches to InsertionSort).
  - Deterministic Select — only recurses into the needed side of the array.
  - Closest Pair — recursive divide-and-conquer with a strip check.

---

## 3. Recurrence Analysis

**MergeSort**
- T(n) = 2T(n/2) + Θ(n).
- Master Theorem, Case 2.
- Result: Θ(n log n).

**QuickSort (randomized pivot)**
- Expected recurrence: T(n) = T(n/2) + T(n/2) + Θ(n).
- Random pivot choice → expected depth O(log n).
- Result: Θ(n log n) on average, worst case Θ(n²).

**Deterministic Select (Median-of-Medians)**
- Groups of 5, recursive median-of-medians pivot.
- Recurrence: T(n) ≤ T(n/5) + T(7n/10) + Θ(n).
- Result: Θ(n).

**Closest Pair of Points (2D)**
- Sort by x, recursive split + strip check.
- T(n) = 2T(n/2) + Θ(n).
- Master Theorem, Case 2.
- Result: Θ(n log n).

---

## 4. Experimental Results

Example run (`cli.Main`):

Observations:
**Time vs n**: Theoretical bounds confirmed; QuickSort faster in practice due to cache effects.
**Depth vs n**: MergeSort ~ log₂n, QuickSort ≤ 2·log₂n, Select grows linearly with n.
**Constant factors**: Closest Pair has higher constants due to geometric checks.  
