package com.moktar.codilitysolutions.lesson11;

/**
 * You are given an array A consisting of N integers.
 * <p>
 * For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
 * <p>
 * For example, consider integer N = 5 and array A such that:
 * <p>
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * For the following elements:
 * <p>
 * A[0] = 3, the non-divisors are: 2, 6,
 * A[1] = 1, the non-divisors are: 3, 2, 3, 6,
 * A[2] = 2, the non-divisors are: 3, 3, 6,
 * A[3] = 3, the non-divisors are: 2, 6,
 * A[4] = 6, there aren't any non-divisors.
 * Write a function:
 * <p>
 * class Solution { public int[] solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
 * <p>
 * Result array should be returned as an array of integers.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * the function should return [2, 4, 3, 2, 0], as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..50,000];
 * each element of array A is an integer within the range [1..2 * N].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class CountNonDivisible {

    public int[] solution(int[] A) {
        // write your code in Java SE 8
        int[] counters = new int[100_001];
        for (int a : A) {
            counters[a]++;
        }

        final int totalDividers = A.length;
        final int[] result = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            int a = A[i];

            int count = 0;
            count += counters[a];
            if (a != 1) {
                count += counters[1];
            }

            for (int m = 2; m * m <= a; m++) {
                if (a % m == 0) {
                    count += counters[m];
                    int sf = a / m;                        // symmetric factor
                    if (sf != m) {
                        count += counters[sf];
                    }

                    if (count == totalDividers) {
                        break;
                    }
                }
            }
            result[i] = totalDividers - count;
        }
        return result;
    }
}
