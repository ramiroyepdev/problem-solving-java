package com.challenges.exercism.hard.change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ChangeCalculator {

  private final TreeSet<Integer> currencyCoins = new TreeSet<>();

  ChangeCalculator(List<Integer> currencyCoins) {
    this.currencyCoins.addAll(currencyCoins);
  }

  List<Integer> computeMostEfficientChange(int grandTotal) {
    if (grandTotal < 0) {
      throw new IllegalArgumentException("Negative totals are not allowed.");
    }

    if (grandTotal == 0) {
      return new ArrayList<>();
    }

    // minAmountOfCoinsForChange[i] will store the minimum number of coins needed to make change for
    // amount i
    int[] minAmountOfCoinsForChange = new int[grandTotal + 1];
    // coinsUsed[i] will store the coin that was last used to achieve the optimal solution for
    // amount i
    int[] coinsUsed = new int[grandTotal + 1];
    Arrays.fill(minAmountOfCoinsForChange, Integer.MAX_VALUE);
    minAmountOfCoinsForChange[0] = 0;

    for (int i = 1; i <= grandTotal; i++) {
      for (Integer coin : currencyCoins) {
        // Is the coin small enough to be part of the change for i and is there a solution for the
        // remaining amount (i - coin)?
        if (coin <= i && minAmountOfCoinsForChange[i - coin] != Integer.MAX_VALUE) {
          // If using this coin would result in a solution with fewer coins than the best known
          // solution for i
          if (minAmountOfCoinsForChange[i - coin] + 1 < minAmountOfCoinsForChange[i]) {
            // Update the best known solution for i
            minAmountOfCoinsForChange[i] = minAmountOfCoinsForChange[i - coin] + 1;
            // Remember that the best solution for i includes this coin
            coinsUsed[i] = coin;
          }
        }
      }
    }

    // If we couldn't find any solution for the grand total, throw an exception
    if (minAmountOfCoinsForChange[grandTotal] == Integer.MAX_VALUE) {
      throw new IllegalArgumentException(
          "The total " + grandTotal + " cannot be represented in the given currency.");
    }

    // Backtrack through the coinsUsed array to determine which coins make up the optimal solution
    List<Integer> change = new ArrayList<>();
    int actual = grandTotal;
    while (actual > 0) {
      change.add(coinsUsed[actual]);
      actual -= coinsUsed[actual];
    }

    return change.stream().sorted().toList();
  }
}
