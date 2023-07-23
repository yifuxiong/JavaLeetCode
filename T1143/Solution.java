package T1143;

public class Solution {
    // 动态规划
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        // 边界条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // 递归
    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }

        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();

        return dfs(c1, c2, 0, 0, 0);
    }

    public int dfs(char[] c1, char[] c2, int i, int j, int res) {
        int m = c1.length;
        int n = c2.length;

        // i和j是起始索引，若i>=m或者j>=n说明其中有一个字符串已经匹配完了，返回结果
        if (i >= m || j >= n) {
            return res;
        }

        if (c1[i] == c2[j]) {
            return dfs(c1, c2, i + 1, j + 1, res + 1);
        } else {
            return Math.max(dfs(c1, c2, i, j + 1, res), dfs(c1, c2, i + 1, j, res));
        }
    }

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        String text21 = "abc", text22 = "abc";
        String text31 = "abc", text32 = "def";

        Solution solut = new Solution();
        System.out.println(solut.longestCommonSubsequence(text1, text2));
        System.out.println(solut.longestCommonSubsequence2(text1, text2));
    }
}
