package Qian_Zhui_He;


// T304, T363, T930(这题主要是哈希表的优化方法)
public class Solution {
    // 二维前缀和模板（重点）
    public int maxSumSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 1. 预处理前缀和数组
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 【和是指对应的前缀和，值是指原数组中的值】
                // 当前格子（和） = 上方的格子（和） + 左边的格子（和） - 左上角的格子（和） + 当前格子（值）
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i][j];
            }
        }


        // 我们令左上角为(x1, y1)左下角为(x2, y2)
        // 2. 计算(x1, y1, x2, y2)这个矩形内的结果
        int ans = 0;
        for (int x1 = 1; x1 <= m; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = x1; x2 <= m; x2++) {
                    for (int y2 = y1; y2 <= n; y2++) {
                        ans = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
                    }
                }
            }
        }
        return ans;
    }
}
