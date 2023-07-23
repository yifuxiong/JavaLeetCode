package T363;

public class Solution {
    // 朴素二维前缀和做一遍
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;

        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        for (int i =0; i <=m; i++){
            for (int j = 0; j <=n; j++){
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int p = i; p <= m; p++) {
                    for (int q = j; q <= n; q++) {
                        int cur = sum[p][q] - sum[i - 1][q] - sum[p][j - 1] + sum[i - 1][j - 1];
                        if (cur <= k) {
                            ans = Math.max(ans, cur);
                        }
                    }
                }
            }
        }
        return ans;
    }

    //
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
        int k = 2;

        int[][] matrix2 = {{2, 2, -1}};
        int k2 = 3;


        int[][] matrix3 = {{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}};
        int k3 = 3;

        Solution solut = new Solution();
        System.out.println(solut.maxSumSubmatrix(matrix3, k3));
    }
}
