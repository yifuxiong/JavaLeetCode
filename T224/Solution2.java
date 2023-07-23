package T224;

import java.util.Deque;
import java.util.LinkedList;

// 官方：
public class Solution2 {
    // 简单的(), +, -变换号
    public int calculate(String s) {
        Deque<Integer> op = new LinkedList<>();
        int sign = 1;
        op.push(sign);


        int n = s.length();
        int ret = 0;
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = op.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -op.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                op.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                op.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "1 + 1";
        String s2 = "2-1 + 2";
        String s3 = "(1+(4+5+2)-3)+(6+8)";
        String s4 = "21 -2 +(14 -12 )";
        String s5 = "0 ";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.calculate(s4));
    }
}
