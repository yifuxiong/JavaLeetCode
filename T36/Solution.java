package T36;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        List<Character> table = new ArrayList<>();
        // 行
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                } else {
                    if (!table.contains(board[i][j])) {
                        table.add(board[i][j]);
                    } else {
                        return false;
                    }
                }
            }
            table.clear();
        }

        // 列
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (board[i][j] == '.') {
                    continue;
                } else {
                    if (!table.contains(board[i][j])) {
                        table.add(board[i][j]);
                    } else {
                        return false;
                    }
                }
            }
            table.clear();
        }

        // 3*3个的九宫格
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                // (0, 0)
                if (board[x * 3][y * 3] != '.') {
                    if (!table.contains(board[x * 3][y * 3])) {
                        table.add(board[x * 3][y * 3]);
                    } else {
                        return false;
                    }
                }
                // (0, 1)
                if (board[x * 3][y * 3 + 1] != '.') {
                    if (!table.contains(board[x * 3][y * 3 + 1])) {
                        table.add(board[x * 3][y * 3 + 1]);
                    } else {
                        return false;
                    }
                }
                // (0, 2)
                if (board[x * 3][y * 3 + 2] != '.') {
                    if (!table.contains(board[x * 3][y * 3 + 2])) {
                        table.add(board[x * 3][y * 3 + 2]);
                    } else {
                        return false;
                    }
                }
                // (1, 0)
                if (board[x * 3 + 1][y * 3] != '.') {
                    if (!table.contains(board[x * 3 + 1][y * 3])) {
                        table.add(board[x * 3 + 1][y * 3]);
                    } else {
                        return false;
                    }
                }
                // (1, 1)
                if (board[x * 3 + 1][y * 3 + 1] != '.') {
                    if (!table.contains(board[x * 3 + 1][y * 3 + 1])) {
                        table.add(board[x * 3 + 1][y * 3 + 1]);
                    } else {
                        return false;
                    }
                }
                // (1, 2)
                if (board[x * 3 + 1][y * 3 + 2] != '.') {
                    if (!table.contains(board[x * 3 + 1][y * 3 + 2])) {
                        table.add(board[x * 3 + 1][y * 3 + 2]);
                    } else {
                        return false;
                    }
                }
                // (2, 0)
                if (board[x * 3 + 2][y * 3] != '.') {
                    if (!table.contains(board[x * 3 + 2][y * 3])) {
                        table.add(board[x * 3 + 2][y * 3]);
                    } else {
                        return false;
                    }
                }
                // (2, 1)
                if (board[x * 3 + 2][y * 3 + 1] != '.') {
                    if (!table.contains(board[x * 3 + 2][y * 3 + 1])) {
                        table.add(board[x * 3 + 2][y * 3 + 1]);
                    } else {
                        return false;
                    }
                }
                // (2, 2)
                if (board[x * 3 + 2][y * 3 + 2] != '.') {
                    if (!table.contains(board[x * 3 + 2][y * 3 + 2])) {
                        table.add(board[x * 3 + 2][y * 3 + 2]);
                    } else {
                        return false;
                    }
                }
                table.clear();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '.', '.', '.', '7', '9'}
        };

        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] board3 = {
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        };

        Solution solut = new Solution();
        System.out.println(solut.isValidSudoku(board3));
    }
}
