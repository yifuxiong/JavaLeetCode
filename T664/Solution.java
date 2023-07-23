package T664;

public class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            // 这一行代码初始化
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int minStep = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        minStep = Math.min(minStep, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = minStep;
                }
            }
        }

        // printDp(dp);
        return dp[0][n - 1];
    }

    public void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String s = "aaabbb";
        String s2 = "aba";

        Solution solut = new Solution();
        System.out.println(solut.strangePrinter(s));
    }
}
