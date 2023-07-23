package T227;

import java.io.IOException;
import java.util.*;

// 请不要使用内置库函数eval()
// JavaScript中有个eval()方法，可以将表达式带入直接算出结果
public class Solution {
    public int calculate(String s) {
        Deque<String> deque = new LinkedList<>();
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (s.charAt(i) != '*' && s.charAt(i) != '/' && s.charAt(i) != '+' && s.charAt(i) != '-') {
                num = num * 10 + (s.charAt(i) - '0');
            } else {
                deque.offer(String.valueOf(num));
                deque.offer(String.valueOf(s.charAt(i)));
                num = 0;
            }
        }
        // 最后一个是数，不是符号，添加到栈中
        deque.offer(String.valueOf(num));
        System.out.println(deque);

        // 去乘除
        Deque<String> queue = new LinkedList<>();
        if (deque.contains("*") || deque.contains("/")) {
            while (!deque.isEmpty()) {
                String n = deque.poll();
                if (n.equals("*")) {
                    long num1 = Long.parseLong(queue.pollLast());
                    long num2 = Long.parseLong(deque.poll());
                    queue.offer(String.valueOf(num1 * num2));
                } else if (n.equals("/")) {
                    long num1 = Long.parseLong(queue.pollLast());
                    long num2 = Long.parseLong(deque.poll());
                    queue.offer(String.valueOf(num1 / num2));
                } else {
                    queue.offer(n);
                }
            }
            System.out.println(queue);
        } else {
            while (!deque.isEmpty()) {
                queue.offer(deque.poll());
            }
        }

        // 全变为+或-进行从左到右的计算
        long num1 = 0, num2 = 0;
        while (queue.size() > 1) {
            String n = queue.poll();
            if (n.equals("+")) {
                num2 = Long.parseLong(queue.poll());
                queue.offerFirst(String.valueOf(num1 + num2));
            } else if (n.equals("-")) {
                num2 = Long.parseLong(queue.poll());
                queue.offerFirst(String.valueOf(num1 - num2));
            } else {
                num1 = Long.parseLong(n);
            }
            // System.out.println(queue);
        }
        return Integer.parseInt(queue.poll());
    }

    public static void main(String[] args) {
        String s = " 3 / 2 ";
        String s2 = " 5-2/1 + 3 -1";
        String s3 = "0- 2147483647";
        String s4 = "14/ 3 * 2";
        String s5 = "1 + 1 + 1";

        Solution solut = new Solution();
        System.out.println(solut.calculate(s5));
    }
}
