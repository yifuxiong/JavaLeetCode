package T1006;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    private char[] opts = {'*', '/', '+', '-'};

    public int clumsy(int N) {
        Deque<Integer> stack = new LinkedList<>();

        char preOpt = '+';
        int k = 0;
        while (N > 0) {
            if (preOpt == '+') {
                stack.push(N);
            } else if (preOpt == '-') {
                stack.push(-N);
            } else if (preOpt == '*') {
                stack.push(stack.pop() * N);
            } else if (preOpt == '/') {
                stack.push((int) (stack.pop() / N));
            }
            preOpt = opts[k];
            k = (k + 1) % 4;
            N = N - 1;
        }
        // System.out.println(stack);

        int res = 0;
        for (int i: stack){
            res += i;
        }
        return res;
    }


    public static void main(String[] args) {
        int N = 4;
        int N2 = 10;

        Solution solut = new Solution();
        System.out.println(solut.clumsy(N));
    }
}
