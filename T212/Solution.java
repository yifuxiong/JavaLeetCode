package T212;

import java.util.LinkedList;
import java.util.List;

// 通过41/42测试用例
public class Solution {
    List<String> ans = new LinkedList<>();

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length, n = board[0].length;
        for (String word : words) {
            if (word.length() > m * n) {
                continue;
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 确定起始字母位置，从该位置开始广度优先遍历
                    if (word.charAt(0) == board[i][j]) {
                        char[][] board_copy = new char[m][n];
                        for (int a = 0; a < m; a++) {
                            for (int b = 0; b < n; b++) {
                                board_copy[a][b] = board[a][b];
                            }
                        }
                        bfs(board_copy, i, j, word, 0);
                    }
                }
            }
        }
        return ans;
    }

    public void bfs(char[][] board_copy, int x, int y, String word, int index) {
        if (x < 0 || x >= board_copy.length || y < 0 || y >= board_copy[0].length) {
            return;
        }
        if (index < word.length() && board_copy[x][y] == word.charAt(index)) {
            board_copy[x][y] = '#';  // 覆盖掉
            if (index == word.length() - 1 && !ans.contains(word)) {
                ans.add(word);
            } else {
                bfs(board_copy, x - 1, y, word, index + 1);
                bfs(board_copy, x + 1, y, word, index + 1);
                bfs(board_copy, x, y - 1, word, index + 1);
                bfs(board_copy, x, y + 1, word, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};

        char[][] board2 = {{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        String[] words2 = {"oa", "oaa"};

        char[][] board3 = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
        String[] words3 = {"eaabcdgfa", "eaafgdcba"};

        Solution solut = new Solution();
        List<String> ans = solut.findWords(board, words);
        System.out.println(ans);
    }
}
