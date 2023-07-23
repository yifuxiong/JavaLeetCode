package Binary_sort_bianxing;

import java.util.Arrays;

// 山峰问题：T33(有重复值), T153
// 类二分法找左边界：T154, T278, T875, T852, T1011, T1231(leetcode已经锁了), T1482
// 爱吃香蕉的珂珂：T875
// 在 D 天内送达包裹的能力：T1011
// 制作 m 束花所需的最少天数：T1482
public class Solution {
    // 例题：T1482
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n) {
            return -1;
        } else if (m * k == n) {
            return Arrays.stream(bloomDay).max().getAsInt();
        }

        int left = Arrays.stream(bloomDay).min().getAsInt();
        int right = Arrays.stream(bloomDay).max().getAsInt();
        while (left < right) {
            int mid = (left + right) / 2;
            int count = getCount(bloomDay, mid, k);
            // 如果制作花的总数比m要大，则right要减小
            if (count >= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int getCount(int[] bloomDay, int mid, int k) {
        int count = 0;
        int tmp = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            // 小于等待的天数mid，表示可以采摘。
            if (bloomDay[i] <= mid) {
                // 相邻花的朵数
                tmp++;
            } else {
                // 不相邻了，中断了
                tmp = 0;
            }
            // k朵相邻的花才能制作一束
            if (tmp == k) {
                tmp = 0;  // 重新开始计数多少个相邻
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] bloomDay = new int[]{1, 10, 3, 10, 2};
        int m = 3, k = 1;

        Solution solut = new Solution();
        System.out.println(solut.minDays(bloomDay, m, k));
    }
}
