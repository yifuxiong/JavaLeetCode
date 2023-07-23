package 剑指Offer04二维数组中的查找;

public class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }

        // 越界
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            // 二分查找
            int left = 0, right = n - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else if (matrix[i][mid] > target) {
                    right = mid;
                } else {
                    return true;
                }
            }
            if (matrix[i][left] == target){
                return true;
            }
        }

        if (n == 1) {
            // 对于n==1的情况
            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == target) {
                    return true;
                }
            }
        }
        if (m == 1) {
            // 对于m==1的情况
            for (int j = 0; j < n; j++) {
                if (matrix[0][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 5;

        int[][] matrix2 = {};
        int target2 = 0;


        int[][] matrix3 = {{1,4},{2,5}};
        int target3 = 4;

        Solution solut = new Solution();
        System.out.println(solut.findNumberIn2DArray(matrix3, target3));
    }
}
