package T377;

import java.util.Arrays;

public class Solution {
    // 递归
    public int combinationSum4(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        int ans = 0;
        for (int num : nums) {
            ans += combinationSum4(nums, target - num);
        }
        return ans;
    }

    // 记忆化递归
    private int[] dp;

    public int combinationSum42(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;

        return dfs(nums, target);
    }

    public int dfs(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        if (dp[target] != -1) {
            // 表示dp[target]计算过了，那就可以返回结果了。dp[target]也正好是目标结果
            return dp[target];
        }

        int ans = 0;
        for (int num : nums) {
            ans += dfs(nums, target - num);
        }
        return ans;
    }

    // 动态规划
    public int combinationSum43(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        int ans = 0;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;

        int[] nums2 = {9};
        int target2 = 3;

        Solution solut = new Solution();
        System.out.println(solut.combinationSum4(nums, target));
        System.out.println(solut.combinationSum42(nums, target));
        System.out.println(solut.combinationSum43(nums, target));
    }
}
