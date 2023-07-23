package T221;

/**
 * https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode-solution/
 */
public class Solution {
    // 暴力法
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }

        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个1作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);

                    // 计算可能的最大正方形的边长
                    int currentMaxSide = Math.min(rows - i, columns - j);

                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }


    /**
     * 动态规划
     * - 如果该位置的值是 0，则 dp(i, j) = 0，因为当前位置不可能在由 1 组成的正方形中；
     * - 如果该位置的值是 1，则 dp(i, j) 的值由其上方、左方和左上方的三个相邻位置的 dp 值决定。
     * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     * <p>
     * - 此外，还需要考虑边界条件。如果 i 和 j 中至少有一个为 0，则以位置 (i, j) 为右下角的最大正方形的边长只能是 1，因此 dp(i, j) = 1。
     */
    public int maximalSquare2(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    // 边界上
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }
                }
                maxSide = Math.max(dp[i][j], maxSide);
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        };
        /**
         * dp[][]如下
         *
         * 1  0  1  0  0
         * 1  0  1  1  1
         * 1  1  1  2  2
         * 1  0  0  1  0
         */

        char[][] matrix2 = {
                {'0', '1'},
                {'1', '0'}
        };

        char[][] matrix3 = {
                {'0'}
        };

        Solution solut = new Solution();
        System.out.println(solut.maximalSquare(matrix));
        System.out.println(solut.maximalSquare2(matrix));
    }
}
