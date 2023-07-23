package T79;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<String> visited;

    public boolean exist(char[][] board, String word) {
        visited = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                String str = "" + board[i][j];
                visited.add(new String(i + "," + j));
                if (backTrack(board, i, j, visited, str, word)) {
                    return true;
                }
                visited.remove(visited.size() - 1);
            }
        }
        return false;
    }

    public boolean backTrack(char[][] board, int i, int j, List<String> visited, String str, String word) {
        System.out.println(str);
        if (str.length() > word.length()) {
            return false;
        }
        if (str.length() == word.length() && str.equals(word)) {
            return true;
        }

        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
            if (i + 1 < board.length && !visited.contains(new String((i + 1) + "," + j))) {
                str += board[i + 1][j];
                visited.add(new String((i + 1) + "," + j));
                if (backTrack(board, i + 1, j, visited, str, word)) {
                    return true;
                }
                visited.remove(visited.size() - 1);
                str = str.substring(0, str.length() - 1);
            }
            if (i - 1 >= 0 && !visited.contains(new String((i - 1) + "," + j))) {
                str += board[i - 1][j];
                visited.add(new String((i - 1) + "," + j));
                if (backTrack(board, i - 1, j, visited, str, word)) {
                    return true;
                }
                visited.remove(visited.size() - 1);
                str = str.substring(0, str.length() - 1);
            }
            if (j + 1 < board[0].length && !visited.contains(new String(i + "," + (j + 1)))) {
                str += board[i][j + 1];
                visited.add(new String(i + "," + (j + 1)));
                if (backTrack(board, i, j + 1, visited, str, word)) {
                    return true;
                }
                visited.remove(visited.size() - 1);
                str = str.substring(0, str.length() - 1);
            }
            if (j - 1 >= 0 && !visited.contains(new String(i + "," + (j - 1)))) {
                str += board[i][j - 1];
                visited.add(new String(i + "," + (j - 1)));
                if (backTrack(board, i, j - 1, visited, str, word)) {
                    return true;
                }
                visited.remove(visited.size() - 1);
                str = str.substring(0, str.length() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        String word1 = "ABCB";

        char[][] board2 = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word2 = "SEE";

        char[][] board3 = new char[][]{
                {'A', 'A'}
        };
        String word3 = "AAA";

        char[][] board4 = new char[][]{
                {'A', 'B'}
        };
        String word4 = "BA";

        Solution solut = new Solution();
        System.out.println(solut.exist(board3, word3));
    }
}
