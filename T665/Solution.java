package T665;

public class Solution {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
                if (count > 1) {
                    return false;
                }
                // nums[i]比前面两个数都小
                if (i > 1 && nums[i] < nums[i - 2]) {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 3};
        int[] nums2 = {4, 2, 1};
        int[] nums3 = {3, 4, 2, 3};

        Solution solut = new Solution();
        System.out.println(solut.checkPossibility(nums2));
    }
}
