package T995;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int minKBitFlips(int[] A, int K) {
        // que记录A的下标
        Deque<Integer> que = new LinkedList<>();

        int ans = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (que.size() > 0 && i > que.peek() + K - 1) {
                que.removeFirst();
            }
            // 两种情况：
            // 1.本来是1，翻转奇数次变为0，所以需要再次翻转，放入队列
            // 2.本来是0，翻转偶数次还是0，所以需要再次翻转，放入队列
            if (que.size() % 2 == A[i]) {
                if (i + K > n) {
                    return -1;
                }
                que.add(i);
                ans += 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {0, 0, 0, 1, 0, 1, 1, 0};
        int k = 3;

        Solution solut = new Solution();
        System.out.println(solut.minKBitFlips(A, k));
    }
}
