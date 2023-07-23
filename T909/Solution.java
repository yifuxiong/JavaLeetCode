package T909;

import java.util.*;

public class Solution {
    // BFS
    public int snakesAndLadders(int[][] board) {
        // board是一个N*N的棋盘
        int N = board.length;
        // 二维数组转换成一维
        int[] newBoard = new int[N * N];

        // 初始化一维数组newBoard
        int flag = 1;  // 当前方向
        int j = 0;  // 当前列数
        int k = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (j >= 0 && j < N) {
                newBoard[((N - 1) - i) * N + k] = board[i][j];
                k = (k + 1) % N;
                j += flag;
            }
            // 换方向
            flag = -flag;
            j += flag;
        }

        // 打印一维数组
        for (int i = 0; i < N * N; i++) {
            System.out.print(newBoard[i] + " ");
        }
        System.out.println();

        Queue<Integer> queue = new LinkedList<>();
        // 队列中装的是index
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        // hashSet中也装的是index
        visited.add(0);

        int step = 0;
        while (!queue.isEmpty()) {
            // 每一层step+1
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                for (int nextIndex : getNextIndex(index, newBoard)) {
                    if (!visited.contains(nextIndex)) {
                        if (nextIndex == N * N - 1) {
                            return step;
                        }
                        queue.offer(nextIndex);
                        visited.add(nextIndex);
                    }
                }
            }
        }
        return -1;
    }

    public List<Integer> getNextIndex(int index, int[] newBoard) {
        List<Integer> ret = new ArrayList<>();

        int n = newBoard.length;
        // 这里就是x+1, ..., x+6
        for (int i = 1; i <= 6; i++) {
            if (index + i < n) {
                // 如果有梯子和蛇
                if (newBoard[index + i] > 0) {
                    ret.add(newBoard[index + i] - 1);
                } else {
                    ret.add(index + i);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        int[][] board2 = {
                {-1, -1},
                {-1, 3}
        };
        int[][] board3 = {
                {-1, -1, 19, 10, -1},
                {2, -1, -1, 6, -1},
                {-1, 17, -1, 19, -1},
                {25, -1, 20, -1, -1},
                {-1, -1, -1, -1, 15}
        };

        Solution solut = new Solution();
        System.out.println(solut.snakesAndLadders(board));
    }
}
