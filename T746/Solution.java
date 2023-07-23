package T746;

public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 1];
        // 初始状态，不加上cost都是0
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        Solution solut = new Solution();
        System.out.println(solut.minCostClimbingStairs(cost2));
    }
}
