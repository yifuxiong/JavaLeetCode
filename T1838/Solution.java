package T1838;

import java.util.Arrays;

public class Solution {
    // 官方：排序+滑动窗口
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        // 排序
        Arrays.sort(nums);

        int res = 0;
        long total = 0;
        int left = 0;
        for (int right = 1; right < n; right++) {
            // 计算增加值的总和：一个矩形面积
            total += (long) (right - left) * (nums[right] - nums[right - 1]);
            while (total > k) {
                total -= (nums[right] - nums[left]);
                // 左边界向右移动
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4};
        int k = 5;

        int[] nums2 = {1, 4, 8, 13};
        int k2 = 5;

        int[] nums3 = {3, 9, 6};
        int k3 = 2;

        Solution solut = new Solution();
        System.out.println(solut.maxFrequency(nums2, k2));
    }
}
