package T1631;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int minimumEffortPath(int[][] heights) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int m = heights.length;
        int n = heights[0].length;

        int left = 0, right = 999999;  // right为10^6 - 1
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
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
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
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int[][] heights2 = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        int[][] heights3 = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};

        Solution solut = new Solution();
        System.out.println(solut.minimumEffortPath(heights));

    }
}
