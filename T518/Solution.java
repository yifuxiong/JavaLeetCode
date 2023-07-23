package T518;

public class Solution {
    public int change(int amount, int[] coins) {
        // 有n种不同面额的硬币
        int n = coins.length;
        // n种不同面额的硬币凑成amount的金额
        int[][] dp = new int[n + 1][amount + 1];
        // 初始化：0种不同面额的硬币凑成0金额
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                // 如果当前amount(也就是j)小于当前选中硬币的面额，那么就不取当前硬币
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                    // 否则选取当前硬币
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        int amount2 = 3;
        int[] coins2 = {2};

        int amount3 = 10;
        int[] coin3 = {10};

        Solution solut = new Solution();
        System.out.println(solut.change(amount, coins));
    }
}
