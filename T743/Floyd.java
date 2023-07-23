package T743;

public class Floyd {
    // 宫水三叶题解
    // Floyd(邻接矩阵)
    private int N = 110;
    private int[][] w = new int[N][N];
    private int INF = 0x3f3f3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {  // 自己到自己的路径长度为0
                    w[i][j] = 0;
                } else {  // 否则先初始化为无穷
                    w[i][j] = INF;
                }
            }
        }

        // 初始化路径赋值
        for (int[] t : times) {
            int u = t[0], v = t[1], distance = t[2];
            w[u][v] = distance;
        }

        // floyd
        for (int p = 1; p <= n; p++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (w[i][p] + w[p][j] < w[i][j]) {
                        w[i][j] = w[i][p] + w[p][j];
                    }
                }
            }
        }

        // 打印
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                System.out.print(w[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, w[k][i]);
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

        Floyd fld = new Floyd();
        System.out.println(fld.networkDelayTime(times, n, k));
    }
}
