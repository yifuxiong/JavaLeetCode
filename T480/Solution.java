package T480;

import java.util.Collections;
import java.util.Vector;

public class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        int index = 0;

        int left = 0, right = 0;
        Vector<Integer> vector = new Vector<>();
        for (right = 0; right < n; right++) {
            // 不够k个数，直接添加
            if (right - left + 1 < k) {
                vector.add(nums[right]);
            } else if (right - left + 1 == k) {
                vector.add(nums[right]);
                Collections.sort(vector);

                // 如果k是偶数，取最中间两个数的平均值
                if (k % 2 == 0) {
                    ans[index++] = ((long)vector.get(k / 2) + (long)vector.get(k / 2 - 1)) / 2d;
                } else {  // 如果k是奇数，取中间的一个数
                    ans[index++] = vector.get(k / 2);
                }
            } else {
                // 删除最左边的第一个数
                vector.removeElement(nums[left]);
                left++;
                // 添加新数
                vector.add(nums[right]);
                Collections.sort(vector);

                // 如果k是偶数，取最中间两个数的平均值
                if (k % 2 == 0) {
                    ans[index++] = ((long)vector.get(k / 2) + (long)vector.get(k / 2 - 1)) / 2d;
                } else {  // 如果k是奇数，取中间的一个数
                    ans[index++] = vector.get(k / 2);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] nums2 = {7, 0, 3, 9, 9, 9, 1, 7, 2, 3};
        int k2 = 6;

        int[] nums3 = {1,4,2,3};
        int k3 = 4;

        Solution solut = new Solution();
        double[] ans = solut.medianSlidingWindow(nums3, k3);
        for (double a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
