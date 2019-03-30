package com.moktar.codilitysolutions.lesson15;

import java.util.Arrays;

import static java.lang.StrictMath.abs;

/**
 * Let A be a non-empty array consisting of N integers.
 * <p>
 * The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.
 * <p>
 * For example, the following array A:
 * <p>
 * A[0] =  1
 * A[1] =  4
 * A[2] = -3
 * has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
 * The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
 * The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
 * The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
 * The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
 * The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
 * The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a non-empty array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.
 * <p>
 * For example, given the following array A:
 * <p>
 * A[0] =  1
 * A[1] =  4
 * A[2] = -3
 * the function should return 1, as explained above.
 * <p>
 * Given array A:
 * <p>
 * A[0] = -8
 * A[1] =  4
 * A[2] =  5
 * A[3] =-10
 * A[4] =  3
 * the function should return |(−8) + 5| = 3.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class MinAbsSumOfTwo {

    public int solution(int[] A) {

        Arrays.sort(A);
        if (A[0] >= 0 && A[A.length - 1] >= 0) {          // only positive range
            return A[0] * 2;
        } else if (A[0] <= 0 && A[A.length - 1] <= 0) {   // only negative range
            return abs(A[A.length - 1] * 2);
        }

        int f = 0, b = A.length - 1;
        int sum = Integer.MAX_VALUE;
        boolean fFinish = false, bFinish = false;

        while (f <= b) {
            sum = Math.min(sum, abs(A[f] + A[b]));

            System.out.printf("%s(%s) %s(%s)%n", f, fFinish, b, bFinish);

            if (fFinish) {
                b--;
            } else if (bFinish) {
                f++;
            } else if (A[b] < abs(A[f])) {
                f++;
            } else if (abs(A[f]) < A[b]) {
                b--;
            } else {
                f++;
                b--;
            }

            if (A[f] > 0) {
                f--;
                if (f != 0) sum = Math.min(sum, abs(A[f] + A[f - 1]));
                fFinish = true;
            }
            if (A[b] < 0) {
                b++;
                if (b != A.length - 1) sum = Math.min(sum, abs(A[b] + A[b + 1]));
                bFinish = true;
            }

            if (fFinish && bFinish) break;
        }
        return sum;
    }
}
