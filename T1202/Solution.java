package T1202;

import java.util.*;

// 建立一个并查集类，这里可以对比T399
class UnionFind {
    private int[] parent;

    /**
     * 以i为根节点的子树高度（引入了路径压缩以后该定义并不准确）
     */
    private int[] rank;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    // 调整x和y两个节点的关系
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        // 如果x和y的高度相同，随便取一个作为根节点
        if (rank[rootX] == rank[rootY]) {
            parent[rootX] = rootY;
            // 此时以rootY为根节点的树的高度仅加了1
            rank[rootY]++;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
            // 此时以rootY为根节点的树的高度不变
        } else {
            // 同理，此时以rootX为根节点的树的高度不变
            parent[rootY] = rootX;
        }
    }

    /**
     * 路径压缩
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}


public class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 第一步：将任意交换的节点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        // pair的形式是{index1, index2}，这里分开取
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第二步：构建映射关系
        char[] charArray = s.toCharArray();
        // key: 连通分量的代表元， value: 同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (hashMap.containsKey(root)) {
                hashMap.get(root).offer(charArray[i]);
            } else {
                PriorityQueue<Character> minHeap = new PriorityQueue<>();
                minHeap.offer(charArray[i]);
                hashMap.put(root, minHeap);
                // 上面三行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
                // hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
            }
        }

        // 第三步：重组字符串
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            // 这里会默认每次poll出一个最小值
            stringBuffer.append(hashMap.get(root).poll());
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(3);
        pairs.add(list);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        pairs.add(list2);
//        List<Integer> list3 = new ArrayList<Integer>();
//        list3.add(0);
//        list3.add(2);
//        pairs.add(list3);

        String s2 = "cba";
        List<List<Integer>> pairs2 = new ArrayList<>();
        List<Integer> list21 = new ArrayList<>();
        list21.add(0);
        list21.add(1);
        pairs2.add(list21);
        List<Integer> list22 = new ArrayList<>();
        list22.add(1);
        list22.add(2);
        pairs2.add(list22);

        Solution solut = new Solution();
        System.out.println(solut.smallestStringWithSwaps(s2, pairs2));

    }
}
