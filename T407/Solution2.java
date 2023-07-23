package T407;

import java.util.LinkedList;
import java.util.Queue;

// 官方方法二：广度优先搜索
public class Solution2 {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        int maxHeight = 0;

        // 将maxHeight赋值为heightMap中的最大值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHeight = Math.max(maxHeight, heightMap[i][j]);
            }
        }

        // 将每个点的盛水高度事先设置为maxHeight
        int[][] water = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                water[i][j] = maxHeight;
            }
        }

        Queue<int[]> qu = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是边界
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    // 将边界点的盛水之后的高度赋值为原来的高度
                    if (water[i][j] > heightMap[i][j]) {
                        water[i][j] = heightMap[i][j];
                        // 存储边界坐标
                        qu.offer(new int[]{i, j});
                    }
                }
            }
        }

        while (!qu.isEmpty()) {
            int[] curr = qu.poll();
            int x = curr[0];
            int y = curr[1];
            for (int i = 0; i < 4; i++) {
                // 上右下左，依次遍历
                int nx = x + dirs[i], ny = y + dirs[i + 1];
                // 超出边界直接跳过
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                if (water[x][y] < water[nx][ny] && water[nx][ny] > heightMap[nx][ny]) {
                    // 调整盛水之后的高度
                    water[nx][ny] = Math.max(water[x][y], heightMap[nx][ny]);
                    qu.offer(new int[]{nx, ny});
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 盛水之后的高度water[i][j] - 本身高度heightMap[i][j]才是储水高度
                res += water[i][j] - heightMap[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] heightMap = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
        int[][] heightMap2 = {{3, 3, 3, 3, 3}, {3, 2, 2, 2, 3}, {3, 2, 1, 2, 3}, {3, 2, 2, 2, 3}, {3, 3, 3, 3, 3}};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.trapRainWater(heightMap2));
    }
}
