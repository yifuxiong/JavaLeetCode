package T81;

public class Solution {
    // 官方二分查找
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid]) {  // 过滤相同元素
                left++;
            } else if (nums[left] < nums[mid]) {  // 前有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {  // 后有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    // 暴力查找
    public boolean search2(int[] nums, int target) {
        for (int n : nums) {
            if (target == n) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        int target = 0;

        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        int target2 = 3;

        Solution solut = new Solution();
        System.out.println(solut.search(nums, target));
        System.out.println(solut.search2(nums, target));
    }
}
