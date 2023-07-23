package T453;

import java.util.Arrays;

public class Solution {
    // 从正面解决问题：
    // 假设目前数组总和为sum，我们需要移动次数为m，那么整体数组总和将会增加m * (n - 1)，这里的n为数组长度，最后数组所有元素都相等为x，于是有：
    // sum + m * (n - 1) = x * n    (1)
    //
    // 我们再设数组最小的元素为min_val，m = x - min_val​，即 ​x = m + min_val​带入(1)得：
    // m = sum - min_val * n​
    public int minMoves(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int minValue = Arrays.stream(nums).min().getAsInt();
        int n = nums.length;
        return sum - minValue * n;
    }

    // 用减法的角度思考：每次都有n-1个元素加1，变为每次都有1个元素减1
    public int minMoves2(int[] nums) {
        int minValue = Arrays.stream(nums).min().getAsInt();
        int ans = 0;
        for (int num : nums) {
            ans += (num - minValue);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums2 = {1, 1, 1};

        Solution solut = new Solution();
        System.out.println(solut.minMoves(nums));
        System.out.println(solut.minMoves2(nums));
    }
}
