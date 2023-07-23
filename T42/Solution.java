package T42;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // 动态规划
    public int trap(int[] height) {

        return 0;
    }

    // 单调栈
    // 保存一个单调递减的栈
    public int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();  // 出栈
                if (stack.isEmpty()) {  // 出栈之后，栈为空直接退出
                    break;
                }

                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];  // 这里top相当于矩形的底
                ans += currHeight * currWidth;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};

        Solution solut = new Solution();
        System.out.println(solut.trap(height));
        System.out.println(solut.trap2(height));
    }
}
