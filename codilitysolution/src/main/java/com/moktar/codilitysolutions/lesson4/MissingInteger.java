package com.moktar.codilitysolutions.lesson4;

/**
 * This is a demo task.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 * <p>
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 * <p>
 * Given A = [1, 2, 3], the function should return 4.
 * <p>
 * Given A = [−1, −3], the function should return 1.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class MissingInteger {

    public int solution(int[] A) {
        // write your code in Java SE 8
        // since looking for smallest positive integer that should exist, but not
        // .. worst case is A contains elements 1...len(A) and answer len(A+1)
        // .. in other cases answer will be smaller that len(A)
        // .. can keep an array to track - since O(n) space is allowed in task spec
        // adopt 1-indexing to map each position to each pos integer value
        // and keep an extra element at the end
        // .. easier for calculations
        boolean[] isIntExist = new boolean[A.length + 2];

        populateExistingInts(A, isIntExist);

        return findMaxConsecutiveInt(isIntExist) + 1;
    }

    private void populateExistingInts(int[] A, boolean[] isIntExist) {
        for (int val : A) {
            if (val <= 0 || val >= A.length + 1) {
                continue;
            }

            if (!isIntExist[val]) {
                isIntExist[val] = true;
            }
        }
    }

    private int findMaxConsecutiveInt(boolean[] isIntExist) {
        // since 1-indexing..
        for (int i = 1; i < isIntExist.length; i++) {
            if (!isIntExist[i]) {
                return i - 1;
            }
        }

        return 0;
    }
}
