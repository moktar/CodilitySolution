package com.moktar.codilitysolutions.lesson10;

/**
 * A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
 * <p>
 * For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given a positive integer N, returns the number of its factors.
 * <p>
 * For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..2,147,483,647].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class CountFactors {

    public int solution(int N) {
        // write your code in Java SE 8
        if (N == 1) {
            return 1;
        }

        int count = 2;
        long last = -1;
        for (long i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                count += 2;
                last = i;
            }
        }
        return (last > -1 && last * last == N) ? count - 1 : count;
    }
}
