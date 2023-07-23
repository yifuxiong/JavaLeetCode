package T847;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int shortestPathLength(int[][] graph) {
        int INF = 0x3f3f3f3f;
        int n = graph.length;
        // 由于是无向图，因此是个对角阵
        int[][] edge = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    edge[i][j] = 0;  // 自己到自己的距离为0
                } else {
                    edge[i][j] = INF;  // 否则先初始化为无穷
                }
            }
        }

        // 初始化路径赋值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                edge[i][graph[i][j]] = 1;
            }
        }

        for (int p = 0; p < n; p++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (edge[i][p] + edge[p][j] < edge[i][j]) {
                        edge[i][j] = edge[i][p] + edge[p][j];
                    }
                }
            }
        }

        // 打印
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(edge[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        int ans = INF;
        for (int i = 0; i < n; i++) {
            // 依次从每个起点开始递归，找出其中的最短路径之和
            List<Integer> nodes = new ArrayList<>();
            List<String> visited = new ArrayList<>();
            ans = Math.min(ans, dfs(edge, i, nodes, visited,0, INF));
            System.out.println(nodes);
        }

        return ans;
    }

    public int dfs(int[][] edge, int start, List<Integer> nodes, List<String> visited, int dis, int INF){
        int n = edge.length;
        if (nodes.size() >= n){
            return dis;
        }

        int levelMinDis = INF;
        int index = start;
        int end = 0;
        String coord;
        while(end < n){
            if (start == end){
                end++;
            }
            coord = start + "," + end;
            if (!visited.contains(coord) && levelMinDis > edge[start][end]){
                levelMinDis = edge[start][end];
                index = end;  // 该层最短距离另一个节点的索引
            }
            end++;
        }
        // 起点
        coord = start + "," + index;
        visited.add(coord);
        if(!nodes.contains(start)) nodes.add(start);
        start = index;  // 终点变为下一次遍历的起点
        dis += levelMinDis;
        return dfs(edge, start, nodes, visited, dis, INF);
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
        int[][] graph2 = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};

        Solution solut = new Solution();
        System.out.println(solut.shortestPathLength(graph2));
    }
}
