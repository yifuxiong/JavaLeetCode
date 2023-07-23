package T16;

import java.util.Arrays;

public class Solution {
    // 题目说：找到【最接近】的，类似于二分查找的思路
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        if (n == 3) {
            return nums[0] + nums[1] + nums[2];
        }

        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int first = 0; first < n - 2; first++) {
            int left = first + 1, right = n - 1;
            while (left < right) {
                int total = nums[left] + nums[right] + nums[first];
                if (Math.abs(target - total) < Math.abs(target - ans)) {
                    ans = total;
                }
                if (total < target) {
                    left++;
                } else if (total > target) {
                    right--;
                } else {
                    return total;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        Solution solut = new Solution();
        System.out.println(solut.threeSumClosest(nums, target));
    }
}
