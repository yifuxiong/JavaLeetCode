package T803;

class UnionFind {
    int[] f;
    int[] sz;

    public UnionFind(int n) {
        f = new int[n];
        sz = new int[n];

        for (int i = 0; i < n; i++) {
            f[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        if (x != f[x]) {
            f[x] = find(f[x]);
        }
        return f[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        f[rootX] = rootY;
        sz[rootY] += sz[rootX];  // 记录
    }

    // 多出来的函数，求每次掉落的砖块
    public int size(int x) {
        return sz[find(x)];
    }
}

// 官方解答
public class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int h = grid.length, w = grid[0].length;

        UnionFind unionFind = new UnionFind(h * w + 1);
        int[][] status = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                status[i][j] = grid[i][j];
            }
        }

        for (int i = 0; i < hits.length; i++) {
            status[hits[i][0]][hits[i][1]] = 0;
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i][j] == 1) {
                    if (i == 0) {
                        unionFind.union(h * w, i * w + j);
                    }
                    if (i > 0 && status[i - 1][j] == 1) {
                        unionFind.union(i * w + j, (i - 1) * w + j);
                    }
                    if (j > 0 && status[i][j - 1] == 1) {
                        unionFind.union(i * w + j, i * w + j - 1);
                    }
                }
            }
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] ret = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            if (grid[r][c] == 0) {
                continue;
            }
            int prev = unionFind.size(h * w);

            if (r == 0) {
                unionFind.union(c, h * w);
            }

            for (int[] direction : directions) {
                int dr = direction[0], dc = direction[1];
                int nr = r + dr, nc = c + dc;

                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    if (status[nr][nc] == 1) {
                        unionFind.union(r * w + c, nr * w + nc);
                    }
                }
            }

            int size = unionFind.size(h * w);
            ret[i] = Math.max(0, size - prev - 1);
            status[r][c] = 1;

        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = {{1, 0}};

        int[][] grid2 = {{1, 0, 0, 0}, {1, 1, 0, 0}};
        int[][] hits2 = {{1, 1}, {1, 0}};

        Solution solut = new Solution();
        int[] ret = solut.hitBricks(grid2, hits2);
        for (int r : ret) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}
