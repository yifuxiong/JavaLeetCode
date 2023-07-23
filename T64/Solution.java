package T64;

import java.util.Vector;

public class Solution {
    public int minPathSum(int[][] grid) {
        // 行数
        int m = grid.length;
        // 列数
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当左和上都是边界时，说明是左上角第一个格子
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                }
                // 左边是左边界，但上面不是上边界
                else if (j == 0 && i != 0) {
                    // 只能从上边来
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
                // 上面是上边界，但左边不是左边界
                else if (i == 0 && j != 0) {
                    // 只能从左边来
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                // 上面和左边都不是边界
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid2 = new int[][]{{1, 2, 3}, {4, 5, 6}};

        Solution solut = new Solution();
        System.out.println(solut.minPathSum(grid));
    }
}
