package T503;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // 暴力解法
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            while (j != i) {
                // 找到第一个比nums[i]大的数，添加到ans中去
                if (nums[i] < nums[j]) {
                    ans[i] = nums[j];
                    break;
                }
                // j循环遍历+1
                j = (j + 1) % n;
            }
            if (j % n == i) {
                ans[i] = -1;
            }
        }
        return ans;
    }

    // 单调栈
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // que中存储的是数组下标
        Deque<Integer> que = new LinkedList<>();
        // 循环数组一种实现方式是，把数组复制一份到数组末尾，
        // 这样虽然不是严格的循环数组，但是对于本题已经足够了，
        // 因为本题对数组最多遍历两次
        for (int i = 0; i < n * 2 - 1; i++) {
            // 如果队列不为空，且当前元素比栈顶元素大
            // 说明当前元素是前面一些元素的「下一个更大元素」
            while (!que.isEmpty() && nums[que.peek()] < nums[i % n]) {
                // 则逐个弹出栈顶元素，直到当前元素比栈顶元素小为止
                ans[que.pop()] = nums[i % n];
            }
            // 队列为空
            // 或者当前元素比栈顶元素小
            // 说明当前元素的「下一个更大元素」与栈顶元素相同
            que.push(i % n);  // 则把当前元素入栈
            System.out.println(que);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        int[] nums2 = {3, 1, 2, 4, 1, 2};

        Solution solut = new Solution();
        // 暴力解法
        int[] ans = solut.nextGreaterElements(nums2);
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println("←←←←← 暴力解法 ←←←←←");

        // 单调栈
        int[] ans2 = solut.nextGreaterElements2(nums2);
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(ans2[i] + " ");
        }
        System.out.println("←←←←← 单调栈 ←←←←←");
    }
}
