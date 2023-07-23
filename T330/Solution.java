package T330;

// https://leetcode-cn.com/problems/patching-array/solution/tan-xin-fu-gai-wen-ti-xiang-xi-jie-xi-by-dwux/
public class Solution {
    // 实际上就是判断是否需要添加2^n，如果是，则patches+1
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
            while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        int n = 6;

        int[] nums2 = {1, 5, 10};
        int n2 = 20;

        int[] nums3 = {1, 2, 2};
        int n3 = 5;

        Solution solut = new Solution();
        System.out.println(solut.minPatches(nums3, n3));
    }
}
