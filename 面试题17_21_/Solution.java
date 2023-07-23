package 面试题17_21_;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode-cn.com/problems/volume-of-histogram-lcci/solution/zhi-fang-tu-de-shui-liang-by-leetcode-so-7rla/
public class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;

        // stack里面存放的是数组的下标
        stack.push(0);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 最外层出栈
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 次外层是left
                int left = stack.peek();

                // 如果后面的大于前面的，计算水容量
                int w = i - 1 - left;
                // 左边和右边的较小值 - top = (left or i)相对于top高出的一段
                int h = Math.min(height[left], height[i]) - height[top];
                ans += (w * h);
            }
            // 如果是递减，直接进栈
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 3, 2};
        int[] height3 = {};

        Solution solut = new Solution();
        System.out.println(solut.trap(height));
    }
}
