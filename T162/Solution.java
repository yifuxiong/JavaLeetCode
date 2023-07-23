package T162;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 时间复杂度O(n)
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }

        List<Integer> tmp = new ArrayList<>();
        // 逆序比较
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] > nums[i - 1]) {
                tmp.add(i);
            }
        }
        tmp.add(0);

        int ret = 0;
        for (Integer i : tmp) {
            if (i == len - 1) {
                ret = i;
                break;
            }
            if (nums[i] > nums[i + 1]) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    // 时间复杂度O(logN)
    // 二分查找大的那一半一定会有峰值
    // nums[mid]<nums[mid+1]时，若nums[mid+2]<nums[mid+1]，那么nums[mid+1]就是峰值
    // 若mid+1后面一直大于下去，那么边界值nums[len-1]一定是峰值
    // 综上，二分查找大的一半一定有峰值
    public int findPeakElement2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};

        Solution solut = new Solution();
        System.out.println(solut.findPeakElement2(nums));
    }
}
