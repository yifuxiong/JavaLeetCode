package T27;

public class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int num : nums) {
            if (num != val) {
                nums[k++] = num;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;

        Solution solut = new Solution();
        System.out.println(solut.removeElement(nums2, val2));
    }
}
