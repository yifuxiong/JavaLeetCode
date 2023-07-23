package T959;

class UnionFind {
    private int[] parent;
    // 连通分量
    private int[] size;
    public int count;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        count = n;

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

        // 交换
        if (size[rootX] < size[rootY]) {
            int temp = size[x];
            size[x] = size[y];
            size[y] = temp;
        }
        // rootX是连通分量更大的那个
        parent[rootY] = rootX;
        size[x] += size[y];

        count--;
    }

    public int getCount() {
        return count;
    }
}

public class Solution {
    public int regionsBySlashes(String[] grid) {
        // grid的字符串个数，模拟成大正方形的边长，以每个小正方形为单位
        int N = grid.length;
        // 每个小正方形中的三角形个数
        int size = 4 * N * N;

        UnionFind unionFind = new UnionFind(size);
        // 遍历grid的每个字符串
        for (int i = 0; i < N; i++) {
            // 遍历grid每个字符串中的每个字符
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                // 二维网格转换为一维表格
                int index = 4 * (i * N + j);
                char c = row[j];

                // 单元格内合并
                if (c == '/') {
                    // 合并0 3，合并1 2
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {  // 注意：反斜杠是一个字符
                    // 合并0 1，合并2 3
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                } else {
                    // 空格，全部合并
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }

                // 单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                // 向下合并：2（当前）、0（下一行）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }

    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        String[] grid2 = {" /", "  "};
        String[] grid3 = {"\\/", "/\\"};
        String[] grid4 = {"/\\", "\\/"};
        String[] grid5 = {"//", "/ "};

        Solution solut = new Solution();
        System.out.println(solut.regionsBySlashes(grid5));
    }
}
