package T1047;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public String removeDuplicates(String S) {
        Deque<Character> stack = new LinkedList<>();

        int i = 0;
        while (i < S.length()) {
            if (!stack.isEmpty() && stack.peekLast() == S.charAt(i)) {
                stack.pollLast();
            } else {
                stack.offerLast(S.charAt(i));
            }
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }

    public String removeDuplicates2(String S) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < S.length()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == S.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(S.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String S = "abbaca";
        String S2 = "aaaaaaaaa";

        Solution solut = new Solution();
        System.out.println(solut.removeDuplicates(S2));
        System.out.println(solut.removeDuplicates2(S2));
    }
}
