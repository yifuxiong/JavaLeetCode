package T84;

import java.util.Deque;
import java.util.LinkedList;

// 官方解法
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // 从左往右
        Deque<Integer> mono_stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peekLast()] >= heights[i]) {
                mono_stack.pollLast();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peekLast());
            mono_stack.offerLast(i);
        }

        // 从右往左
        mono_stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peekLast()] >= heights[i]) {
                mono_stack.pollLast();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peekLast());
            mono_stack.offerLast(i);
        }

        // 计算矩形面积 = 宽 * 长
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};

        Solution solut = new Solution();
        System.out.println(solut.largestRectangleArea(heights));
    }
}
