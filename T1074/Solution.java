package T1074;

public class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        // 初始化，生成前缀和
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 打印看看
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }

        int count = 0;
        // 左上角(x1, y1)和右下角(x2, y2)在sum[][]中滑动
        for (int x1 = 1; x1 <= m; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = x1; x2 <= m; x2++) {
                    for (int y2 = y1; y2 <= n; y2++) {
                        int rect = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
                        if (rect == target) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int target = 0;

        int[][] matrix2 = {{1, -1}, {-1, 1}};
        int target2 = 0;

        int[][] matrix3 = {{100}};
        int target3 = 0;

        Solution solut = new Solution();
        System.out.println(solut.numSubmatrixSumTarget(matrix2, target2));
    }
}
