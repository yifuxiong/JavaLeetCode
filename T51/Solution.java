package T51;

import java.util.*;

// 回溯法：N皇后问题I
public class Solution {
    List<List<String>> solutions;

    public List<List<String>> solveNQueens(int n) {
        solutions = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();  // 每列
        Set<Integer> diagonals1 = new HashSet<>();  // 从左上往右下，这条斜线
        Set<Integer> diagonals2 = new HashSet<>();  // 从左下往右上，这条斜线
        backTrace(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backTrace(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                // 剪枝
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                // 剪枝

                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);

                backTrace(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);

                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        int n = 4;
        int n2 = 8;

        Solution solut = new Solution();
        System.out.println(solut.solveNQueens(n));
    }
}
