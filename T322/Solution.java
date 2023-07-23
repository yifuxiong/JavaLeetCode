package T322;

// T279改进
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int j = 0; j < n; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        if (dp[amount] > amount) {
            // 此时dp[amount]中是初始赋的最大值
            // 表示没有方案
            return -1;
        } else {
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        int[] coins2 = {2};
        int amount2 = 3;

        int[] coins3 = {1};
        int amount3 = 0;

        int[] coins4 = {1};
        int amount4 = 1;

        int[] coins5 = {1};
        int amount5 = 2;

        Solution solut = new Solution();
        System.out.println(solut.coinChange(coins, amount));
    }
}
