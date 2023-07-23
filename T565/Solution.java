package T565;

public class Solution {
    public int arrayNesting(int[] nums) {
        int len = 0;
        for (int n : nums) {
            len = Math.max(len, getLen(nums, n));
        }
        return len;
    }

    public int getLen(int[] nums, int n) {
        int len = 1;
        int origin = n;
        while (nums[n] != origin) {
            n = nums[n];
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 0, 3, 1, 6, 2};

        Solution solut = new Solution();
        System.out.println(solut.arrayNesting(nums));
    }
}
