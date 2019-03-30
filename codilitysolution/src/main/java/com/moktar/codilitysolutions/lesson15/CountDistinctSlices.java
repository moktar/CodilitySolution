package com.moktar.codilitysolutions.lesson15;

/**
 * An integer M and a non-empty array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.
 * <p>
 * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.
 * <p>
 * For example, consider integer M = 6 and array A such that:
 * <p>
 * A[0] = 3
 * A[1] = 4
 * A[2] = 5
 * A[3] = 5
 * A[4] = 2
 * There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 * <p>
 * The goal is to calculate the number of distinct slices.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int M, int[] A); }
 * <p>
 * that, given an integer M and a non-empty array A consisting of N integers, returns the number of distinct slices.
 * <p>
 * If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
 * <p>
 * For example, given integer M = 6 and array A such that:
 * <p>
 * A[0] = 3
 * A[1] = 4
 * A[2] = 5
 * A[3] = 5
 * A[4] = 2
 * the function should return 9, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000];
 * M is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..M].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class CountDistinctSlices {

    public int solution(int M, int[] A) {
        // write your code in Java SE 8
        int count = 0;
        boolean[] counters = new boolean[100_001];

        int back, front = back = 0;
        for (; back < A.length && front < A.length; ) {

            if (!counters[A[front]]) {
                int val = front - back + 1;
                count += val;

                //System.out.printf("(%s,%s) +%s %n", back, front, val);
                if (count >= 1_000_000_000) {
                    return 1_000_000_000;
                }
                counters[A[front]] = true;
                front++;
            } else {
                counters[A[back]] = false;
                back++;
            }
        }
        return count;
    }

}
