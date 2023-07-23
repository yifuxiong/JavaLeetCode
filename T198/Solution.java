package T198;

// 打家劫舍I
public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // max(第i家不劫，第i家劫了）
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {7, 2, 3, 9, 1};

        Solution solut = new Solution();
        System.out.println(solut.rob(nums3));
    }
}
