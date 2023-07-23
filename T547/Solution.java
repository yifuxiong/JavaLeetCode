package T547;

public class Solution {
    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        for (int i = 0; i < provinces; i++) {
            // 如果该点没有访问过
            if (!visited[i]) {
                dfs(isConnected, visited, provinces, i);
                count++;
            }
        }
        return count;
    }

    // 并查集
    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            // j城市与i城市相连，但是没有访问过，那么我就访问
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                // 再从j城市开始，去访问别的城市
                dfs(isConnected, visited, provinces, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        Solution solut = new Solution();
        System.out.println(solut.findCircleNum(isConnected));
    }
}
