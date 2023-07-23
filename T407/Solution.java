package T407;

import java.util.PriorityQueue;

// 官方解法一：最小堆
public class Solution {
    public int trapRainWater(int[][] heightMap) {
        // 构不成一个桶
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        // 是否访问过
        boolean[][] visit = new boolean[m][n];
        // 优先队列，从小到大排列
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是边界
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    /*
                     * 这个i*n+j的意思：
                     * 如果i==0, i*n+j==j，意思就是第0行，第j列
                     * 如果i==m-1, i*n+j就是经过前面(m-1)*n个数，遍历到最后一行的第j个数
                     * */
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});  // 索引转换成一维存储<索引，高度>
                    visit[i][j] = true;
                }
            }
        }

        int res = 0;
        // 4个方向
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            // 每次poll出一个边界点<索引，高度>
            int[] curr = pq.poll();
            for (int k = 0; k < 4; ++k) {
                // curr[0]是索引，将一维转换为二维
                // k从0到4，(x,y)->(dirs[k],dirs[k+1])分别对应上，右，下，左
                int nx = curr[0] / n + dirs[k];  // 第几行
                int ny = curr[0] % n + dirs[k + 1];  // 第几列

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    // curr[1]是该点的高度
                    if (curr[1] > heightMap[nx][ny]) {
                        // 由于边界点是一个单调队列存储，先出的肯定是高度较小的边界，
                        // 那么这个边界点可以作为储水标准，
                        // 将这个边界点高度(桶的最短版)与周围未访问节点相比，
                        // 如果边界点的高度比未访问点的高度要大，说明可以储水，
                        // 并计算储水量
                        res += curr[1] - heightMap[nx][ny];
                    }
                    // 访问该点之后，该点也作为边界点存储。高度是当前高度与边界点高度的较大值
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] heightMap = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
        int[][] heightMap2 = {{3, 3, 3, 3, 3}, {3, 2, 2, 2, 3}, {3, 2, 1, 2, 3}, {3, 2, 2, 2, 3}, {3, 3, 3, 3, 3}};

        Solution solut = new Solution();
        System.out.println(solut.trapRainWater(heightMap2));
    }
}
