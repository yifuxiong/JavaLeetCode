package T1269;

public class Solution {
    private int N = (int) 1e9 + 7;

    // 想了一下，回溯法一定超时，只能动态规划
    public int numWays(int steps, int arrLen) {
        // 达到最远的位置steps/2，更远就回不来了
        int maxLen = Math.min(steps / 2, arrLen - 1);

        int[][] dp = new int[steps + 1][maxLen + 1];
        // 初始化
        dp[steps][0] = 1;

        for (int i = steps - 1; i >= 0; i--) {
            int edge = Math.min(i, maxLen);  // 时间复杂度进行优化
            for (int j = 0; j <= edge; j++) {
                // 原地
                dp[i][j] = (dp[i][j] + dp[i + 1][j]) % N;
                // 向右移动1
                if (j - 1 >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i + 1][j - 1]) % N;
                }
                // 向左移动1
                if (j + 1 <= maxLen) {
                    dp[i][j] = (dp[i][j] + dp[i + 1][j + 1]) % N;
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int steps = 3, arrLen = 2;
        int steps2 = 2, arrLen2 = 4;
        int steps3 = 4, arrLen3 = 2;

        Solution solut = new Solution();
        System.out.println(solut.numWays(steps3, arrLen3));
    }
}
