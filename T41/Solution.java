package T41;

import java.util.Arrays;

public class Solution {
    // 一次排序：时间复杂度O(logN)
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);

        int start = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                continue;
            }
            if (nums[i] == start) {
                start++;
            } else if (i > 0 && nums[i] == nums[i - 1]) {
                // 重复元素
                continue;
            } else {
                return start;
            }
        }
        if (nums[nums.length - 1] < 0) {
            return 1;
        } else {
            return nums[nums.length - 1] + 1;
        }
    }

    // 题目要求：时间复杂度O(n)，空间复杂度O(1)
    // 官方解答：哈希表
    public int firstMissingPositive2(int[] nums) {

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        int[] nums4 = {0, 2, 2, 1, 1};

        Solution solut = new Solution();
        System.out.println(solut.firstMissingPositive(nums4));
        System.out.println(solut.firstMissingPositive2(nums4));
    }
}
