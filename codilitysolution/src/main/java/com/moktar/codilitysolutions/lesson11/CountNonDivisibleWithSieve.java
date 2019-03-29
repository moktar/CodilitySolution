package com.moktar.codilitysolutions.lesson11;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * You are given an array A consisting of N integers.
 * <p>
 * For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
 * <p>
 * For example, consider integer N = 5 and array A such that:
 * <p>
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * For the following elements:
 * <p>
 * A[0] = 3, the non-divisors are: 2, 6,
 * A[1] = 1, the non-divisors are: 3, 2, 3, 6,
 * A[2] = 2, the non-divisors are: 3, 3, 6,
 * A[3] = 3, the non-divisors are: 2, 6,
 * A[4] = 6, there aren't any non-divisors.
 * Write a function:
 * <p>
 * class Solution { public int[] solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
 * <p>
 * Result array should be returned as an array of integers.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * the function should return [2, 4, 3, 2, 0], as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..50,000];
 * each element of array A is an integer within the range [1..2 * N].
 */
public class CountNonDivisibleWithSieve {

    private static boolean log = false;

    public int[] solution(int[] A) {
        //log("On input: %s", Arrays.toString(A));

        int[] sieve = new int[100_001];
        int i = 2;
        int j;
        for (; ; ) {
            j = i * i;
            if (j >= 100_001) {
                break;
            }
            int k = j;
            while (k < 100_001) {
                sieve[k] = i;
                k += i;
            }
            i++;
        }

        int[] counters = new int[100_001];
        for (int a : A) {
            counters[a]++;
        }

        return sieveAlg1(A, sieve, counters);
        //return sieveAlg2(A, sieve, counters);
    }

    private int[] sieveAlg1(int[] A, int[] sieve, int[] counters) {
        final int totalDividers = A.length;
        final int[] result = new int[A.length];
        final Set<Integer> divs = new HashSet<>();
        final LinkedList<Integer> queue = new LinkedList<>();

        for (int i1 = 0; i1 < A.length; i1++) {
            int a = A[i1];

            //log("\nCalculation started for %s, totalDividers:%s", a, totalDividers);

            divs.clear();

            int count = 0;
            divs.add(a);
            queue.push(a);
            count += counters[a];

            while (!queue.isEmpty()) {
                int next = queue.pollFirst();
                int factor = sieve[next];

                int nextSymmetric = a / next;
                if (divs.add(nextSymmetric)) {
                    queue.push(nextSymmetric);
                    count += counters[nextSymmetric];
                    //log("Removing a/x %s(%s), counter: %s", nextSymmetric, counters[nextSymmetric], count);
                }

                if (factor > 0) {

                    if (divs.add(factor)) {
                        queue.push(factor);
                        count += counters[factor];
                        //log("Removing factor %s(%s), counter: %s", factor, counters[factor], result[i]);
                    }

                    int symmetric = a / factor;
                    if (divs.add(symmetric)) {
                        queue.push(symmetric);
                        count += counters[symmetric];
                        //log("Removing a/factor %s(%s), counter: %s", symmetric, counters[symmetric], count);
                    }

                    int factorForNextSymmetric = next / factor;
                    if (divs.add(factorForNextSymmetric)) {
                        queue.push(factorForNextSymmetric);
                        count += counters[factorForNextSymmetric];
                        //log("Removing x/factor %s(%s), counter: %s", factorForNextSymmetric, counters[factorForNextSymmetric], count);
                    }
                }
                if (count == totalDividers) {
                    queue.clear();
                    break;
                }
            }
            result[i1] = totalDividers - count;
        }
        return result;
    }

    private int[] sieveAlg2(int[] A, int[] primeFactors, int[] counters) {
        final int totalDividers = A.length;
        final int[] result = new int[A.length];
        final Set<Integer> divs = new HashSet<>();
        final LinkedList<Integer> queue = new LinkedList<>();

        for (int k = 0; k < A.length; k++) {
            int a = A[k];

            //log("\nCalculation started for %s, totalDividers:%s", a, totalDividers);

            int count = 0;
            divs.clear();
            queue.push(a);

            while (!queue.isEmpty()) {
                int next = queue.pollLast();

                if (divs.add(next)) {
                    count += counters[next];
                    //log("Counting next %s(%s), counter: %s", next, counters[next], count);
                }

                int nextSymmetric = a / next;
                if (!divs.contains(nextSymmetric)) {
                    queue.push(nextSymmetric);
                    //log("Removing a/x %s(%s), counter: %s", nextSymmetric, counters[nextSymmetric], count);
                }

                int prime = primeFactors[next];
                if (prime > 0) {

                    if (!divs.contains(prime)) { // add primeFactor
                        queue.push(prime);
                        //log("Pushing prime %s(%s), counter: %s", prime, counters[prime], count);
                    }

                    int primeSymmetric = a / prime;
                    if (!divs.contains(primeSymmetric)) {
                        queue.push(primeSymmetric);
                        //log("Pushing primeSymmetric %s(%s), counter: %s", primeSymmetric, counters[primeSymmetric], count);
                    }

                    int primeToNextSymmetric = next / prime;
                    if (!divs.contains(primeToNextSymmetric)) {
                        queue.push(primeToNextSymmetric);
                        //log("Pushing primeToNextSymmetric %s(%s), counter: %s", next, counters[primeToNextSymmetric], count);
                    }
                }
                if (count == totalDividers) {
                    queue.clear();
                    break;
                }
            }
            result[k] = totalDividers - count;
        }
        return result;
    }

    private static void log(String str, Object... params) {
        if (log)
            System.out.printf(str + "%n", params);
    }
}
