package T150;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();

        for (String s: tokens) {
            if (s.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b + a);
            } else if (s.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (s.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b * a);
            } else if (s.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push((int) (b / a));
            } else {
                // 是数字
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        Solution solut = new Solution();
        System.out.println(solut.evalRPN(tokens3));
    }
}
