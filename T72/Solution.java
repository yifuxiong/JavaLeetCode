package T72;

public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        char[] ch1 = word1.toCharArray();
        char[] ch2 = word2.toCharArray();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";

        Solution solut = new Solution();
        System.out.println(solut.minDistance(word1, word2));
    }
}
