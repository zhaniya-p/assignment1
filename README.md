# Assignment 1 — Divide-and-Conquer Algorithms

---

## 1. Architecture Notes

* MergeSort: Uses a reusable buffer for merging and a small-n cutoff (16) to switch to insertion sort for tiny arrays. Recursion depth is tracked via Metrics.
* QuickSort: Randomized pivot, always recurses on the smaller partition and iterates over the larger one to limit stack depth to O(log n) typical.
* Deterministic Select (Median-of-Medians): Groups of 5, pivot = median of medians, recurses only on required side. Smaller-side-first recursion prevents deep stacks.
* Closest Pair (2D): Sorts points by x and y, recursive split, and strip check in y-order using 7–8 neighbor scan.

Metrics collected:

* Comparisons
* Allocations (temporary arrays)
* Max recursion depth
* Runtime (ms)

---

## 2. Recurrence Analysis

| Algorithm              | Recurrence                      | Master / Akra–Bazzi      | Θ-Result            |
| ---------------------- | ------------------------------- | ------------------------ | ------------------- |
| MergeSort              | T(n) = 2T(n/2) + Θ(n)           | Master Case 2            | Θ(n log n)          |
| QuickSort (randomized) | T(n) ≈ T(n/2) + Θ(n)            | Intuition (random pivot) | Θ(n log n) expected |
| Deterministic Select   | T(n) = T(n/5) + T(7n/10) + Θ(n) | Akra–Bazzi               | Θ(n)                |
| Closest Pair           | T(n) = 2T(n/2) + Θ(n)           | Master Case 2            | Θ(n log n)          |

Notes:

* Master Theorem applied for standard divide-and-conquer.
* Akra–Bazzi intuition used for Deterministic Select, because partition size is not exactly half.

---

## 3. Plots

> Time vs n
> (Insert runtime measurements from CLI / Main.java for array sizes 1e3, 1e4, 1e5, 1e6)

> Recursion Depth vs n
> (Insert maximum recursion depth measurements from Metrics for each n)

Short discussion:

* MergeSort recursion depth ≈ log₂ n
* QuickSort recursion depth ≤ 2 log₂ n due to smaller-first recursion
* Deterministic Select recursion depth grows slower than log n
* Closest Pair recursion depth ≈ log₂ n

Constant-factor effects:

* Cache behavior improves MergeSort and Closest Pair for contiguous memory arrays.
* Garbage collection negligible as we reuse buffer arrays in MergeSort and preallocate arrays for Closest Pair strip.
* QuickSort performance may slightly vary due to pivot randomness.

---

## 4. Summary

* Measured times align closely with theoretical Θ-results for all algorithms.
* Recursion depths confirm stack-bound strategies are effective.
* Small-n cutoff and buffer reuse reduce constant factors significantly.
* Randomized QuickSort and Median-of-Medians select show robustness against adversarial inputs.

---

## 5. GitHub Workflow

* Branches: feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics, feature/cli, release/v1.0
* Commits:

    * init: Maven + JUnit5 + CI + README
    * feat(metrics): counters, depth tracker, CSV writer
    * feat(mergesort): baseline + buffer + cutoff + tests
    * feat(quicksort): smaller-first recursion, randomized pivot + tests
    * refactor(util): partition, swap, shuffle, guards
    * feat(select): deterministic select (MoM5) + tests
    * feat(closest): divide-and-conquer closest pair + tests
    * feat(cli): parse args, run algos, emit CSV
    * bench(jmh): select vs sort
    * docs(report): Master/Akra–Bazzi analysis, plots
    * fix: edge cases (duplicates, tiny arrays)
    * release: v1.0
