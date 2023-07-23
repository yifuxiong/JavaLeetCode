package T1579;

// 有size, setCount
class UnionFind {
    private int[] parent;
    private int[] size;
    public int setCount;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        setCount = n;

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

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        size[rootX] += size[rootY];
        setCount--;
        return true;
    }

    public int getCount() {
        return setCount;
    }

    public boolean isConnnected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        return rootX == rootY;
    }
}

public class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;

        // 节点编号改为从 0 开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }

        // 公共边
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufa.union(edge[1], edge[2])) {
                    ++ans;
                } else {
                    ufb.union(edge[1], edge[2]);
                }
            }
        }

        // 独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                // Alice 独占边
                if (!ufa.union(edge[1], edge[2])) {
                    ++ans;
                }
            } else if (edge[0] == 2) {
                // Bob 独占边
                if (!ufb.union(edge[1], edge[2])) {
                    ++ans;
                }
            }
        }

        if (ufa.setCount != 1 || ufb.setCount != 1) {
            return -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        // out: 2
        int n = 4;
        int[][] edges = {{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};

        // out: 0
        int n2 = 4;
        int[][] edges2 = {{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}};

        // out: -1
        int n3 = 4;
        int[][] edges3 = {{3, 2, 3}, {1, 1, 2}, {2, 3, 4}};

        Solution solut = new Solution();
        System.out.println(solut.maxNumEdgesToRemove(n3, edges3));
    }
}
