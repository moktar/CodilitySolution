package com.moktar.codilitysolutions.lesson10;

/**
 * An integer N is given, representing the area of some rectangle.
 * <p>
 * The area of a rectangle whose sides are of length A and B is A * B, and the perimeter is 2 * (A + B).
 * <p>
 * The goal is to find the minimal perimeter of any rectangle whose area equals N. The sides of this rectangle should be only integers.
 * <p>
 * For example, given integer N = 30, rectangles of area 30 are:
 * <p>
 * (1, 30), with a perimeter of 62,
 * (2, 15), with a perimeter of 34,
 * (3, 10), with a perimeter of 26,
 * (5, 6), with a perimeter of 22.
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given an integer N, returns the minimal perimeter of any rectangle whose area is exactly equal to N.
 * <p>
 * For example, given an integer N = 30, the function should return 22, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..1,000,000,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */

public class MinPerimeterRectangle {

    public int solution(int N) {
        // write your code in Java SE 8
        if (N == 1) {
            return 4;
        }

        double s = Math.sqrt((double) N);
        // System.out.printf("Square root : %s\n", s);

        int d = (int) s;
        if (s - d > 0) {
            while (N % d != 0) {
                d--;
            }
        }

        int dd = N / d;
        return dd * 2 + d * 2;
    }
}
