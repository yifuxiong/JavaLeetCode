package T1035;

public class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int ans1 = numDistinct(0, 0, nums1, nums2, 0);
        int ans2 = numDistinct(0, 0, nums2, nums1, 0);
        return Math.max(ans1, ans2);
    }

    public int numDistinct(int i, int j, int[] nums1, int[] nums2, int res) {
        int m = nums1.length, n = nums2.length;

        if (j >= n || i >= m) {
            return res;
        }
        if (nums1[i] == nums2[j]) {
            return numDistinct(i + 1, j + 1, nums1, nums2, res + 1);
        } else {
            return Math.max(numDistinct(i + 1, j, nums1, nums2, res), numDistinct(i, j + 1, nums1, nums2, res));
        }
    }

    public int maxUncrossedLines2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  // i和j都往后移动一个
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);  // i或j往后移动一个
                }
            }
        }
        // printDp(dp);
        return dp[m][n];
    }

    public void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 5, 1, 2, 5};
        int[] nums2 = {10, 5, 2, 1, 5, 2};

        Solution solut = new Solution();
        System.out.println(solut.maxUncrossedLines(nums1, nums2));
        System.out.println(solut.maxUncrossedLines2(nums1, nums2));
    }
}
