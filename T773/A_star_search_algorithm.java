package T773;

import java.util.*;

/*
 * A*算法几个距离函数：
 *
 * G(x)是起点s到节点x的【实际】路径长度，G(x)并不是最短的。
 * H(x)是节点x到终点t的【估计】【最短】路径长度，称为启发式函数。
 * H*(x)是节点x到终点t的【实际】最短长度，过程中无法求出但可以估计。需要用H(x)估计H*(x)。
 * F(x)=G(x)+H(x)，即为起点s到终点t的【估计】路径长度。
 *
 * 我们总是跳出最小的F(x)对应的x进行搜索，因此A*算法需要优先队列来实现。
 * */
class AStar {
    // 曼哈顿距离
    // 每行每行的看，举个例子
    // 比如第一行{0, 1, 2, 1, 2, 3}，
    // 0在索引为0的位置，他与其他位置索引的曼哈顿距离，在二维数组上显示为
    // {0, 1, 2}
    // {1, 2, 3}
    // ...
    // 后面每行同理
    public static int[][] dist = {
            {0, 1, 2, 1, 2, 3},
            {1, 0, 1, 2, 1, 2},
            {2, 1, 0, 3, 2, 1},
            {1, 2, 3, 0, 1, 2},
            {2, 1, 2, 1, 0, 1},
            {3, 2, 1, 2, 1, 0}
    };

    public String status;
    public int f, g, h;

    public AStar(String status, int g) {
        this.status = status;
        this.g = g;
        this.h = getH(status);
        this.f = this.g + this.h;
    }

    public static int getH(String status) {
        int ret = 0;
        for (int i = 0; i < 6; i++) {
            if (status.charAt(i) != '0') {
                ret += dist[i][status.charAt(i) - '1'];
            }
        }
        return ret;
    }
}

public class A_star_search_algorithm {
    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(board[i][j]);
            }
        }
        String initial = sb.toString();
        if ("123450".equals(initial)) {
            return 0;
        }

        // lambda表达式表示的最小优先队列
        PriorityQueue<AStar> pq = new PriorityQueue<>((a, b) -> a.f - b.f);
        pq.offer(new AStar(initial, 0));
        Set<String> seen = new HashSet<>();
        seen.add(initial);

        while (!pq.isEmpty()) {
            AStar node = pq.poll();
            for (String nextStatus : get(node.status)) {
                if (!seen.contains(nextStatus)) {
                    if (nextStatus.equals("123450")) {
                        return node.g + 1;
                    }
                    pq.offer(new AStar(nextStatus, node.g + 1));
                    seen.add(nextStatus);
                }
            }
        }
        return -1;
    }

    // 枚举 status 通过一次交换操作得到的状态
    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        int x = status.indexOf('0');
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

        A_star_search_algorithm as = new A_star_search_algorithm();
        System.out.println(as.slidingPuzzle(board3));
    }
}
