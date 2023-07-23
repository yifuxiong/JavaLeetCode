package T689;

public class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        int curSum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int curSum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int curSum3 = 0, maxSum123 = 0;
        // 第3个滑动窗口的左边界
        for (int i = 2 * k; i < nums.length; i++) {
            // 第1个滑动窗口的左边界
            curSum1 += nums[i - 2 * k];
            // 第2个滑动窗口的左边界
            curSum2 += nums[i - k];
            // 第3个滑动窗口的左边界
            curSum3 += nums[i];
            // 第3个滑动窗口的右边界
            if (i >= 3 * k - 1) {
                if (curSum1 > maxSum1) {
                    maxSum1 = curSum1;
                    maxSum1Idx = i - 3 * k + 1;
                }
                if (maxSum1 + curSum2 > maxSum12) {
                    maxSum12 = maxSum1 + curSum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - 2 * k + 1;
                }
                if (maxSum12 + curSum3 > maxSum123) {
                    maxSum123 = maxSum12 + curSum3;
                    ans[0] = maxSum12Idx1;
                    ans[1] = maxSum12Idx2;
                    ans[2] = i - k + 1;
                }
                curSum1 -= nums[i - 3 * k + 1];
                curSum2 -= nums[i - 2 * k + 1];
                curSum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;

        int[] nums2 = {4, 5, 10, 6, 11, 17, 4, 11, 1, 3};
        int k2 = 1;

        Solution solut = new Solution();
        int[] ans = solut.maxSumOfThreeSubarrays(nums2, k2);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
