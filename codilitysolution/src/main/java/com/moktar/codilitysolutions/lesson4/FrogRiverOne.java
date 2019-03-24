package com.moktar.codilitysolutions.lesson4;

/**
 * A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.
 * <p>
 * You are given an array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.
 * <p>
 * The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.
 * <p>
 * For example, you are given integer X = 5 and array A such that:
 * <p>
 * A[0] = 1
 * A[1] = 3
 * A[2] = 1
 * A[3] = 4
 * A[4] = 2
 * A[5] = 3
 * A[6] = 5
 * A[7] = 4
 * In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int X, int[] A); }
 * <p>
 * that, given a non-empty array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.
 * <p>
 * If the frog is never able to jump to the other side of the river, the function should return −1.
 * <p>
 * For example, given X = 5 and array A such that:
 * <p>
 * A[0] = 1
 * A[1] = 3
 * A[2] = 1
 * A[3] = 4
 * A[4] = 2
 * A[5] = 3
 * A[6] = 5
 * A[7] = 4
 * the function should return 6, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N and X are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..X].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class FrogRiverOne {

    public int solution(int X, int[] A) {
        // write your code in Java SE 8

        // X can fit in memory and O(X) space is allowed - task spec
        // indicates whether there is leaf at point X
        // frog needs each point to be true
        // X +1 --> to map each index as real leaf position
        // .. easier for claculations
        boolean[] isLeafFallAtPointArr = new boolean[X + 1];

        // counts the first leaf at each point
        // .. since only that is useful to frog
        // .. for frog, there should be exactly X useful leaves
        int usefulLeafCounter = 0;

        // looping array, fill isLeafFallAtPointArr to track whether leaf fall at that point
        // doing that keep track of actual set operations (usefulLeafCounter)
        // .. to make sure every point filled (should be X set operations)
        for (int i = 0; i < A.length; i++) {
            if (!isLeafFallAtPointArr[A[i]]) {
                isLeafFallAtPointArr[A[i]] = true;
                usefulLeafCounter++;

                if (usefulLeafCounter == X) {
                    return i;
                }
            }
        }

        return -1;
    }
}
