package T304;

public class NumMatrix {
    // 一维前缀和做一遍
    protected int[][] presum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            presum = new int[m][n + 1];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    presum[i][j + 1] = presum[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        // 从row1到row2，把每行的前缀和col2+1与前缀和col1的差值相加
        for (int i = row1; i <= row2; i++) {
            sum += presum[i][col2 + 1] - presum[i][col1];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}
