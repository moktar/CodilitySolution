package com.moktar.codilitysolutions.lesson3;

/**
 * An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * <p>
 * Your goal is to find that missing element.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A, returns the value of the missing element.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */

public class PermMissingElem {

    public int solution(int[] A) {
        // write your code in Java SE 8
        int totalXor = 0;
        for (int i = 1; i <= A.length + 1; i++) {
            totalXor ^= i;
        }

        int missingTotalXor = 0;
        for (int i = 0; i < A.length; i++) {
            missingTotalXor ^= A[i];
        }

        return totalXor ^ missingTotalXor;
    }
}
