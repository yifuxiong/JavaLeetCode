package T26;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            } else {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        Solution solut = new Solution();
        System.out.println(solut.removeDuplicates(nums2));
    }
}
