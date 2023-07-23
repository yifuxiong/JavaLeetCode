package T227;

import java.util.Deque;
import java.util.LinkedList;

// 官方思路
public class Solution2 {
    // 这次没有括号，有空字符
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int n = s.length();

        char preSign = '+';
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            // 这里不能写else if，比如表达式3+2*2，你试一下，第一次num存储的3被覆盖了
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                // 如果当前字符不是数字，且不是空字符；或者是最后一个字符，即认为是遍历到了数字末尾
                // （如果没有这个末尾判断，比如3/2，遍历到2的时候就不会进行计算了，而是直接退出循环了），那么就开始计算
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    stack.push((int) (stack.pop() / num));
                }
                // 计算完之后，一定要改变当前运算符
                preSign = s.charAt(i);
                num = 0;
            }
        }
        System.out.println(stack);

        int ans = 0;
        for (int i: stack){
            ans += i;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = " 3 / 2 ";
        String s2 = " 5-2/1 + 3 -1";
        String s3 = "0- 2147483647";
        String s4 = "14/ 3 * 2";
        String s5 = "1 + 1 + 1";
        String s6 = "3 + 2 *2";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.calculate(s6));
    }
}
