package 剑指Offer53_I在排序数组中查找数字I;

public class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] >= target) {
                right = mid;
            }
        }

        // 到这里只是确定left的位置，即第一个等于target的位置
        int ans = 0;
        while (left < nums.length && nums[left] == target) {
            left++;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        Solution solut = new Solution();
        System.out.println(solut.search(nums, target));
    }
}
