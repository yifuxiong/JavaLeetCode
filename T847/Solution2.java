package T847;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution2 {
    // 宫水三叶
    private int INF = 0x3f3f3f3f;

    // 方法一：状态压缩 + BFS
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int mask = 1 << n;

        // 初始化所有的(state, u)距离为正无穷
        int[][] dist = new int[mask][n];
        for (int i = 0; i < mask; i++) {
            Arrays.fill(dist[i], INF);
        }

        // 因为可以从任意起点出发，先将起始的起点状态入队，并设起点距离为0
        Deque<int[]> d = new ArrayDeque<>();  // state, u
        for (int i = 0; i < n; i++) {
            dist[1 << i][i] = 0;
            d.addLast(new int[]{1 << i, i});
        }

        // DFS过程，如果从点u能够到达i，则更新距离并进行入队
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
            int state = poll[0], u = poll[1], step = dist[state][u];
            if (state == mask - 1) {
                return step;
            }
            for (int i : graph[u]) {
                if (dist[state | (1 << i)][i] == INF) {
                    dist[state | (1 << i)][i] = step + 1;
                    d.addLast(new int[]{state | (1 << i), i});
                }
            }
        }
        return -1; // never
    }

    // 方法二：Floyd + 状态压缩DP
    public int shortestPathLength2(int[][] graph) {
        int n = graph.length;
        int mask = 1 << n;

        // FLoyd求两点间的最短路径
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                dist[i][j] = 1;
            }
        }

        for (int p = 0; p < n; p++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][p] + dist[p][j]) {
                        dist[i][j] = dist[i][p] + dist[p][j];
                    }
                }
            }
        }

        // DP过程，如果从i能够到j的话，使用i到j的最短距离（步长）来转移
        int[][] f = new int[mask][n];
        // 起始时，让所有状态的最短距离（步长）为正无穷
        for (int i = 0; i < mask; i++) {
            Arrays.fill(f[i], INF);
        }
        // 由于可以将任意点作为起点出发，可以将这些起点的最短距离（步长）设置为0
        for (int i = 0; i < n; i++) {
            f[1 << i][i] = 0;
        }

        // 枚举所有的state
        for (int state = 0; state < mask; state++) {
            // 枚举state中已经被访问过的点
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 0) {
                    continue;
                }
                // 枚举state中尚未被访问过的点
                for (int j = 0; j < n; j++) {
                    if (((state >> j) & 1) == 1) {
                        continue;
                    }
                    f[state | (1 << j)][j] = Math.min(f[state | (1 << j)][j], f[state][i] + dist[i][j]);
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, f[mask - 1][i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
        int[][] graph2 = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.shortestPathLength(graph));
        System.out.println(solut2.shortestPathLength2(graph));
    }
}
