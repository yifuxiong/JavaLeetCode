package T239;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution {
    // 我自己的方法（超时）
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length + 1 - k];

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 先塞k个数进去
        int i = 0;
        while (i < k) {
            queue.offer(nums[i]);
            i++;
        }

        int index = 0;
        while (i < nums.length) {
            res[index++] = queue.peek();
            queue.remove(nums[i - k]);
            queue.add(nums[i++]);
        }
        // 最后一个添加进来
        res[index] = queue.peek();
        return res;
    }

    // 单调队列
    // 用 L, R 来标记窗口的左边界和右边界
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            // 新的数大于等于队尾元素的值
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 存储下标
            deque.offerLast(i);
        }
        // 这样队列中的数的大小就是从大到小排列

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        // 从k开始，继续遍历
        for (int i = k; i < n; i++) {
            // 新的数大于等于队尾元素的值
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 存储下标
            deque.offerLast(i);

            // 队列中的首元素（即nums的下标）是否在[L, R]中，如果不在则弹出队首的值
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // ans[0]已经填充了，从ans[1]开始
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    // 遍历打印返回数组的各个元素
    public static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        Solution solut = new Solution();
        int[] res = solut.maxSlidingWindow2(nums, k);
        printNums(res);
    }
}
