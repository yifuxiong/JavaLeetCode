package T1423;

import java.util.Arrays;

public class Solution {
    // 转换思路：换成求剩余n-k个数之和的最小值，用滑动窗口计算
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // cardPoints这个数组所有数之和
        int sum = Arrays.stream(cardPoints).sum();
        // 当前滑动窗口中n-k个数之和
        int curWin = 0;
        // 记录窗口中n-k个数之和的最小值
        int minAns = Integer.MAX_VALUE;

        int left = 0, right = 0;
        while (right < n) {
            if (right < n - k - 1) {
                curWin += cardPoints[right];
            } else if (right == n - k - 1) {
                curWin += cardPoints[right];
                minAns = Math.min(curWin, minAns);
            } else {
                curWin -= cardPoints[left];
                left++;
                curWin += cardPoints[right];
                minAns = Math.min(curWin, minAns);
            }
            right++;
        }
        // 返回数组总和 - 窗口中的最小值
        return sum - minAns;
    }

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;

        int[] cardPoints2 = {2, 2, 2};
        int k2 = 2;

        int[] cardPoints3 = {9, 7, 7, 9, 7, 7, 9};
        int k3 = 7;

        int[] cardPoints4 = {1, 1000, 1};
        int k4 = 1;

        int[] cardPoints5 = {1, 79, 80, 1, 1, 1, 200, 1};
        int k5 = 3;

        Solution solut = new Solution();
        System.out.println(solut.maxScore(cardPoints5, k5));
    }
}
