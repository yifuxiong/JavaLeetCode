package T684;

public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];

        UnionFind unionFind = new UnionFind(edges.length + 1, res);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};

        Solution solut = new Solution();
        int[] ret = solut.findRedundantConnection(edges2);
        // 如何保证，res中存放的是最后一组节点？按顺序遍历自然就是最后一组
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
        System.out.println();
    }
}

class UnionFind {
    private int[] parent;
    public int[] res;

    // 初始化结构体
    public UnionFind(int len, int[] res) {
        this.res = res;
        parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }

    // 路径压缩
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 缔结联系
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootX] = rootY;
        } else {
            /**
             * 这一步是关键：
             * 他俩都已经有对应的根节点了，说明已经连接到树上了
             * 但是如果他俩的根节点不同，说明他俩之间的边可以去掉
             */
            this.res[0] = x;
            this.res[1] = y;
        }
    }
}
