package T154;

public class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;
            if (nums[right] == nums[mid]) {
                right--;
            } else if (nums[right] > nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        int[] nums2 = {2, 2, 2, 0, 1};
        int[] nums3 = {2, 1};

        Solution solut = new Solution();
        System.out.println(solut.findMin(nums3));
    }
}
