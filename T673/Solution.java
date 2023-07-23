package T673;

import java.util.Arrays;

public class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n;
        }

        int[] dp = new int[n];
        int[] g = new int[n];
        // 初始化，最短都应该是自己本身的长度，即1
        Arrays.fill(dp, 1);
        Arrays.fill(g, 1);

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        // dp[i]会被dp[j]+1替换
                        dp[i] = dp[j] + 1;
                        g[i] = g[j];
                    } else if (dp[i] == dp[j] + 1) {
                        // 前驱
                        g[i] += g[j];
                    }
                } // nums[i] <= nums[j], 那么dp[i]就是它本身1
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                ans += g[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        int[] nums2 = {2, 2, 2, 2, 2};

        Solution solut = new Solution();
        System.out.println(solut.findNumberOfLIS(nums));
    }
}
