package com.moktar.codilitysolutions.lesson10;

/**
 * A non-empty array A consisting of N integers is given.
 * <p>
 * A peak is an array element which is larger than its neighbours. More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].
 * <p>
 * For example, the following array A:
 * <p>
 * A[0] = 1
 * A[1] = 5
 * A[2] = 3
 * A[3] = 4
 * A[4] = 3
 * A[5] = 4
 * A[6] = 1
 * A[7] = 2
 * A[8] = 3
 * A[9] = 4
 * A[10] = 6
 * A[11] = 2
 * has exactly four peaks: elements 1, 3, 5 and 10.
 * <p>
 * You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown in a figure below. You have to choose how many flags you should take with you. The goal is to set the maximum number of flags on the peaks, according to certain rules.
 * <p>
 * <p>
 * <p>
 * Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.
 * <p>
 * For example, given the mountain range represented by array A, above, with N = 12, if you take:
 * <p>
 * two flags, you can set them on peaks 1 and 5;
 * three flags, you can set them on peaks 1, 5 and 10;
 * four flags, you can set only three flags, on peaks 1, 5 and 10.
 * You can therefore set a maximum of three flags in this case.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a non-empty array A of N integers, returns the maximum number of flags that can be set on the peaks of the array.
 * <p>
 * For example, the following array A:
 * <p>
 * A[0] = 1
 * A[1] = 5
 * A[2] = 3
 * A[3] = 4
 * A[4] = 3
 * A[5] = 4
 * A[6] = 1
 * A[7] = 2
 * A[8] = 3
 * A[9] = 4
 * A[10] = 6
 * A[11] = 2
 * the function should return 3, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..400,000];
 * each element of array A is an integer within the range [0..1,000,000,000].
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class Flags {

    public int solution(int[] A) {
        // write your code in Java SE 8

        if (A.length < 3) {
            return 0;
        }

        int[] peaks = new int[A.length];
        int[] dist = new int[A.length];
        int peakCount = 0;
        int prevPeakIndex = -1;

        for (int i = 1; i < A.length - 1; i++) {
            if (A[i - 1] < A[i] && A[i + 1] < A[i]) {
                peakCount++;
                int d = i - prevPeakIndex;
                dist[i] = d;
                prevPeakIndex = i;
            }
            peaks[i] = peakCount;
        }
        peaks[peaks.length - 1] = peaks[peaks.length - 2];

        // System.out.printf("Peaks   : %s\n", Arrays.toString(peaks));
        // System.out.printf("Distance: %s\n", Arrays.toString(dist));

        if (peakCount == 0) {
            return 0;
        }

        double sqrt = Math.sqrt((double) A.length);
        int maxFlags = (int) sqrt;
        int range = A.length / maxFlags;
        int maxCount = 0;
        // System.out.printf("Square root: %s, maxFlags:%s, range:%s\n", sqrt, maxFlags, range);

        while (range > 1) {

            maxFlags = A.length / range;
            long prevDist = Integer.MAX_VALUE;
            int flags = 0;

            for (int startIndex = 0; startIndex < peaks.length; startIndex += range) {

                int stopIndex = Math.min(startIndex + range - 1, peaks.length - 1);
                int peaksCount = peaks[stopIndex] - (startIndex > 0 ? peaks[startIndex - 1] : 0);

                if (peaksCount < 1) {
                    // System.out.printf("Check [%s;%s], peaks not found, range:%s, flags:%s\n", startIndex, stopIndex, range, flags);

                } else {

                    int peak = 0;
                    for (int j = startIndex; j <= stopIndex; j++) {
                        if (dist[j] == 0) {
                            continue;
                        }
                        peak++;
                        if (range <= (dist[j] + prevDist)) {
                            prevDist = 0;
                            flags++;

                        } else {
                            prevDist += dist[j];

                        }
                        if (peaksCount == peak) {
                            break;
                        }
                    }
                }
                if (flags == peakCount || flags == range) {
                    break;
                }
            }
            maxCount = Math.max(maxCount, flags);
            if (maxCount == peakCount) {
                break;
            }
            range--;
        }
        return maxCount;

    }
}
