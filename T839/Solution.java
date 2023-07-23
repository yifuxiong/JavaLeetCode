package T839;

import java.util.HashMap;

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

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // size小了，就交换节点，换成size大的节点
        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        size[rootX] += size[rootY];
        --setCount;
    }

    public boolean isConnected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return true;
        } else {
            return false;
        }
    }
}

public class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        if (n < 2) {
            return n;
        }

        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // 已经关联
                if (unionFind.isConnected(i, j)) {
                    continue;
                }
                if (check(strs[i], strs[j]) == true) {  // 关联
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.setCount;
    }

    public boolean check(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int len = s1.length();
        int diffChar = 0;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffChar++;
                if (diffChar > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = {"tars", "rats", "arts", "star"};
        String[] strs2 = {"omv", "ovm"};
        String[] strs3 = {"abc", "abc"};
        String[] strs4 = {"qihcochwmglyiggvsqqfgjjxu", "gcgqxiysqfqugmjgwclhjhovi", "gjhoggxvcqlcsyifmqgqujwhi", "wqoijxciuqlyghcvjhgsqfmgg", "qshcoghwmglygqgviiqfjcjxu", "jgcxqfqhuyimjglgihvcqsgow", "qshcoghwmggylqgviiqfjcjxu", "wcoijxqiuqlyghcvjhgsqgmgf", "qshcoghwmglyiqgvigqfjcjxu", "qgsjggjuiyihlqcxfovchqmwg", "wcoijxjiuqlyghcvqhgsqgmgf", "sijgumvhqwqioclcggxgyhfjq", "lhogcgfqqihjuqsyicxgwmvgj", "ijhoggxvcqlcsygfmqgqujwhi", "qshcojhwmglyiqgvigqfgcjxu", "wcoijxqiuqlyghcvjhgsqfmgg", "qshcojhwmglyiggviqqfgcjxu", "lhogcgqqfihjuqsyicxgwmvgj", "xscjjyfiuglqigmgqwqghcvho", "lhggcgfqqihjuqsyicxgwmvoj", "lhgocgfqqihjuqsyicxgwmvgj", "qihcojhwmglyiggvsqqfgcjxu", "ojjycmqshgglwicfqguxvihgq", "sijvumghqwqioclcggxgyhfjq", "gglhhifwvqgqcoyumcgjjisqx"};

        Solution solut = new Solution();
        System.out.println(solut.numSimilarGroups(strs));
    }
}
