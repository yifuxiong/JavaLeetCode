package T300;

import java.util.Arrays;

// 最长严格递增子序列(LIS)（其实就是动态规划的方法）
// 思路：每遍历一个nums[i]，就遍历它前面比他小的个数并取最大值，放入一个数组f中。最终结果取所有f中的最大值
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n;
        }

        int res = 1;
        int[] f = new int[n];
        Arrays.fill(f, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // f[i]=f[j]+1的确没错，这里要取最大值
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(f[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};

        Solution solut = new Solution();
        System.out.println(solut.lengthOfLIS(nums2));
    }
}
