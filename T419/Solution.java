package T419;

public class Solution {
    // 方法一：深度优先遍历
    // 类似于T200（岛屿的数量）
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    dfs(board, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] board, int x, int y) {
        // 越界
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        // 上下左右
        if (board[x][y] == 'X') {
            // 覆盖，以免重复访问
            board[x][y] = '.';
            dfs(board, x - 1, y);
            dfs(board, x + 1, y);
            dfs(board, x, y - 1);
            dfs(board, x, y + 1);
        }
    }

    // 方法一换循环来写（不用递归）
    public int countBattleships_gai(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '-';
                    // 横向
                    for (int k = j + 1; k < col && board[i][k] == 'X'; k++) {
                        board[i][k] = '-';
                    }
                    // 纵向
                    for (int k = i + 1; k < row && board[k][j] == 'X'; k++) {
                        board[k][j] = '-';
                    }
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法二：枚举起点
     * 题目进阶要求一次扫描算法，只使用O(1)额外空间，并且不修改甲板的值。
     * 因为题目中给定的两艘战舰之间至少有一个水平或垂直的空位分隔，任意两个战舰之间是不相邻的，
     * 因此我们可以通过枚举每个战舰的左上顶点即可统计战舰的个数。
     * 假设矩阵的行数为row，矩阵的列数col，矩阵中的位置(i,j)为战舰的左上顶点，需满足以下条件：
     * 满足当前位置所在的值 board[i][j] = 'X'；
     * 满足当前位置的左则为空位，即board[i][j-1] = '.'；
     * 满足当前位置的上方为空位，即board[i-1][j] = '.'；
     * 我们统计出所有战舰的左上顶点的个数即为所有战舰的个数。
     *
     * @param board
     * @return
     */
    public int countBattleships2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j] == 'X') {
                        continue;
                    }
                    count++;
                }
            }
        }
        return count;
    }
}
