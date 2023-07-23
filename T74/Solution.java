package T74;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int up = 0, down = m - 1;
        int left = 0, right = n - 1;

        // 小于最小值或者大于最大值
        if (matrix[m - 1][n - 1] < target || matrix[0][0] > target) {
            return false;
        }

        // 确定行
        int colMid = (up + down) / 2;
        while (up <= down) {
            colMid = (up + down) / 2;
            if (matrix[colMid][0] == target) {
                return true;
            } else if (matrix[colMid][0] < target) {  // 后面几行
                if (colMid + 1 < m && matrix[colMid + 1][0] > target) {
                    break;
                } else {
                    up = colMid + 1;
                }
            } else if (matrix[colMid][0] > target) {  // 前面几行
                if (colMid - 1 >= 0 && matrix[colMid - 1][0] < target) {
                    colMid -= 1;
                    break;
                } else {
                    down = colMid - 1;
                }
            }
        }
        System.out.println("第" + colMid + "行");

        // 确定列
        // int rowMid = (left + right) / 2;
        while (left <= right) {
            int rowMid = (left + right) / 2;
            if (matrix[colMid][rowMid] == target) {
                return true;
            } else if (matrix[colMid][rowMid] < target) {  // 后面
                left = rowMid + 1;
            } else if (matrix[colMid][rowMid] > target) {  // 前面
                right = rowMid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        int target2 = 21;

        Solution solut = new Solution();
        System.out.println(solut.searchMatrix(matrix, target2));
    }
}
