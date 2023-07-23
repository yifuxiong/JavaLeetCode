package T212;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 和Solution思路一样，优化一下
public class Solution2 {
    List<String> ans = new ArrayList<>();
    Set<String> set = new HashSet<>();
    char[][] board;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;
    boolean[][] vis = new boolean[15][15];

    public List<String> findWords(char[][] _board, String[] words) {
        board = _board;
        m = board.length;
        n = board[0].length;
        for (String w : words) {
            set.add(w);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = true;
                sb.append(board[i][j]);
                dfs(i, j, sb);
                vis[i][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return ans;
    }

    void dfs(int i, int j, StringBuffer sb) {
        if (sb.length() > 10) {
            return;
        }
        if (set.contains(sb.toString())) {
            ans.add(sb.toString());
            set.remove(sb.toString());
        }

        for (int[] d : dirs) {
            int dx = i + d[0], dy = j + d[1];
            if (dx < 0 || dx >= m || dy < 0 || dy >= n) {
                continue;
            }
            if (vis[dx][dy]) {
                continue;
            }
            vis[dx][dy] = true;
            sb.append(board[dx][dy]);
            dfs(dx, dy, sb);
            vis[dx][dy] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};

        char[][] board2 = {{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        String[] words2 = {"oa", "oaa"};

        char[][] board3 = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
        String[] words3 = {"eaabcdgfa", "eaafgdcba"};

        Solution2 solut2 = new Solution2();
        List<String> ans = solut2.findWords(board3, words3);
        System.out.println(ans);
    }
}
