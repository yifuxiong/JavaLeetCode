package T63;

import java.util.Arrays;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 起点为障碍，返回0
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        // 上边界和左边界初始化赋值
        for (int i = 0; i < m; i++) {
            // 边界遇到一个障碍物，则后面全为0
            if (obstacleGrid[i][0] == 1) {
                while (i < m) {
                    dp[i][0] = 0;
                    i++;
                }
            } else dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            // 边界遇到一个障碍物，则后面全为0
            if (obstacleGrid[0][j] == 1) {
                while (j < n) {
                    dp[0][j] = 0;
                    j++;
                }
            } else dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 打印看看dp
        printDp(dp);

        // 总共的走法数减去其中越过障碍的走法数
        return dp[m - 1][n - 1];
    }

    // 打印看看
    public static void printDp(int[][] dp) {
        for (int[] row : dp) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] obstacleGrid2 = new int[][]{{0, 1}, {0, 0}};
        int[][] obstacleGrid3 = new int[][]{{1, 0}};
        int[][] obstacleGrid4 = new int[][]{{0, 0, 1, 0}};
        int[][] obstacleGrid5 = new int[][]{{0, 0}, {1, 1}, {0, 0}};

        Solution solut = new Solution();
        System.out.println(solut.uniquePathsWithObstacles(obstacleGrid));
    }
}
