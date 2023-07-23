package T55;

public class Solution2 {
    // 暴力法2
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        // 初始化
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) {
                continue;
            } else {
                int step = nums[i];
                for (int j = i; j <= i + step && j < n; j++) {
                    dp[j] = true;
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        int[] nums3 = {0, 2, 3};
        int[] nums4 = {1, 0, 2, 3};
        int[] nums5 = {0};
        int[] nums6 = {0, 0};
        int[] nums7 = {1, 0};
        int[] nums8 = {2, 0, 2};
        int[] nums9 = {3, 0, 8, 2, 0, 0, 1};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.canJump(nums6));
    }
}
