package T879;

public class Solution {
    // 0-1背包问题
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) 1e9 + 7;
        int len = group.length;

        // 依次遍历group，人员数边界，利润边界
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        // 初始化：不管多少人，完成一个0利润的任务，都有一种方法
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }

        for (int i = 1; i <= len; i++) {
            int g = group[i - 1];
            int p = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= g) {
                        dp[i][j][k] += dp[i - 1][j - g][Math.max(k - p, 0)];
                        // 求余数
                        if (dp[i][j][k] >= mod) {
                            dp[i][j][k] -= mod;
                        }
                    }
                }
            }
        }
        return dp[len][n][minProfit];
    }

    public static void main(String[] args) {
        int n = 5, minProfit = 3;
        int[] group = {2, 2}, profit = {2, 3};

        int n2 = 10, minProfit2 = 5;
        int[] group2 = {2, 3, 5}, profit2 = {6, 7, 8};

        Solution solut = new Solution();
        System.out.println(solut.profitableSchemes(n2, minProfit2, group2, profit2));
    }
}
