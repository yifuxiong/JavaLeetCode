package T743;

import java.util.Arrays;

public class Dijkstra {
    // 宫水三叶题解
    // 朴素 Dijkstra（邻接矩阵）
    private int N = 110, M = 6010;
    private int[][] w = new int[N][N];
    private int[] dist = new int[N];  // 点k到当前点i的最短距离dist[i]
    private boolean[] vis = new boolean[N];  // 是否访问过
    private int INF = 0x3f3f3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        // 初始化
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    w[i][j] = 0;
                } else {
                    w[i][j] = INF;
                }
            }
        }

        // 初始化路径赋值
        for (int[] t : times) {
            int u = t[0], v = t[1], distance = t[2];
            w[u][v] = distance;
        }

        // dijkstra
        Arrays.fill(vis, false);
        Arrays.fill(dist, INF);
        dist[k] = 0;  // 自己到自己的距离是0
        for (int p = 1; p <= n; p++) {
            int t = -1;
            for (int i = 1; i <= n; i++) {
                if (!vis[i] && (t == -1 || dist[i] < dist[t])) {
                    t = i;
                }
            }
            vis[t] = true;
            for (int i = 1; i <= n; i++) {
                dist[i] = Math.min(dist[i], dist[t] + w[t][i]);
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4, k = 2;

        int[][] times2 = {{1, 2, 1}};
        int n2 = 2, k2 = 1;

        int[][] times3 = {{1, 2, 1}};  // 有向图没有从起始点开始
        int n3 = 2, k3 = 2;

        int[][] times4 = {{1, 2, 1}, {2, 1, 3}};  // 两个节点闭环
        int n4 = 2, k4 = 2;

        int[][] times5 = {{1, 2, 1}, {2, 3, 2}, {1, 3, 2}};  // 局部最优
        int n5 = 3, k5 = 1;

        Dijkstra dijk = new Dijkstra();
        System.out.println(dijk.networkDelayTime(times5, n5, k5));
    }
}
