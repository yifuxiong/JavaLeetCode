package T213;

// 打家劫舍II，T198的进化版
// 分别在nums[0:N-1]和nums[1:N]上计算能获取到的最大值，这两种情况取最大。
// 这肯定能保证在物理上隔离了首尾两个元素，肯定不会同时选到。
public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 表示第n-1家偷(0)和不偷(1)
        int[] dp = new int[n];
        // 初始条件
        dp[0] = nums[0];
        // dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        // 当i = 1时，i-2小于0，所以不加dp[i-2]这一项
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        int ans = dp[n - 2];

        dp[0] = nums[1];
        dp[1] = Math.max(dp[0], nums[2]);
        for (int i = 2; i < n; i++) {
            if (i + 1 < n) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i + 1]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2]);
            }
        }
        ans = Math.max(ans, dp[n - 1]);

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        int[] nums2 = {1, 2, 3, 1};
        int[] nums3 = {0};
        int[] nums4 = {2, 7, 9, 3, 1};

        Solution solut = new Solution();
        System.out.println(solut.rob(nums4));
    }
}
