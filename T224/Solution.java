package T224;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int calculate(String s) {

        Deque<String> stack = new LinkedList<>();
        char[] charArray = s.toCharArray();

        int i = 0;
        while (i < charArray.length) {
            // 空字符直接跳过
            if (charArray[i] == ' ') {
                i++;
            } else if (charArray[i] == ')') {
                // 声明一个新的计算队列
                Deque<String> calcuQueue = new LinkedList<>();
                while (!stack.peekLast().equals("(")) {
                    calcuQueue.offerFirst(stack.pollLast() + "");
                }
                if (stack.peekLast().equals("(")) {
                    // 去掉最内层的一个'('
                    stack.pollLast();
                    stack.offerLast(getResult(calcuQueue));
                }
                // 此时charArray[i] == ')'，去掉这个')'
                i++;
            } else {
                // '+', '-', '(', 数字
                stack.offerLast(charArray[i] + "");
                i++;
            }
        }
        return Integer.parseInt(getResult(stack));
    }

    public String getResult(Deque<String> calcuqueue) {
        int a = 0, b = 0;
        String op = "";

        while (!calcuqueue.isEmpty()) {
            // 不是+或-，是数字
            if (!calcuqueue.peekFirst().equals("+") && !calcuqueue.peekFirst().equals("-")) {
                if (!op.equals("")) {
                    // 如果op不为空字符串，说明a已经赋值了
                    b = b * 10 + Integer.parseInt(calcuqueue.pollFirst());
                    // 如果取完之后，队列刚好为空
                    if (calcuqueue.isEmpty()){
                        if (op.equals("+")) {
                            return (a + b) + "";
                        } else {
                            return (a - b) + "";
                        }
                    }
                } else {
                    a = a * 10 + Integer.parseInt(calcuqueue.pollFirst());
                }
            } else {
                if (!op.equals("")) {
                    // op已经赋值过，现在又遍历到了op，说明a和b已经赋值，可以开始计算了
                    if (op.equals("+")) {
                        calcuqueue.offerFirst((a + b) + "");
                    } else {
                        calcuqueue.offerFirst((a - b) + "");
                    }
                    a = 0;
                    b = 0;
                    op = "";
                } else {
                    op = calcuqueue.pollFirst();
                }
            }
            // System.out.println(calcuqueue);
        }
        return calcuqueue.pollFirst();
    }

    public static void main(String[] args) {
        String s = "1 + 1";
        String s2 = "2-1 + 2";
        String s3 = "(1+(4+5+2)-3)+(6+8)";
        String s4 = "21 -2 +(14 -12 )";
        String s5 = "0";

        Solution solut = new Solution();
        System.out.println(solut.calculate(s));
    }
}
