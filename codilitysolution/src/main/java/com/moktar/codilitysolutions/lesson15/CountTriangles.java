package com.moktar.codilitysolutions.lesson15;

import java.util.Arrays;

/**
 * An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
 * <p>
 * A[P] + A[Q] > A[R],
 * A[Q] + A[R] > A[P],
 * A[R] + A[P] > A[Q].
 * For example, consider array A such that:
 * <p>
 * A[0] = 10    A[1] = 2    A[2] = 5
 * A[3] = 1     A[4] = 8    A[5] = 12
 * There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns the number of triangular triplets in this array.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 10    A[1] = 2    A[2] = 5
 * A[3] = 1     A[4] = 8    A[5] = 12
 * the function should return 4, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..1,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */

public class CountTriangles {

    public int solution(int[] A) {

        Arrays.sort(A);
        int counter = 0;

        for (int x = 0; x < A.length - 2; x++) {

            for (int y = x + 1; y < A.length - 1; y++) {

                int z = findZ(A, y + 1, A.length - 1, x, y);
                counter += z - y;
            }
        }
        return counter;
    }

    private int findZ(int[] A, int from, int to, int x, int y) {
        if (A[to] < A[x] + A[y]) {
            return to;
        }
        return findZByBinarySearch(A, from, to, A[x] + A[y]);
    }

    private int findZByBinarySearch(int[] A, int from, int to, int i) {
        int beg = from, end = to;

        int res = from - 1;
        for (int mid; beg <= end; ) {
            mid = (beg + end) / 2;

            if (A[mid] < i) {
                res = mid;
                beg = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

}
