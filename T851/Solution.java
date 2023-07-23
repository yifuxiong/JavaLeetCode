package T851;


class UnionFind {
    private int[] parent;  //
    private int[] rank;  // 层次级别（按秩合并的时候需要）

    public UnionFind(int len) {
        parent = new int[len];
        rank = new int[len];

        for (int i = 0; i < len; i++) {
            // 初始父节点都设置为自己
            parent[i] = i;
            // 初始秩的级别都设为1
            rank[i] = 1;
        }
    }

    /**
     * 查找根节点
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if (parent[x] != x) {
            return find(parent[x]);
        }
        return parent[x];
    }

    /**
     * 合并步骤，按秩合并从而减少查找过程中所访问的节点
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }

        // 否则，按秩合并
        if (rank[rootX] == rank[rootY]) {
            // 如果他们的层次级别相同
            // 随便将其中一个节点的根节点设置为另一个节点
            parent[rootX] = rootY;
            // 注意此时作为根节点的层次级别要+1
            rank[rootY] += 1;
        } else if (rank[rootX] < rank[rootY]) {
            // 否则将层次级别小的节点的根节点设置为层次级别大的节点
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
        }
    }

    /**
     * 判断两个节点是否相连
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}

class UnionFind_gai {
    int[] child;
    int[][] richer;
    int[] quiet;

    public UnionFind_gai(int[][] richer, int[] quiet) {
        int len = quiet.length;
        child = new int[len];
        this.richer = richer;
        this.quiet = quiet;

        for (int i = 0; i < len; i++) {
            child[i] = i;
        }
    }

    /**
     * 查找步骤。查找根节点
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if (child[x] != x) {
            child[x] = find(child[x]);
        }
        return child[x];
    }

    /**
     * 合并步骤。
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        // x的rich程度要高于y，则y至少要绑定x
        int childX = find(x);
        int childY = find(y);
        if (childY == y) {
            if (quiet[y] > quiet[x]) {
                child[y] = x;
            }
            if (quiet[childY] > quiet[childX]) {
                child[childY] = childX;
            }
        } else {
            if (childX != childY) {
                if (quiet[childX] < quiet[childY]) {
                    child[y] = childX;
                }
            }
        }
    }
}

public class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] ans = new int[n];

        UnionFind_gai ug = new UnionFind_gai(richer, quiet);
        for (int[] r : richer) {
            ug.union(r[0], r[1]);
        }

        for (int i = 0; i < n; i++) {
            ans[i] = ug.find(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};

        Solution solut = new Solution();
        int[] ans = solut.loudAndRich(richer, quiet);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
