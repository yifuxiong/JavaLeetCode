package T583;

public class Solution {
    // 转变思路：找word1和word2中相同的最长子串
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return m + n - 2 * dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";

        String word11 = "leetcode";
        String word22 = "etco";

        Solution solut = new Solution();
        System.out.println(solut.minDistance(word1, word2));
    }
}
