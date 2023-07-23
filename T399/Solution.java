package T399;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UnionFind {
    private int[] parent;

    // 指向父节点的权值
    private double[] weight;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.weight = new double[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1.0d;
        }
    }

    public void union(int x, int y, double value) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }

        parent[rootX] = rootY;
        // 关系式推导请见【参考代码】下方的示意图
        weight[rootX] = weight[y] * value / weight[x];
    }

    /**
     * 路径压缩
     *
     * @param x
     * @return 根节点x
     */
    public int find(int x) {
        if (x != parent[x]) {
            int origin = parent[x];
            parent[x] = find(parent[x]);
            weight[x] *= weight[origin];
        }
        return parent[x];
    }

    public double isConnected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return weight[x] / weight[y];
        } else {
            return -1.0d;
        }
    }
}

// 官方题解
public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();
        UnionFind unionFind = new UnionFind(2 * equationsSize);

        // 第一步：预处理，将变量的值与id进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第二步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        List<String> elist1 = new ArrayList<String>();
        elist1.add("a");
        elist1.add("b");
        List<String> elist2 = new ArrayList<String>();
        elist2.add("b");
        elist2.add("c");
        equations.add(elist1);
        equations.add(elist2);

        double[] values = {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        List<String> qlist1 = new ArrayList<>();
        qlist1.add("a");
        qlist1.add("c");
        List<String> qlist2 = new ArrayList<>();
        qlist2.add("b");
        qlist2.add("a");
        List<String> qlist3 = new ArrayList<>();
        qlist3.add("a");
        qlist3.add("e");
        List<String> qlist4 = new ArrayList<>();
        qlist4.add("a");
        qlist4.add("a");
        List<String> qlist5 = new ArrayList<>();
        qlist5.add("x");
        qlist5.add("x");
        queries.add(qlist1);
        queries.add(qlist2);
        queries.add(qlist3);
        queries.add(qlist4);
        queries.add(qlist5);

        Solution solut = new Solution();
        double[] res = solut.calcEquation(equations, values, queries);
        for (double r : res) {
            System.out.print(r + ", ");
        }
        System.out.println();
    }
}
