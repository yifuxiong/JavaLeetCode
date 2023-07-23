package T740;

import java.util.Arrays;

public class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }

        int n = Arrays.stream(nums).max().getAsInt();
        int[] all = new int[n + 1];
        for (int num : nums) {
            all[num]++;
        }

        int[] dp = new int[n + 1];
        // 初始化: i<=1: dp[i] = all[i] * i;
        dp[0] = 0;
        dp[1] = all[1];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + all[i] * i);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 2};
        int[] nums2 = {2, 2, 3, 3, 3, 4};
        int[] nums3 = {1, 1, 3, 3, 3, 5};

        Solution solut = new Solution();
        System.out.println(solut.deleteAndEarn(nums2));
    }
}
