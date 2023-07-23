package T773;

import java.util.*;

public class Solution2 {
    // {0, 1, 2}
    // {3, 4, 5}
    // 这些是索引值！！！
    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    // 官方题解：BFS
    public int slidingPuzzle(int[][] board) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }

        String initial = sb.toString();
        if ("123450".equals(initial)) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(initial);
        Set<String> seen = new HashSet<>();
        seen.add(initial);

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String stauts = queue.poll();
                for (String nextStatus : get(stauts)) {
                    if (!seen.contains(nextStatus)) {
                        if ("123450".equals(nextStatus)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    public List<String> get(String status) {
        List<String> ret = new ArrayList<>();
        char[] array = status.toCharArray();

        // 获得当前'0'的位置索引
        int x = status.indexOf('0');
        // 遍历与当前索引值x相邻的位置索引
        for (int y : neighbors[x]) {
            swap(array, x, y);
            ret.add(new String(array));
            swap(array, x, y);
        }
        return ret;
    }

    public void swap(char[] array, int x, int y) {
        char temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        int[][] board2 = {{1, 2, 3}, {5, 4, 0}};
        int[][] board3 = {{4, 1, 2}, {5, 0, 3}};
        int[][] board4 = {{3, 2, 4}, {1, 5, 0}};
        int[][] board5 = {{1, 2, 3}, {4, 5, 0}};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.slidingPuzzle(board4));
    }
}
