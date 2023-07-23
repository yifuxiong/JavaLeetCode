package T846;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    // 方法一：数学方法
    // 不过这个方法有点问题，因为是取余，所以最终不能保证每组元素之间是连续的
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }

        int[] array = new int[groupSize];
        for (int h : hand) {
            array[h % groupSize]++;
        }

        for (int i = 0; i < groupSize - 1; i++) {
            if (array[i] != array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    // 方法二：宫水三叶【模拟 + 哈希表 + 优先队列（堆）】
    // 官方的思路和这个一样，只是仅仅用一个数组代替优先队列
    public boolean isNStraightHand2(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }

        // 哈希表维护每个元素个数
        Map<Integer, Integer> hashMap = new HashMap<>();
        // 优先队列维护元素顺序
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            hashMap.put(hand[i], hashMap.getOrDefault(hand[i], 0) + 1);
            queue.offer(hand[i]);
        }

        while (!queue.isEmpty()) {
            // 起始元素
            int top = queue.poll();
            if (hashMap.get(top) == 0) {
                continue;
            }
            // 每次取groupSize个元素
            for (int i = 0; i < groupSize; i++) {
                // 这里很巧妙：
                // 使得这groupSize个元素逐渐递增
                // 而不是用queue.poll()，因为队列中可能有相同元素
                int cnt = hashMap.getOrDefault(top + i, 0);
                if (cnt == 0) {
                    return false;
                }
                // 更新哈希表
                hashMap.put(top + i, cnt - 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        int[] hand2 = {1, 2, 3, 4, 5};
        int groupSize2 = 4;

        Solution solut = new Solution();
        System.out.println(solut.isNStraightHand(hand, groupSize));
        System.out.println(solut.isNStraightHand2(hand, groupSize));
    }
}
