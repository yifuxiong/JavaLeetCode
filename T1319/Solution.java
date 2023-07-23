package T1319;

// 这题我只需要判断连通分量就行了
class UnionFind {
    private int[] parent;
    private int[] size;
    public int setCount;  // 连通分量

    public UnionFind(int n) {
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * @param x
     * @param y
     * @return boolean
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (size[rootX] < size[rootY]) {
            // 交换
            int temp = size[x];
            size[x] = size[y];
            size[y] = temp;
        }

        parent[rootY] = rootX;
        size[x] += size[y];
        setCount--;
        return true;
    }

    public boolean connected(int x, int y) {
        if (find(x) == find(y)) {
            return true;
        } else {
            return false;
        }
    }
}

public class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        UnionFind unionFind = new UnionFind(n);
        for (int[] conn : connections) {
            unionFind.union(conn[0], conn[1]);
        }
        // 连通分量的个数-1就是边的个数
        return unionFind.setCount - 1;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] connections = {{0, 1}, {0, 2}, {1, 2}};

        int n2 = 6;
        int[][] connections2 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};

        int n3 = 6;
        int[][] connections3 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}};

        int n4 = 5;
        int[][] connections4 = {{0, 1}, {0, 2}, {3, 4}, {2, 3}};

        Solution solut = new Solution();
        System.out.println(solut.makeConnected(n4, connections4));

    }
}
