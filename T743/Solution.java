package T743;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 我的方法：没有考虑到全局最优，做出来的是局部最优
public class Solution {
    private int[][] distance;
    private Map<Integer, List<Integer>> hashMap;
    private int ans = 0;

    public int networkDelayTime(int[][] times, int n, int k) {
        int m = times.length;
        // n个节点，至少n-1条边
        if (m < n - 1) {
            return -1;
        }

        // 距离表
        distance = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            distance[times[i][0]][times[i][1]] = times[i][2];
        }
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print(distance[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        // 对应关系
        hashMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            List<Integer> list = hashMap.getOrDefault(times[i][0], new ArrayList<>());
            list.add(times[i][1]);
            hashMap.put(times[i][0], list);
        }
        System.out.println(hashMap);

        List<Integer> list = hashMap.get(k);
        if (list == null) {
            return -1;
        } else {
            for (int l : list) {
                dfs(k, l, 0);
            }
        }
        return ans;
    }

    public void dfs(int u, int v, int dis) {
        if (distance[u][v] == 0) {
            return;
        }
        dis += distance[u][v];
        ans = Math.max(ans, dis);
        distance[u][v] = 0;
        distance[v][u] = 0;
        List<Integer> list = hashMap.get(v);
        if (list == null) {
            return;
        }
        for (int l : list) {
            dfs(v, l, dis);
        }
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

        Solution solut = new Solution();
        System.out.println(solut.networkDelayTime(times5, n5, k5));
    }
}
