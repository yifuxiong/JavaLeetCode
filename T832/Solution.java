package T832;

public class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 逆序
                int temp = A[i][j];
                A[i][j] = A[i][n - 1 - j];
                A[i][n - 1 - j] = temp;

                // 反转
                A[i][j] = (A[i][j] == 1 ? 0 : 1);
                // 避免j为奇数时，最中间的那个数反转两次
                if (j != n - 1 - j) {
                    A[i][n - 1 - j] = (A[i][n - 1 - j] == 1 ? 0 : 1);
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] A2 = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};

        Solution solut = new Solution();
        int[][] ans = solut.flipAndInvertImage(A);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
