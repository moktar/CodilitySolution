package com.moktar.codilitysolutions.lesson6;

/**
 * A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
 * <p>
 * For example, array A such that:
 * <p>
 * A[0] = -3
 * A[1] = 1
 * A[2] = 2
 * A[3] = -2
 * A[4] = 5
 * A[5] = 6
 * contains the following example triplets:
 * <p>
 * (0, 1, 2), product is −3 * 1 * 2 = −6
 * (1, 2, 4), product is 1 * 2 * 5 = 10
 * (2, 4, 5), product is 2 * 5 * 6 = 60
 * Your goal is to find the maximal product of any triplet.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a non-empty array A, returns the value of the maximal product of any triplet.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = -3
 * A[1] = 1
 * A[2] = 2
 * A[3] = -2
 * A[4] = 5
 * A[5] = 6
 * the function should return 60, as the product of triplet (2, 4, 5) is maximal.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [3..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class MaxProductOfThree {

    public int solution(int[] A) {
        // write your code in Java SE 8
        int[] edges = new int[]{
                Integer.MAX_VALUE, //  min       -> 0
                Integer.MAX_VALUE, //  min + 1   -> 1
                Integer.MAX_VALUE, //  max - 2   -> 2
                Integer.MAX_VALUE, //  max - 1   -> 3
                Integer.MAX_VALUE, //  max       -> 4
        };
        for (int a : A) {

            if (edges[0] == Integer.MAX_VALUE || a <= edges[0]) {
                edges[1] = edges[0];
                edges[0] = a;
            } else if (edges[1] == Integer.MAX_VALUE || a < edges[1]) {
                edges[1] = a;
            }

            if (edges[4] == Integer.MAX_VALUE || edges[4] <= a) {
                edges[2] = edges[3];
                edges[3] = edges[4];
                edges[4] = a;
            } else if (edges[3] == Integer.MAX_VALUE || edges[3] <= a) {
                edges[2] = edges[3];
                edges[3] = a;
            } else if (edges[2] == Integer.MAX_VALUE || edges[2] < a) {
                edges[2] = a;
            }
        }

        // System.out.printf("Edge values: %s\n", Arrays.toString(edges));

        int head = edges[4];
        int head2 = edges[3] * head;
        int tail2 = edges[0] * edges[1];

        if (head >= 0) {
            return Math.max(tail2 * head, head2 * edges[2]);
        } else {
            return head2 * edges[2];
        }
    }
}
