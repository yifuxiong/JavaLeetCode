package T33;

public class Solution {
    // 类二分法
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] == nums[mid]) {  // 过滤重复元素
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

        return -1;
    }

    // 暴力法
    public int search2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;

        int[] nums3 = {1};
        int target3 = 0;

        Solution solut = new Solution();
        System.out.println(solut.search(nums, target));
        System.out.println(solut.search2(nums, target));
    }
}
