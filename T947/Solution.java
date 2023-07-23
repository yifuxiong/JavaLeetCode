package T947;

import java.util.HashMap;
import java.util.Map;

class UnionFind {
    private Map<Integer, Integer> parent;
    private int count;

    public UnionFind() {
        parent = new HashMap<>();  // 这里parent是一个哈希表，不需要len了
        count = 0;
    }

    public int getCount() {
        return count;
    }

    // 路径压缩：在并查集里我们需要维护连通分量的个数，新创建顶点的时候连通分量加1
    public int find(int x) {
        // 并查集中新加入一个节点，节点的父节点是它自己，所以连通分量的总数+1
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            count++;
        }

        // 下面和之前一样，递归寻找父节点
        if (x != parent.get(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    // 建立连接：合并不在同一个连通分量中的两个并查集的时候，连通分量减1
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY){
            // 两个连通分量合并成一个，连通分量总数-1
            parent.put(rootX, rootY);
            count--;
        }
    }
}

public class Solution {
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone: stones){
            int index1 = stone[0];
            int index2 = stone[1];
            // 题目条件给出了index1, index2 大于等于0，小于等于10000
            unionFind.union(index1 + 10001, index2);
        }
        return stones.length - unionFind.getCount();
    }

    public static void main(String[] args) {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        int[][] stones2 = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        int[][] stones3 = {{0, 0}};
        int[][] stones4 = {{0, 1}, {1, 0}};

        Solution solut = new Solution();
        System.out.println(solut.removeStones(stones));
    }
}

