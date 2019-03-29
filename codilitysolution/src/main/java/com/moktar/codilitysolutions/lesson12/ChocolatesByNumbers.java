package com.moktar.codilitysolutions.lesson12;

/**
 * Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.
 * <p>
 * You start to eat the chocolates. After eating a chocolate you leave only a wrapper.
 * <p>
 * You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.
 * <p>
 * More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).
 * <p>
 * You stop eating when you encounter an empty wrapper.
 * <p>
 * For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.
 * <p>
 * The goal is to count the number of chocolates that you will eat, following the above rules.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N, int M); }
 * <p>
 * that, given two positive integers N and M, returns the number of chocolates that you will eat.
 * <p>
 * For example, given integers N = 10 and M = 4. the function should return 5, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N and M are integers within the range [1..1,000,000,000].
 */
public class ChocolatesByNumbers {

    public int solution(int N, int M) {
        // write your code in Java SE 8
        if (M == N) {
            return 1;
        } else if (M == 1) {
            return N;
        } else if (M > N) {
            M %= N;
        }
        if (M == 0) {
            return 1;
        }

         int gcd = gcd1(N, M);
//         int gcd = gcd2(N, M);
//        int gcd = gcd3(N, M);

        return N / gcd;
    }

    private int gcd1(int N, int M) {
        int a = N, b = M;

        for (; ; ) {
            if (a == b) {
                return a;

            } else if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
    }

    private int gcd2(int N, int M) {
        int a = N, b = M, c;

        for (; ; ) {
            if (a % b == 0) {
                return b;
            } else {
                c = a;
                a = b;
                b = c % b;
            }
        }
    }

    private int gcd3(int N, int M) {
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
