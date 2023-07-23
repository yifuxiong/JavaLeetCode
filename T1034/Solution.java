package T1034;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int n, m;
    boolean[][] visited;
    List<int[]> borders;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        borders = new ArrayList<>();
        dfs(grid, row, col, grid[row][col]);
        for (int[] border: borders){
            int i = border[0], j = border[1];
            grid[i][j] = color;
        }
        return grid;
    }

    public void dfs(int[][] grid, int x, int y, int originColor){
        // 越界
        if (x >= n || x < 0 || y >= m || y < 0){
            return;
        }
        // 如果访问过，直接返回
        if (visited[x][y] == true){
            return;
        }
        if (originColor != grid[x][y]){
            return;
        }

        // 判断是否为边界点
        int score = 0;
        if (x + 1 < n && grid[x + 1][y] == grid[x][y]){
            score += 1;
        }
        if (x - 1 >= 0 && grid[x - 1][y] == grid[x][y]){
            score += 1;
        }
        if (y + 1 < m && grid[x][y + 1] == grid[x][y]){
            score += 1;
        }
        if (y - 1 >= 0 && grid[x][y - 1] == grid[x][y]){
            score += 1;
        }
        if (score < 4){
            borders.add(new int[]{x, y});
        }
        visited[x][y] = true;

        dfs(grid, x + 1, y, originColor);
        dfs(grid, x - 1, y, originColor);
        dfs(grid, x, y + 1, originColor);
        dfs(grid, x, y - 1, originColor);
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1}, {1, 2}};
        int row = 0, col = 0, color = 3;

        Solution solut = new Solution();
        grid = solut.colorBorder(grid, row, col, color);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
