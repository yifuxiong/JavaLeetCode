package T1049;

import java.util.Arrays;

public class Solution {
    // 转换成0-1背包问题，求两堆石头的最小差值。由于石头总和为sum。则问题转换成了
    // 背包最多装sum / 2的石头，stones数组里有一大堆石头。求如何装能装下最多重量石头。
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int n = stones.length;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        // 初始化省略

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= stones[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }

        // 当前这一堆的总质量
        int one = dp[n][sum / 2];
        // 剩余一堆的总质量
        int two = (sum - one);
        return Math.abs(one - two);
    }

    public void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};
        int[] stones2 = {31, 26, 33, 21, 40};
        int[] stones3 = {1, 2};
        int[] stones4 = {1, 2, 4, 8, 16, 32, 64, 12, 25, 51};

        Solution solut = new Solution();
        System.out.println(solut.lastStoneWeightII(stones4));
    }
}
