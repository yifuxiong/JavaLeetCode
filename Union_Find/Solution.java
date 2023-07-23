package Union_Find;
// 类似T399, T547, T684, T803, T1202

import java.util.*;

// 先声明一个并查集类，这里拿T1202为例
class UnionFind {
    private int[] parent;
    private int[] level;

    // 构造方法中写初始结构体
    public UnionFind(int n) {
        parent = new int[n];
        level = new int[n];  // 以i为节点生成树，这个树的层数（或者高度）

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            level[i] = 1;
        }
    }

    /**
     * 找父节点，这个方法叫做路径压缩
     *
     * @param x
     * @return
     */
    public int findParent(int x) {
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    // 建立关系
    public void union(int x, int y) {
        int parentX = findParent(x);
        int parentY = findParent(y);

        if (parentX == parentY)
            return;

        // 如果他俩层次相等，随便取一个作为父节点
        if (level[parentX] == level[parentY]) {
            parent[parentX] = parentY;
            level[parentY]++;
        } else if (level[parentX] < level[parentY]) {
            parent[parentX] = parentY;
        } else {
            parent[parentY] = parentX;
        }
    }
}


public class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        int len = s.length();
        // 第一步：将任意交换的节点对输入并查集
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int x = pair.get(0);
            int y = pair.get(1);
            unionFind.union(x, y);
        }

        // 第二步：构建映射关系
        char[] charArray = s.toCharArray();
        // 需要一个将索引和字符数组对应起来的容器，哈希表最合适
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = unionFind.findParent(i);
            // 如果有这个小根堆了，那就把这个数加入到小根堆中进行排序
            if (hashMap.containsKey(root)) {
                // hashMap.get(root)是一个堆类型，所以要用offer()
                hashMap.get(root).offer(charArray[i]);
            } else {
                // 否则新建一个小根堆，把索引和堆都插入哈希表中
                PriorityQueue<Character> queue = new PriorityQueue<>();
                queue.offer(charArray[i]);
                hashMap.put(root, queue);
            }
        }

        // 第三步：重组字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            // 这一句不要忘掉，必须找到根节点，因为哈希表中的索引是按照根节点插入的
            int root = unionFind.findParent(i);
            sb.append(hashMap.get(root).poll());
        }
        return sb.toString();
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
        System.out.println(solut.smallestStringWithSwaps(s, pairs));
    }
}


