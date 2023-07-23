package 剑指Offer42连续子数组的最大和;

public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // 动态规划
        int[] dp = new int[n];
        // 初始化
        dp[0] = nums[0];

        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            // 要不就是前缀和，要不就是从当前nums[i]开始
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxValue = Math.max(maxValue, dp[i]);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {0};
        int[] nums3 = {1};
        int[] nums4 = {-2, 1};  // 1
        int[] nums5 = {-2, -1};  // -1
        int[] nums6 = {1, 2};

        Solution solut = new Solution();
        System.out.println(solut.maxSubArray(nums6));
    }
}
