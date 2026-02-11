package com.challenges.exercism.hard.bookstore;

import java.util.*;

public class BookStore {

  private static final double BOOK_PRICE = 8.0;

  // Example:
  // DISCOUNTS[3] = 0.10 → 10% discount for 3 different books
  private static final double[] DISCOUNTS = {0.0, 0.0, 0.05, 0.10, 0.20, 0.25};

  // Memoization map:
  // Key   → String representation of remaining counts
  // Value → Minimum cost achievable from that state
  //
  // Example key:
  // "[2, 2, 1, 1, 0]"
  private final Map<String, Double> memo = new HashMap<>();

  // Convert input list into count representation.
  // Example input:
  // {1,1,2,2,3,4}
  public double calculateBasketCost(List<Integer> books) {

    // counts becomes:
    // [2, 2, 1, 1, 0]
    int[] counts = new int[5];
    for (int book : books) {
      counts[book - 1]++;
    }

    return calculate(counts);
  }

  private double calculate(int[] counts) {

    // key = "[2, 2, 1, 1, 0]"
    String key = Arrays.toString(counts);

    // If we have already solved this exact state,
    // return stored result instead of recomputing.
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    // Base case:
    // If counts = [0,0,0,0,0], basket is empty → cost = 0
    if (isEmpty(counts)) {
      return 0.0;
    }

    // Determine which book types are still available.
    //
    // For counts = [2,2,1,1,0]
    // available = [0,1,2,3]
    // (book indices that still have copies)
    List<Integer> available = getAvailableBooks(counts);

    double minCost = Double.MAX_VALUE;

    // Try all possible group sizes.
    //
    // In this example:
    // available.size() = 4
    //
    // So we try:
    // size = 1
    // size = 2
    // size = 3
    // size = 4
    for (int size = 1; size <= available.size(); size++) {

      minCost =
          Math.min(minCost, tryAllCombinations(counts, available, size, 0, new ArrayList<>()));
    }

    // Store result for this state in memo.
    memo.put(key, minCost);

    return minCost;
  }

  private boolean isEmpty(int[] counts) {
    for (int c : counts) {
      if (c > 0) return false;
    }
    return true;
  }

  private List<Integer> getAvailableBooks(int[] counts) {

    List<Integer> available = new ArrayList<>();

    // Example:
    // counts = [2,2,1,1,0]
    //
    // We add indices where count > 0:
    // available = [0,1,2,3]
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0) {
        available.add(i);
      }
    }
    return available;
  }

  private double tryAllCombinations(
      int[] counts, List<Integer> available, int size, int start, List<Integer> current) {

    // Example (size = 3):
    //
    // possible combinations generated:
    // [0,1,2]
    // [0,1,3]
    // [0,2,3]
    // [1,2,3]
    //
    // These represent choosing 3 distinct book types.

    if (current.size() == size) {

      // Clone counts so we don't mutate original state
      int[] newCounts = counts.clone();

      // Example:
      // current = [0,1,2]
      //
      // counts before: [2,2,1,1,0]
      //
      // After decrement:
      // newCounts = [1,1,0,1,0]
      for (int index : current) {
        newCounts[index]--;
      }

      // Compute discounted price for this group size
      //
      // Example:
      // size = 3
      // price = 3 * 8 * (1 - 0.10)
      //       = 24 * 0.9
      //       = 21.60
      double groupPrice = size * BOOK_PRICE * (1 - DISCOUNTS[size]);

      // Recursively calculate cost of remaining books
      //
      // calculate([1,1,0,1,0])
      return groupPrice + calculate(newCounts);
    }

    double minCost = Double.MAX_VALUE;

    // Generate combinations recursively
    //
    // This builds combinations like:
    // [0]
    // [0,1]
    // [0,1,2]
    // etc.
    for (int i = start; i < available.size(); i++) {
      current.add(available.get(i));

      minCost = Math.min(minCost, tryAllCombinations(counts, available, size, i + 1, current));

      current.remove(current.size() - 1);
    }

    return minCost;
  }
}
