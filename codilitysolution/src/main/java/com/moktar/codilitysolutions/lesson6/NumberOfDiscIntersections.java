package com.moktar.codilitysolutions.lesson6;

/**
 * We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].
 * <p>
 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).
 * <p>
 * The figure below shows discs drawn for N = 6 and A as follows:
 * <p>
 * A[0] = 1
 * A[1] = 5
 * A[2] = 2
 * A[3] = 1
 * A[4] = 4
 * A[5] = 0
 * <p>
 * <p>
 * There are eleven (unordered) pairs of discs that intersect, namely:
 * <p>
 * discs 1 and 4 intersect, and both intersect with all the other discs;
 * disc 2 also intersects with discs 0 and 3.
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
 * <p>
 * Given array A shown above, the function should return 11, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..2,147,483,647].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class NumberOfDiscIntersections {

    public int solution(int[] A) {
        // write your code in Java SE 8
        int len = A.length;
        // observation: each center point is between [0 - len)
        // .. so, checking between this points is enough to observe all circles.
        int[] numOfCirclesStartingArr = new int[len];
        int[] numOfCirclesEndingArr = new int[len];

        // check & keep how many circles passes that point for each start and end
        for (int i = 0; i < len; i++) {
            // each point starts at index - radius
            // since there is no need to consider negative points
            // .. keep them at index 0
            int starting = i - A[i] < 0 ? 0 : i - A[i];
            numOfCirclesStartingArr[starting]++;

            // each point ends at index + radius
            // since there is no need to consider points beyond len
            // .. keep them at index len-1
            // .. long is to eliminate overflow possibility
            int ending = (long) i + (long) A[i] >= len ? len - 1 : i + A[i];
            numOfCirclesEndingArr[ending]++;
        }

        // calculate number of intersections using
        // num of circles starting and ending each point between [0 - len)
        int numOfIntersects = 0;
        int numOfActiveCircles = 0;
        for (int i = 0; i < len; i++) {
            // circles starting at i intersects all active circles
            // .. + all circles at i intersect each other
            numOfIntersects += (numOfCirclesStartingArr[i] * numOfActiveCircles)
                    + (numOfCirclesStartingArr[i] * (numOfCirclesStartingArr[i] - 1)) / 2;
            if (numOfIntersects > 10000000) return -1;
            numOfActiveCircles += numOfCirclesStartingArr[i] - numOfCirclesEndingArr[i];
        }

        return numOfIntersects;
    }

}
