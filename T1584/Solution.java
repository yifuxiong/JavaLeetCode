package T1584;

import java.util.*;

// Prim & Kruskal
// 这里是 Kruskal
public class Solution {
    List<Edge> edges = new ArrayList<>();
    Pos[] pos;


    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        solve(points, n);

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge edge1, Edge edge2) {
                return edge1.len - edge2.len;
            }
        });
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (dsu.unionSet(x, y)) {
                ret += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ret;
    }

    public void solve(int[][] points, int n){
        pos = new Pos[n];
        for (int i = 0; i < n; i++) {
            pos[i] = new Pos(i, points[i][0], points[i][1]);
        }
        build(n);
        for (int i = 0; i < n; i++) {
            int temp = pos[i].x;
            pos[i].x = pos[i].y;
            pos[i].y = temp;
        }
        build(n);
        for (int i = 0; i < n; i++) {
            pos[i].x = -pos[i].x;
        }
        build(n);
        for (int i = 0; i < n; i++) {
            int temp = pos[i].x;
            pos[i].x = pos[i].y;
            pos[i].y = temp;
        }
        build(n);
    }

    public void build(int n){
        Arrays.sort(pos, new Comparator<Pos>() {
            public int compare(Pos pos1, Pos pos2) {
                return pos1.x == pos2.x ? pos1.y - pos2.y : pos1.x - pos2.x;
            }
        });
        int[] a = new int[n];
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            a[i] = pos[i].y - pos[i].x;
            set.add(pos[i].y - pos[i].x);
        }
        int num = set.size();
        int[] b = new int[num];
        int index = 0;
        for (int element : set) {
            b[index++] = element;
        }
        Arrays.sort(b);
        BIT bit = new BIT(num + 1);
        for (int i = n - 1; i >= 0; i--) {
            int poss = binarySearch(b, a[i]) + 1;
            int j = bit.query(poss);
            if (j != -1) {
                int dis = Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y);
                edges.add(new Edge(dis, pos[i].id, pos[j].id));
            }
            bit.update(poss, pos[i].x + pos[i].y, i);
        }
    }

    public int binarySearch(int[] array, int target){
        int low= 0, high =array.length - 1;
        while (low < high){
            int mid = (low + high)/ 2;
            int num = array[mid];
            if (num < target){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        Solution solut = new Solution();
        System.out.println(solut.minCostConnectPoints(points));
    }
}


class DisjointSetUnion{
    int[] f;
    int[] rank;
    int n;


    public DisjointSetUnion(int n){
        this.n = n;
        this.rank = new int[n];
        // Arrays.fill(this.rank, 1);
        // rank的填充放到下面，和f同步
        this.f = new int[n];
        for (int i = 0; i < n; i++){
            this.f[i] = i;
            this.rank[i] = 1;
        }
    }

    public int find(int x){
        if (x != f[x]){
            f[x] = find(f[x]);
        }
        return f[x];
    }

    public boolean unionSet(int x, int y){
        int fx = find(x), fy = find(y);
        if (fx == fy){
            return false;
        }

        // 如果rank[fx] < rank[fy]，二者交换
        if (rank[fx] < rank[fy]){
            int temp = fx;
            fx = fy;
            fy = temp;
        }

        // 高分支直接加上矮分支的高度？
        rank[fx] += rank[fy];
        // 高度更深的那个节点作为根节点
        f[fy] = fx;
        return true;
    }
}


class BIT{
    int[] tree;
    int[] idRec;
    int n;

    public BIT(int n){
        this.n = n;
        this.tree = new int[n];
        Arrays.fill(tree, Integer.MAX_VALUE);
        this.idRec = new int[n];
        Arrays.fill(idRec, -1);
    }

    public int lowbit(int k){
        return k&(-k);
    }

    public void update(int pos, int val, int id){
        while(pos > 0){
            if (tree[pos] > val){
                tree[pos] = val;
                idRec[pos] = id;
            }
            pos -= lowbit(pos);
        }
    }

    public int query(int pos){
        int minval = Integer.MAX_VALUE;
        int j = -1;
        while(pos < n){
            if (minval > tree[pos]){
                minval = tree[pos];
                j = idRec[pos];
            }
            pos += lowbit(pos);
        }
        return j;
    }
}

class Edge{
    int len, x, y;

    public Edge(int len, int x, int y){
        this.len = len;
        this.x = x;
        this.y = y;
    }
}

class Pos{
    int id, x, y;

    public Pos(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
}


