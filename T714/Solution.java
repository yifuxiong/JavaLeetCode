package T714;

public class Solution {
    // 按照官方思路自己写一遍
    /**
     * 状态方程
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i] - fee)
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 初始状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }


    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        int[] prices2 = {8, 7, 2, 5, 6, 1, 4, 4, 5};
        int fee2 = 2;

        int[] prices3 = {1, 3, 7, 5, 10, 3};
        int fee3 = 3;

        Solution solut = new Solution();
        System.out.println(solut.maxProfit(prices, fee));
    }
}
