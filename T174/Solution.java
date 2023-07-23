package T174;

public class Solution {
    // 前缀和
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = dungeon[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + dungeon[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + dungeon[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(1, Math.min(dp[i - 1][j], dp[i][j - 1]) - dungeon[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // 返回右下角
        return dp[m - 1][n - 1] + 1;
    }

    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};

        Solution solut = new Solution();
        System.out.println(solut.calculateMinimumHP(dungeon));
    }
}
