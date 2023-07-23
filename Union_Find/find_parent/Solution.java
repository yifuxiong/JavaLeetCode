package Union_Find.find_parent;

import java.util.HashSet;
import java.util.Set;

class UnionFind {
    public int[] parent;

    public UnionFind(int len) {
        parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }

    // 路径压缩
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 建立联系
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // 将右边点的根节点设置为左边点
            parent[rootY] = rootX;
        }
    }
}

public class Solution {
    public int removeStones(int[][] stones) {
        if (stones.length <= 1) {
            return 0;
        }

        int len = stones.length;
        UnionFind unionFind = new UnionFind(len);
        // 1. 建立联系
        for (int i = 0; i < len; i++) {
            int index1 = stones[i][0];
            int index2 = stones[i][1];
            unionFind.union(index1, index2);
        }

        // 2. 找有几个不同的union
        Set<Integer> hashSet = new HashSet<>();
        int ret = 0;
        for (int i = 0; i < len; i++) {
            int parent = unionFind.parent[stones[i][0]];
            System.out.println("parent=" + parent);
            if (!hashSet.contains(parent)) {
                hashSet.add(parent);
            } else {
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        int[][] stones2 = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};

        Solution solut = new Solution();
        System.out.println(solut.removeStones(stones2));
    }
}

