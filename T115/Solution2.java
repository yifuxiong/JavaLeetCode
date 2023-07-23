package T115;

// 方法二：（递归+减枝）-> 动态规划
// https://leetcode-cn.com/problems/distinct-subsequences/solution/dong-tai-gui-hua-by-powcai-5/
public class Solution2 {
    public int numDistinct(String s, String t) {
        int m = t.length(), n = s.length();
        int[][] dp = new int[m + 1][n + 1];

        // 边界条件：第0行，第j列
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 1;
        }
        // 边界条件：第0列，第i行。由于声明dp时默认是0，所以不用写出来

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";

        String s2 = "babgbag";
        String t2 = "bag";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.numDistinct(s, t));
    }
}
