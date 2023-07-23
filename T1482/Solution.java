package T1482;

import java.util.Arrays;

public class Solution {
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

        int[] bloomDay2 = new int[]{1, 10, 3, 10, 2};
        int m2 = 3, k2 = 2;

        int[] bloomDay3 = new int[]{7, 7, 7, 7, 12, 7, 7};
        int m3 = 2, k3 = 3;

        int[] bloomDay4 = new int[]{1000000000, 1000000000};
        int m4 = 1, k4 = 1;

        int[] bloomDay5 = new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        int m5 = 4, k5 = 2;

        Solution solut = new Solution();
        System.out.println(solut.minDays(bloomDay5, m5, k5));
    }
}
