package T45;

public class Solution {
    // 动态规划
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // 初始化
        for (int i = 1; i < n; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int k = nums[i];
            for (int j = i + 1; j < Math.min(n, i + k + 1); j++) {
                if (dp[j] == -1 && dp[i] != -1) {  // 本处没有到达过，并且上一步可达
                    dp[j] = dp[i] + 1;
                } else {  // 本处到达过
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();

        return dp[n - 1];
    }

    // 贪心算法，但是贪心算法不能解决“到不了终点”的情况
    // 还好这题题干表示，给定的数组一定能到达终点
    public int jump2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int maxPos = 0;
        int end = 0;
        for (int i = 0; i < n - 1; i++) {
            maxPos = Math.max(maxPos, nums[i] + i);
            if (i == end) {
                end = maxPos;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 0, 0, 1, 4};

        Solution solut = new Solution();
        System.out.println(solut.jump(nums));
        System.out.println(solut.jump2(nums));
    }
}
