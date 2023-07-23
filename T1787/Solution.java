package T1787;

public class Solution {
    // 做不到，直接cv
    public int minChanges(int[] nums, int k) {

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 3, 0};
        int k = 1;

        int[] nums2 = {3, 4, 5, 2, 1, 7, 3, 4, 7};
        int k2 = 3;

        int[] nums3 = {1, 2, 4, 1, 2, 5, 1, 2, 6};
        int k3 = 3;

        Solution solut = new Solution();
        System.out.println(solut.minChanges(nums, k));
    }
}
