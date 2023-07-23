package T773;

import java.util.*;

// 自己写的，思路是出来了，但是时间上还是有待优化
// 并且结果为-1的特殊用例不能退出循环，可能需要遍历所有情况才能退出
public class Solution {
    // BFS队列
    private Queue<int[]> queue;
    // 判断重复
    private Set<int[]> seen;

    public int slidingPuzzle(int[][] board) {
        // 2*3的矩阵
        int row = 2, col = 3;
        // 将二维数组转换成一维数组
        int[] newboard = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newboard[i * col + j] = board[i][j];
            }
        }

        // 目标状态{{1,2,3},{4,5,0}}
        int[] targetMatrix = new int[]{1, 2, 3, 4, 5, 0};
        if (isEqual(newboard, targetMatrix)) {
            return 0;
        }

        queue = new LinkedList<>();
        queue.offer(newboard);
        seen = new HashSet<>();
        seen.add(newboard);

        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] status = queue.poll();
                for (int[] nextStatus : getStatus(status)) {
                    printBoard(nextStatus);

                    if (!seen.contains(nextStatus)) {
                        if (isEqual(nextStatus, targetMatrix)) {
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

    // 判断两个一维数组是否相等
    public boolean isEqual(int[] board1, int[] board2) {
        if (board1.length != board2.length) {
            return false;
        }
        for (int i = 0; i < board1.length; i++) {
            if (board1[i] != board2[i]) {
                return false;
            }
        }
        return true;
    }

    // 与周围4个方向交换
    public int[] turnLeft(int[] curBoard, int x) {
        // 如果已经是左边界了，就移动不了
        // x == 0 or x == 3
        if (x == 0 || x == 3) {
            return curBoard;
        }
        int[] nextBoard = new int[curBoard.length];
        for (int i = 0; i < nextBoard.length; i++){
            nextBoard[i] = curBoard[i];
        }
        // x == 1,2 or 4,5
        int tmp = nextBoard[x - 1];
        nextBoard[x - 1] = nextBoard[x];
        nextBoard[x] = tmp;
        return nextBoard;
    }

    public int[] turnRight(int[] curBoard, int x) {
        // 如果已经是右边界了
        if (x == 2 || x == 5) {
            return curBoard;
        }
        int[] nextBoard = new int[curBoard.length];
        for (int i = 0; i < nextBoard.length; i++){
            nextBoard[i] = curBoard[i];
        }
        int tmp = nextBoard[x + 1];
        nextBoard[x + 1] = nextBoard[x];
        nextBoard[x] = tmp;
        return nextBoard;
    }

    public int[] turnUp(int[] curBoard, int x) {
        // 如果已经是上边界了
        if (x < 3) {
            return curBoard;
        }
        int[] nextBoard = new int[curBoard.length];
        for (int i = 0; i < nextBoard.length; i++){
            nextBoard[i] = curBoard[i];
        }
        int tmp = nextBoard[x - 3];
        nextBoard[x - 3] = nextBoard[x];
        nextBoard[x] = tmp;
        return nextBoard;
    }

    public int[] turnDown(int[] curBoard, int x) {
        // 如果已经是下边界了
        if (x > 2) {
            return curBoard;
        }
        int[] nextBoard = new int[curBoard.length];
        for (int i = 0; i < nextBoard.length; i++){
            nextBoard[i] = curBoard[i];
        }
        int tmp = nextBoard[x + 3];
        nextBoard[x + 3] = nextBoard[x];
        nextBoard[x] = tmp;
        return nextBoard;
    }

    public int getZeroPos(int[] curBoard) {
        int pos = 0;
        while (pos < curBoard.length) {
            if (curBoard[pos] != 0) {
                pos++;
            } else {
                break;
            }
        }
        return pos;
    }

    // 通过一次交换能够得到的状态
    public List<int[]> getStatus(int[] curBoard) {
        List<int[]> ret = new ArrayList<>();
        // 得到当前0的位置
        int x = getZeroPos(curBoard);

        int[] board = turnLeft(curBoard, x);
        ret.add(board);
        board = turnRight(curBoard, x);
        ret.add(board);
        board = turnUp(curBoard, x);
        ret.add(board);
        board = turnDown(curBoard, x);
        ret.add(board);

        return ret;
    }

    public void printBoard(int[] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        int[][] board2 = {{1, 2, 3}, {5, 4, 0}};
        int[][] board3 = {{4, 1, 2}, {5, 0, 3}};
        int[][] board4 = {{3, 2, 4}, {1, 5, 0}};
        int[][] board5 = {{1, 2, 3}, {4, 5, 0}};

        Solution solut = new Solution();
        System.out.println(solut.slidingPuzzle(board4));
    }
}
