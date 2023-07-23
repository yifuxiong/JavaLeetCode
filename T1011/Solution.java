package T1011;

import java.util.Arrays;

public class Solution {
    public int shipWithinDays(int[] weights, int D) {
        // 先找左边界
        int left = Arrays.stream(weights).max().getAsInt();
        // 再找右边界
        int right = Arrays.stream(weights).sum();

        // 二分查找
        while (left < right) {
            int mid = (left + right) / 2;
            int curWeights = 0, day = 1;

            for (int weight : weights) {
                if (curWeights + weight > mid) {
                    day++;
                    curWeights = 0;
                }
                curWeights += weight;
            }

            if (day <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;

        int[] weights2 = {3, 2, 2, 4, 1, 4};
        int D2 = 3;

        int[] weights3 = {1, 2, 3, 1, 1};
        int D3 = 4;

        Solution solut = new Solution();
        System.out.println(solut.shipWithinDays(weights, D));
    }
}
