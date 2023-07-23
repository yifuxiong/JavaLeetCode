package T1190;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public String reverseParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        char[] arr = s.toCharArray();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(') {
                // push下标
                stack.push(i);
            }
            if (arr[i] == ')') {
                // 翻转上一个'('和当前')'之间的字符
                reverse(arr, stack.pop() + 1, i - 1);
            }
        }

        StringBuffer sb = new StringBuffer();
        // 去掉括号
        for (char a: arr){
            if (a == '(' || a == ')'){
                continue;
            }
            sb.append(a);
        }

        return sb.toString();
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "(abcd)";
        String s2 = "(u(love)i)";
        String s3 = "(ed(et(oc))el)";
        String s4 = "a(bcdefghijkl(mno)p)q";

        Solution solut = new Solution();
        System.out.println(solut.reverseParentheses(s3));
    }
}
