package T766;

public class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1 || n == 1) {
            return true;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m && j + 1 < n && matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 进阶：如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (m == 1 || n == 1) {
            return true;
        }

        // 如果只能存储一行，那就创建一个临时数组，每次存储一行数据
        int[] temp = new int[n];
        // 第一次存储matrix的第一行数据
        for (int j = 0; j < n; j++) {
            temp[j] = matrix[0][j];
        }

        // 之后，每比较一次后，替换temp中的数据
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j + 1 < n && temp[j] != matrix[i][j + 1]) {
                    return false;
                }
            }
            // 替换temp
            for (int j = 0; j < n; j++) {
                temp[j] = matrix[i][j];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
        int[][] matrix2 = {{1, 2}, {2, 2}};
        int[][] matrix3 = {{1, 2, 3, 4}, {5, 1, 2, 3}, {10, 5, 1, 10}};
        int[][] matrix4 = {{18}, {66}};
        int[][] matrix5 = {{1, 2, 3, 4, 5, 6}};
        int[][] matrix6 = {{41, 45}, {81, 41}, {73, 81}, {47, 73}, {0, 47}, {79, 76}};

        Solution solut = new Solution();
        System.out.println(solut.isToeplitzMatrix(matrix6));
        System.out.println(solut.isToeplitzMatrix2(matrix6));
    }
}
