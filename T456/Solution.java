package T456;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        // 左边
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int j = 1; j < nums.length; j++) {
            min[j] = Math.min(min[j - 1], nums[j]);
        }
        // 右边
        Deque<Integer> stack = new LinkedList<>();
        for (int j = nums.length - 1; j >= 0; j--) {
            // 132模式，2比1小，直接出栈
            while (!stack.isEmpty() && stack.peek() <= min[j]) {
                stack.pop();
            }
            // 否则2比1大的情况下，比3小，则132模式成立
            if (!stack.isEmpty() && stack.peek() < nums[j]) {
                return true;
            }
            stack.push(nums[j]);
        }
        return false;
    }

    // 自己写的暴力解法
    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        int min = nums[0];
        // nums[i]就是那个3，nums[j]是2，min是1
        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                // 2大于1,2小于3
                if (min < nums[j] && nums[j] < nums[i]){
                    return true;
                }
            }
            // 3每移动一次，1也跟着变化
            min = Math.min(min, nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};  // false
        int[] nums2 = {3, 1, 4, 2};  // true
        int[] nums3 = {-1, 3, 2, 0};  // true

        Solution solut = new Solution();
        System.out.println(solut.find132pattern(nums));
        System.out.println(solut.find132pattern2(nums));
    }

}
