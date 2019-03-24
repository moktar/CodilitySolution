package com.moktar.codilitysolutions.lesson5;

/**
 * A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
 * <p>
 * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
 * <p>
 * For example, consider string S = CAGCCTA and arrays P, Q such that:
 * <p>
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 * The answers to these M = 3 queries are as follows:
 * <p>
 * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
 * The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
 * The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
 * Write a function:
 * <p>
 * class Solution { public int[] solution(String S, int[] P, int[] Q); }
 * <p>
 * that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.
 * <p>
 * Result array should be returned as an array of integers.
 * <p>
 * For example, given the string S = CAGCCTA and arrays P, Q such that:
 * <p>
 * P[0] = 2    Q[0] = 4
 * P[1] = 5    Q[1] = 5
 * P[2] = 0    Q[2] = 6
 * the function should return the values [2, 4, 1], as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..100,000];
 * M is an integer within the range [1..50,000];
 * each element of arrays P, Q is an integer within the range [0..N − 1];
 * P[K] ≤ Q[K], where 0 ≤ K < M;
 * string S consists only of upper-case English letters A, C, G, T.
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class GenomicRangeQuery {

    public int[] solution(String S, int[] P, int[] Q) {
        // write your code in Java SE 8
        // ACGT 1234
        // prefix sums for A C G
        // .. no need for T, since it is default case if others are 0

        int len = S.length();
        // 1-indexing for easy calculations
        int[] prefixA = new int[len + 1];
        int[] prefixC = new int[len + 1];
        int[] prefixG = new int[len + 1];

        populatePrefixArr(S, prefixA, prefixC, prefixG);

        return calculateMinImpact(P, Q, prefixA, prefixC, prefixG);
    }

    private void populatePrefixArr(String S, int[] prefixA, int[] prefixC, int[] prefixG) {
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == 'A') {
                prefixA[i + 1]++;
            } else if (c == 'C') {
                prefixC[i + 1]++;
            } else if (c == 'G') {
                prefixG[i + 1]++;
            }

            prefixA[i + 1] += prefixA[i];
            prefixC[i + 1] += prefixC[i];
            prefixG[i + 1] += prefixG[i];

        }
    }

    private int[] calculateMinImpact(int[] P, int[] Q, int[] prefixA, int[] prefixC, int[] prefixG) {
        int[] minImpactArr = new int[P.length];

        for (int i = 0; i < P.length; i++) {
            int start = P[i];
            // +1 since q is inclusive
            int end = Q[i] + 1;

            if (prefixA[end] - prefixA[start] > 0) {
                minImpactArr[i] = 1;
            } else if (prefixC[end] - prefixC[start] > 0) {
                minImpactArr[i] = 2;
            } else if (prefixG[end] - prefixG[start] > 0) {
                minImpactArr[i] = 3;
            } else {
                minImpactArr[i] = 4;
            }
        }

        return minImpactArr;
    }
}
