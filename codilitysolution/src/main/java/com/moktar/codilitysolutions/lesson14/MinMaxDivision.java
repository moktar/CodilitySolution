package com.moktar.codilitysolutions.lesson14;

import android.annotation.SuppressLint;

import java.util.Arrays;

/**
 * You are given integers K, M and a non-empty array A consisting of N integers. Every element of the array is not greater than M.
 * <p>
 * You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
 * <p>
 * The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
 * <p>
 * The large sum is the maximal sum of any block.
 * <p>
 * For example, you are given integers K = 3, M = 5 and array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 1
 * A[2] = 5
 * A[3] = 1
 * A[4] = 2
 * A[5] = 2
 * A[6] = 2
 * The array can be divided, for example, into the following blocks:
 * <p>
 * [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
 * [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
 * [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
 * [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
 * The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int K, int M, int[] A); }
 * <p>
 * that, given integers K, M and a non-empty array A consisting of N integers, returns the minimal large sum.
 * <p>
 * For example, given K = 3, M = 5 and array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 1
 * A[2] = 5
 * A[3] = 1
 * A[4] = 2
 * A[5] = 2
 * A[6] = 2
 * the function should return 6, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N and K are integers within the range [1..100,000];
 * M is an integer within the range [0..10,000];
 * each element of array A is an integer within the range [0..M].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class MinMaxDivision {

    @SuppressLint("NewApi")
    public int solution(int K, int M, int[] A) {
        // write your code in Java SE 8
        int minLargeSumm = Arrays.stream(A).max().getAsInt();
        int maxLargeSumm = Arrays.stream(A).sum();

        if (K >= A.length) return minLargeSumm;
        if (K == 1 || A.length == 1) return maxLargeSumm;
        if (minLargeSumm == maxLargeSumm) return maxLargeSumm;

        int result = -1;
        for (int middle; minLargeSumm <= maxLargeSumm; ) {

            middle = (maxLargeSumm + minLargeSumm) / 2;
            //System.out.printf("[%s->%s], middle = %s%n", minLargeSumm, maxLargeSumm, middle);

            if (isDivided(middle, K, A)) {
                maxLargeSumm = middle - 1;  // could be less
                result = middle;
            } else {
                minLargeSumm = middle + 1;  // could be greater
            }
        }
        return result;
    }

    private boolean isDivided(int maxSumm, int maxBlocks, int[] A) {

        int s = 0, b = 1;
        for (int a : A) {
            if (s + a <= maxSumm) {
                s += a;
            } else {
                if (++b > maxBlocks) {
                    return false;
                }
                s = a;
            }
        }
        return true;
    }
}
