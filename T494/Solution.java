package T494;

import java.util.Arrays;

public class Solution {
    // 0-1背包问题
    // 比如 nums=[1,2,3,4,5],target=3
    // 那么有非负集p=[1,3,5]，负集n=[2,4]，即p-n=target=3
    // 故(p+n)+(p-n)=target+(p+n)
    // 2*p=target+nums
    // p=(target+nums)/2，且(target+nums)一定是偶数
    // 即找一个非负集p，满足以上条件
    // 又转换成了0-1背包问题

    // 决策方程 dp[i][j] = dp[i−1][j] + dp[i−1][j−nums[i−1]]
    public int findTargetSumWays(int[] nums, int target) {
        // p=(target+nums)/2
        int sum = (target + Arrays.stream(nums).sum());
        if (sum - target < 0 || sum % 2 != 0) {
            return 0;
        }

        sum /= 2;
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        // 初始化：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    public int findTargetSumWays2(int[] nums, int target) {
        // p=(target+nums)/2
        int sum = (target + Arrays.stream(nums).sum());
        if (sum - target < 0 || sum % 2 != 0) {
            return 0;
        }

        sum /= 2;
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        // 初始化

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + 1);
                }
            }
        }
        return dp[n][sum];
    }

    public void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        int[] nums2 = {1};
        int target2 = 1;

        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 3;

        Solution solut = new Solution();
        System.out.println(solut.findTargetSumWays(nums, target));
        System.out.println(solut.findTargetSumWays2(nums, target));
    }
}
