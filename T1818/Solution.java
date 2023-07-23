package T1818;

import java.util.Arrays;

public class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int MOD = (int) Math.pow(10, 9) + 7;  // 1000000007;

        int n = nums1.length;
        int[] rec = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            rec[i] = nums1[i];
        }
        Arrays.sort(rec);

        int sum = 0;
        int maxn = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch(nums1, nums2[i]);
            if (j < n) {
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
            }
        }
        return (sum - maxn + MOD) % MOD;
    }

    public int binarySearch(int[] nums, int b) {
        int left = 0, right = nums.length - 1;
        // 如果nums最大值比目标值还要小
        if (nums[right] < b) {
            return right + 1;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < b) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 28, 21};  // 1, 21, 28
        int[] nums2 = {9, 21, 20};

        Solution solut = new Solution();
        System.out.println(solut.minAbsoluteSumDiff(nums1, nums2));
    }
}
