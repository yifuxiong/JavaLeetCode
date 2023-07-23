package T130;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 反过来思考：从边界入手
    // 如果边界有'O'的块，那么我们把这个块作为起点，向四周递归
    // 当遇到其它'O'的块，把遇到的这个块的坐标加入到列表中
    // 最后遍历board，如果是在列表中的坐标，那么不管它，其他的'O'全部换成'X'
    private List<String> table;

    public void solve(char[][] board) {
        table = new ArrayList<>();
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 边界位置
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

//        // 打印table
//        for (String s : table) {
//            System.out.println(s);
//        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!table.contains(i + "," + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (board[x][y] == 'O') {
            if (!table.contains(x + "," + y)) {
                table.add(x + "," + y);
                dfs(board, x - 1, y);
                dfs(board, x + 1, y);
                dfs(board, x, y - 1);
                dfs(board, x, y + 1);
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        Solution solut = new Solution();
        solut.solve(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
