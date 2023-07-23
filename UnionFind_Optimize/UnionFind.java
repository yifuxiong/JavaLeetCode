package UnionFind_Optimize;

// 优化的并查集（多了一个rank，按秩合并）
public class UnionFind {
    private int[] parent;
    // 按秩合并
    private int[] rank;

    public UnionFind(int len){
        parent = new int[len];
        rank = new int[len];

        for (int i = 0; i < len; i++){
            parent[i] = i;
            // 初始秩都设置为1
            rank[i] = 1;
        }
    }

    public int find(int x){
        if (x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 路径压缩
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY){
            return;
        }

        // 按秩合并
        if (rank[rootX] == rank[rootY]){
            parent[rootX] = rootY;
            // 此时以rootY为根节点的树的高度+1
            rank[rootY]++;
        }else if (rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
            // 此时以rootY为根节点的树的高度不变
        }else{
            parent[rootY] = rootX;
            // 此时以rootX为根节点的树的高度不变
        }
    }
}
