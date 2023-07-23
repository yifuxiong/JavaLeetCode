package T55;

public class Solution {
    // 暴力法：超时
    public boolean canJump(int[] nums) {
        int n = nums.length;

        if (n < 2) {
            return true;
        }
        // start, step
        boolean[][] dp = new boolean[n][n];
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[i][0] = (nums[0] != 0);
        }

        for (int i = 0; i < n; i++) {
            int step = nums[i];
            for (int j = i + 1; j <= i + step && j < n; j++) {
                dp[i][j] = true;
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        // 纵向遍历每个dp[i][j]
        // 找一条全是true的路径，那么就能达到
        for (int j = 0; j < n; j++) {
            int flag = 0;
            for (int i = 0; i < n; i++) {
                if (dp[i][j]) {
                    // 下一列
                    break;
                } else {
                    flag++;
                }
            }
            // 某一列全是false
            if (flag == n) {
                return false;
            }
        }
        return true;
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

        Solution solut = new Solution();
        System.out.println(solut.canJump(nums9));
    }
}
