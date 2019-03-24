package com.moktar.codilitysolutions.lesson7;

/**
 * A string S consisting of N characters is considered to be properly nested if any of the following
 * conditions is true:
 * <p>
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(String S); }
 * <p>
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0
 * otherwise.
 * <p>
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the
 * function should return 0, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..200,000];
 * string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 * <p>
 * Created on 09.09.2018
 *
 * @author Ruslan Peshchuk (peshrus@gmail.com)
 * @see <a href="https://app.codility.com/demo/results/trainingU4UXZH-6X4/?showingAll=1">The first
 * result</a>
 */
public class Brackets {

    public int solution(String S) {
        // write your code in Java SE 8
        int isNested = 1;
        char[] charArray = S.toCharArray();
        char[] stack = new char[charArray.length];
        int stackCurrentPosition = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (i == 0) {
                stack[0] = charArray[i];
            } else {
                if (charArray[i] == '}') {
                    if (stack[stackCurrentPosition] != '{') {
                        isNested = 0;
                        break;
                    } else {
                        stack[stackCurrentPosition] = 'a';
                        stackCurrentPosition--;
                    }
                } else if (charArray[i] == ']') {
                    if (stack[stackCurrentPosition] != '[') {
                        isNested = 0;
                        break;
                    } else {
                        stack[stackCurrentPosition] = 'a';
                        stackCurrentPosition--;
                    }
                } else if (charArray[i] == ')') {
                    if (stack[stackCurrentPosition] != '(') {
                        isNested = 0;
                        break;
                    } else {
                        stack[stackCurrentPosition] = 'a';
                        stackCurrentPosition--;
                    }
                } else {
                    // System.out.println(i+":"+charArray[i]);
                    stack[stackCurrentPosition + 1] = charArray[i];
                    stackCurrentPosition++;
                }
            }

        }
        return isNested;
    }
}
