package T1438;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int longestSubarray(int[] nums, int limit) {
        // 单调递增
        Deque<Integer> minQue = new LinkedList<>();
        // 单调递减
        Deque<Integer> maxQue = new LinkedList<>();

        int n = nums.length;
        int maxLen = 0;

        int left = 0, right = 0;
        while (right < n) {
            while (!minQue.isEmpty() && minQue.peekLast() > nums[right]) {
                minQue.pollLast();
            }
            while (!maxQue.isEmpty() && maxQue.peekLast() < nums[right]) {
                maxQue.pollLast();
            }
            minQue.offerLast(nums[right]);
            maxQue.offerLast(nums[right]);
            // 最大值减最小值超过limit，超过一次，left++
            while (!maxQue.isEmpty() && !minQue.isEmpty() && maxQue.peekFirst() - minQue.peekFirst() > limit) {
                if (nums[left] == minQue.peekFirst()) {
                    minQue.pollFirst();
                }
                if (nums[left] == maxQue.peekFirst()) {
                    maxQue.pollFirst();
                }
                left++;
            }
            // 否则right++，并求子数组的最大长度
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {8, 2, 4, 7};
        int limit = 4;

        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;

        int[] nums3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;

        int[] nums4 = {1, 5, 6, 7, 8, 10, 6, 5, 6};
        int limit4 = 4;

        Solution solut = new Solution();
        System.out.println(solut.longestSubarray(nums4, limit4));
    }
}
