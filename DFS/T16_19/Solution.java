package DFS.T16_19;

import java.util.Arrays;
import java.util.Vector;

public class Solution {
    public int[] pondSizes(int[][] land) {
        int m = land.length;
        int n = land[0].length;

        Vector<Integer> vector = new Vector<>();

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = dfs(land, i, j);
                if (a != 0) {
                    vector.add(a);
                }
            }
        }
        System.out.println(vector);

        int len = vector.size();
        int[] ans = new int[len];

        for (int i = 0; i < vector.size(); i++) {
            ans[i] = vector.get(i);
        }

        Arrays.sort(ans);

        return ans;
    }

    public int dfs(int[][] land, int i, int j) {
        // 在边界内
        if (i >= 0 && i < land.length && j >= 0 && j < land[0].length) {
            if (land[i][j] == 0) {
                land[i][j] = -1;
                return 1 + dfs(land, i - 1, j - 1) + dfs(land, i, j - 1) + dfs(land, i + 1, j - 1) + dfs(land, i + 1, j) + dfs(land, i - 1, j) + dfs(land, i, j + 1) + dfs(land, i + 1, j + 1) + dfs(land, i - 1, j + 1);
            } else {
                return 0;
            }
        } else {
            // 边界外
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] land = new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        };

        Solution solut = new Solution();
        int[] ans = solut.pondSizes(land);
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
