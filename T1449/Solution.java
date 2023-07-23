package T1449;

import java.util.Arrays;

public class Solution {
    public String largestNumber(int[] cost, int target) {
        // 第一步：动态规划，完全背包
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int c : cost) {
            for (int j = c; j <= target; ++j) {
                dp[j] = Math.max(dp[j], dp[j - c] + 1);
            }
        }
        // 打印dp看看
        // printDp(dp);

        if (dp[target] < 0) {
            // 如果dp[target]没有遍历到，说明根本凑不齐target，直接返回"0"
            return "0";
        }

        // 第二步：
        StringBuffer sb = new StringBuffer();
        // 由于cost.length==9
        // 应该尽可能让高位的数值越大越好，因此我们可以从数值 8 开始往数值 0 遍历
        for (int i = 8, j = target; i >= 0; i--) {
            while (j >= cost[i] && dp[j] == dp[j - cost[i]] + 1) {
                sb.append(i + 1);
                j -= cost[i];
            }
        }
        return sb.toString();
    }

    public void printDp(int[] dp) {
        for (int d : dp) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] cost = {4, 3, 2, 5, 6, 7, 2, 5, 5};
        int target = 9;

        int[] cost2 = {2,4,6,2,4,6,4,4,4};
        int target2 = 5;

        Solution solut = new Solution();
        System.out.println(solut.largestNumber(cost2, target2));
    }
}
