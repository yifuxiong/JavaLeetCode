package T1893;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 方法一：用一个表记录，暴力法
    public boolean isCovered(int[][] ranges, int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ranges.length; i++) {
            int[] range = ranges[i];
            for (int j = range[0]; j <= range[1]; j++) {
                if (!list.contains(j)) {
                    list.add(j);
                }
            }
        }
        // System.out.println(list);

        for (int i = left; i <= right; i++) {
            if (!list.contains(i)) {
                return false;
            }
        }
        return true;
    }

    // 方法二：差分数组
    public boolean isCovered2(int[][] ranges, int left, int right) {
        // 差分表
        // 由于left>=1，table.length==50+1
        // 又因为right下一个索引可能超过50，因此table.length再+1
        // length就变成52了
        int[] table = new int[52];
        for (int i = 0; i < ranges.length; i++) {
            int[] range = ranges[i];
            table[range[0]] += 1;  // left索引对应的值+1
            table[range[1] + 1] -= 1;  // right下一个索引对应的值-1
        }

        // 打印差分表
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
        System.out.println();

        // 前缀和
        int[] preSum = new int[52];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + table[i];
        }

        // 打印前缀和
        for (int i = 0; i < preSum.length; i++) {
            System.out.print(preSum[i] + " ");
        }
        System.out.println();

        for (int j = left; j <= right; j++) {
            if (preSum[j] == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] ranges = {{1, 2}, {3, 4}, {5, 6}};
        int left = 2, right = 5;

        int[][] ranges2 = {{1, 10}, {10, 20}};
        int left2 = 21, right2 = 21;

        Solution solut = new Solution();
        System.out.println(solut.isCovered(ranges2, left2, right2));
        System.out.println(solut.isCovered2(ranges2, left2, right2));
    }
}
