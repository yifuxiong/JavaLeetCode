package T674;

public class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n;
        }

        int maxlen = 1, curlen = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                curlen++;
            } else {
                maxlen = Math.max(maxlen, curlen);
                curlen = 1;
            }
        }
        maxlen = Math.max(maxlen, curlen);
        return maxlen;
    }

    public static void main(String[] args) {
        int[] nums = {6, 1, 3, 5, 4, 7};
        int[] nums2 = {2, 2, 2, 2, 2};

        Solution solut = new Solution();
        System.out.println(solut.findLengthOfLCIS(nums));
    }
}
