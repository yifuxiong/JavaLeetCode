package T59;

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int left = 0, right = n - 1;
        int up = 0, down = n - 1;
        int x = 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                matrix[up][i] = x++;
            }
            if (++up > down) break;
            for (int i = up; i <= down; i++) {
                matrix[i][right] = x++;
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                matrix[down][i] = x++;
            }
            if (--down < up) break;
            for (int i = down; i >= up; i--) {
                matrix[i][left] = x++;
            }
            if (++left > right) break;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 4;

        Solution solut = new Solution();
        int[][] ret = solut.generateMatrix(n);

        for (int[] r : ret) {
            for (int x : r) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
