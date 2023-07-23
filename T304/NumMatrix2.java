package T304;

public class NumMatrix2 {
    // 二维前缀和
    protected int[][] presum;

    public NumMatrix2(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            presum = new int[m + 1][n + 1];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    presum[i + 1][j + 1] = presum[i + 1][j] + presum[i][j + 1] - presum[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return presum[row2 + 1][col2 + 1] - presum[row1][col2 + 1] - presum[row2 + 1][col1] + presum[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix2 numMatrix2 = new NumMatrix2(matrix);
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix2.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix2.sumRegion(1, 2, 2, 4));
    }
}
