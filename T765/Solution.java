package T765;

class UnionFind {
    private int[] parent;
    private int[] size;
    // 连通分量
    public int setCount;

    public UnionFind(int n) {
        setCount = n;
        parent = new int[n];
        size = new int[n];

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

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }
        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        size[rootX] += size[rootY];
        // 合并分支数减一
        setCount--;
    }

    // 判断是否连接
    public boolean isConnected(int x, int y) {
        if (find(x) == find(y)) {
            return true;
        } else {
            return false;
        }
    }
}

public class Solution {
    public int minSwapsCouples(int[] row) {
        // row的长度是2N
        int N = row.length;
        int n = N / 2;
        UnionFind unionFind = new UnionFind(n);

        /**
         * 如果一对情侣恰好坐在了一起，并且坐在了成组的座位上，其中一个下标一定是偶数，另一个一定是奇数，
         * 并且「偶数的值 + 1 = 奇数的值」。例如编号数对 [2, 3]、[9, 8]，这些数对的特点是除以 2（下取整）得到的数相等。
         */
        for (int i = 0; i < N; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        return n - unionFind.setCount;
    }

    public static void main(String[] args) {
        int[] row = {0, 2, 1, 3};
        int[] row2 = {3, 2, 0, 1};
        int[] row3 = {5, 4, 2, 6, 3, 1, 0, 7};

        Solution solut = new Solution();
        System.out.println(solut.minSwapsCouples(row3));
    }
}
