package T123;

import java.util.Arrays;

/**
 * 首先buy[i][0]表示第i天没有买入股票，buy[i][1]表示第i天买入第1支股票
 * 同理sell[i][0]表示第i天没有卖出股票，sell[i][1]表示第i天卖出第1支股票
 * <p>
 * 状态方程：
 * buy[i][0] = Math.max(buy[i-1][0], sell[i-1][0] - prices[i])
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        int[][] buy = new int[n][2 + 1];
        int[][] sell = new int[n][2 + 1];

        // 先初始化，使buy和sell中的元素都为Integer中的最小值，即-2^32/2
        for (int i = 1; i <= 2; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }
        buy[0][0] = -prices[0];
        sell[0][0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= 2; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] prices3 = {7, 6, 4, 3, 1};
        int[] prices4 = {1};

        Solution solut = new Solution();
        System.out.println(solut.maxProfit(prices2));
    }
}
