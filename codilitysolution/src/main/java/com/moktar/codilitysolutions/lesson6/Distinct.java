package com.moktar.codilitysolutions.lesson6;

/**
 * Write a function
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns the number of distinct values in array A.
 * <p>
 * For example, given array A consisting of six elements such that:
 * <p>
 * A[0] = 2    A[1] = 1    A[2] = 1
 * A[3] = 2    A[4] = 3    A[5] = 1
 * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class Distinct {

    public int solution(int[] A) {
        // write your code in Java SE 8
        int uniques = 0;

        top:
        for (int i = 0; i < A.length; i++) {
            final int id = A[i];
            for (int j = i + 1; j < A.length; j++) {
                if (id == A[j])
                    continue top;
            }
            uniques++;
        }
        return uniques;
    }

}
