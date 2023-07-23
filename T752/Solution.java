package T752;

import java.util.*;

public class Solution {
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        // 死亡列表
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        int step = 0;
        // BSF队列
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        // 遍历过的字符串
        Set<String> seen = new HashSet<>();
        seen.add("0000");

        while (!queue.isEmpty()) {
            ++step;
            // 当前队列的大小
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 队列中poll一个出来
                String status = queue.poll();
                for (String nextStatus : get(status)) {
                    // 既没有使用过，也不在死亡列表中
                    if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        // 标记为使用过
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举status通过一次旋转得到的数字
    public List<String> get(String status) {
        List<String> ret = new ArrayList<>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        String[] deadends2 = {"8888"};
        String target2 = "0009";

        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "8888";

        Solution solut = new Solution();
        System.out.println(solut.openLock(deadends2, target2));
    }
}
