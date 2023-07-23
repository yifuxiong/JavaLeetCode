package T851;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution2 {
    // 拓扑排序
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        // 0~n-1个节点
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }

        // 每个节点连接其他节点的数目
        int[] inDeg = new int[n];
        for (int[] r : richer) {
            // 将这些节点添加到自己的表中
            g[r[0]].add(r[1]);
            ++inDeg[r[1]];
        }

        // 初始化，每个节点默认指向自己
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }

        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                // 如果某个节点并没有连接到其他节点，
                // 那么这个节点默认指向自己
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g[x]) {
                // 如果y从它的亲戚x那儿，遇见了比它更富有，并且更加quiet的节点
                if (quiet[ans[x]] < quiet[ans[y]]) {
                    ans[y] = ans[x];
                }
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};

        Solution2 solut2 = new Solution2();
        int[] ans = solut2.loudAndRich(richer, quiet);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
