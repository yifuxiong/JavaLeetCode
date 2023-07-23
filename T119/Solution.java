package T119;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[][] triangle = new int[rowIndex + 1][rowIndex + 1];
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < rowIndex + 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || i == j) {
                    triangle[i][j] = 1;
                } else {
                    triangle[i][j] = triangle[i - 1][j] + triangle[i - 1][j - 1];
                }
            }
        }
        printT(triangle, rowIndex + 1);

        for (int i = 0; i < rowIndex + 1; i++) {
            ans.add(triangle[rowIndex][i]);
        }
        return ans;
    }

    public void printT(int[][] triangle, int row) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(triangle[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solut = new Solution();
        System.out.println(solut.getRow(3));
    }
}
