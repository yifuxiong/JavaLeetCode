package T73;


public class Solution {
    // 空间复杂度：O(m+n)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 行是否存在0
        boolean[] rows = new boolean[m];
        // 列是否存在0
        boolean[] cols = new boolean[n];

        // 第一遍搜索0的位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        // 第二遍填充0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 空间复杂度：O(1)
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 两个标志位
        boolean rowFlag = false;
        boolean colFlag = false;

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }
        }

        // 从第二行第二列开始遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 第一行或者第一列，从matrix[i][j]延伸出的点置为0
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 根据第一行或者第一列原来本身具有的0，进行渗透
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 判断第一行或者第一列是否需要全部置为0
        if (rowFlag) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (colFlag) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] matrix2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

        Solution solut = new Solution();
        // solut.setZeroes(matrix2);
        // printMatrix(matrix2);

        solut.setZeroes2(matrix2);
        printMatrix(matrix2);
    }
}
