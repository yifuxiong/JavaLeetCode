package T778;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // 深度优先遍历
    public int swimInWater(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int m = grid.length;
        int n = grid[0].length;

        int left = 0, right = 50 * 50 - 1;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            Deque<int[]> queue = new LinkedList<>();
            // 起点
            queue.offer(new int[]{0, 0});

            // seen默认是false
            boolean[] seen = new boolean[m * n];
            // 起点设置为访问过
            seen[0] = true;

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];

                // 4个方向遍历
                for (int i = 0; i < dirs.length; i++) {
                    // 移动后的坐标
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];

                    // 没越界，并且没有访问，并且下一位的高度与当前高度的差小于mid
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.max(grid[x][y], grid[nx][ny]) <= mid) {
                        // 把这个点装入队列
                        queue.offer(new int[]{nx, ny});
                        // 并设置当前点为访问过
                        seen[nx * n + ny] = true;
                    }
                }
            }
            // 如果某个方向上访问到终点
            if (seen[m * n - 1]) {
                // 那么ans为候选结果
                ans = mid;
                // right缩小范围
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 2}, {1, 3}};
        int[][] grid2 = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};

        Solution solut = new Solution();
        System.out.println(solut.swimInWater(grid2));
    }
}
