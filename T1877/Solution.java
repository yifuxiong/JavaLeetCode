package T1877;

import java.util.Arrays;

public class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        if (n == 2) {
            return nums[0] + nums[1];
        }

        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < n / 2; i++) {
            maxSum = Math.max(maxSum, nums[i] + nums[n - i - 1]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 3};
        int[] nums2 = {3, 5, 4, 2, 4, 6};
        int[] nums3 = {};

        Solution solut = new Solution();
        System.out.println(solut.minPairSum(nums2));
    }
}
