package T561;

import java.util.Arrays;

// 排序就能解决
public class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2};
        int[] nums2 = {6, 2, 6, 5, 1, 2};

        Solution solut = new Solution();
        System.out.println(solut.arrayPairSum(nums2));
    }
}
