package T54;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();

        int m = matrix.length;
        if (m == 0){
            return ret;
        }
        int n = matrix[0].length;

        int up = 0;
        int down = m - 1;
        int left = 0;
        int right = n - 1;

        while(true){
            for (int i = left; i <= right; i++){
                ret.add(matrix[up][i]);
            }
            if (++up > down) break;
            for (int i = up; i <= down; i++){
                ret.add(matrix[i][right]);
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--){
                ret.add(matrix[down][i]);
            }
            if (--down < up) break;
            for (int i = down; i>=up; i--){
                ret.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        Solution solut = new Solution();
        System.out.println(solut.spiralOrder(matrix2));
    }
}
