package 递增的三元子序列;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // 单调栈
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 单调栈，存储下标
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            // 如果后面的数比栈顶元素小，那么出栈
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            stack.push(i);
            System.out.println(stack);
        }
        return false;
    }

    // 一般做法
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            if (nums[i] <= small){
                small = nums[i];
            }else if (nums[i] <= mid){
                mid = nums[i];
            }else if (nums[i] > mid){
                // 比mid大，说明mid已经赋值过了
                // 并且给mid赋值前，small已经赋值过了
                // 因此，此时如果有比mid更大的数
                // 说明已经凑齐3个数了
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] nums2 = {5, 4, 3, 2, 1};
        int[] nums3 = {2, 1, 5, 0, 4, 6};
        int[] nums4 = {5, 2, 3, 1, 4};
        int[] nums5 = {};

        Solution solut = new Solution();
        System.out.println(solut.increasingTriplet(nums4));
        System.out.println(solut.increasingTriplet2(nums4));
    }
}
