package com.kkpa.hackerrank.interviewpreparationkit.arrays;

import org.springframework.stereotype.Component;

@Component
public class LeftRotation {

  // Complete the rotLeft function below.
  public int[] rotLeft(int[] a, int d) {
    int n = a.length;
    int[] result = new int[n];
    if (d >= n) {
      d = d % n;
    }

    if (d == 0) {
      return a;
    }

    for (int i = 0; i < n; i++) {
      int newPosition = (n - (d - i));
      if (newPosition >= n) {
        newPosition = newPosition % n;
      }
      result[newPosition] = a[i];
    }
    return result;
  }

}
