package com.moktar.codilitysolutions.lesson12;

/**
 * A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
 * <p>
 * A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20.
 * <p>
 * You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.
 * <p>
 * For example, given:
 * <p>
 * N = 15 and M = 75, the prime divisors are the same: {3, 5};
 * N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
 * N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A, int[] B); }
 * <p>
 * that, given two non-empty arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 15   B[0] = 75
 * A[1] = 10   B[1] = 30
 * A[2] = 3    B[2] = 5
 * the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * Z is an integer within the range [1..6,000];
 * each element of arrays A, B is an integer within the range [1..2,147,483,647].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class CommonPrimeDivisors {

    public int solution(int[] A, int[] B) {

        int count = 0;

        for (int i = 0; i < A.length; i++) {
            int gcd = gcd(A[i], B[i]);
            if (isValid(A[i], B[i], gcd) && isValid(B[i], A[i], gcd)) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(int val1, int val2, int gcd) {

        if (val1 == val2) {
            return true;
        }
        if (val1 == 1 || val2 == 1) {
            return false;
        }

        while (gcd > 1) {
            val1 /= gcd;
            if (val1 == 1) {
                return true;
            }
            gcd = gcd(val1, val2);
        }
        return false;
    }

    private int gcd(int N, int M) {
        int a = N, b = M, res = 1;

        for (; ; ) {
            if (a == b) {
                return res * a;
            } else if ((a % 2 == 0) && (b % 2 == 0)) {
                a /= 2;
                b /= 2;
                res *= 2;
            } else if (a % 2 == 0) {
                a /= 2;
            } else if (b % 2 == 0) {
                b /= 2;

            } else if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
    }
}
