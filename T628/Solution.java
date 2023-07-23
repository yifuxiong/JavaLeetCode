package T628;

import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

public class Solution {
    // 3个正数，2个负数1个正数
    public int maximumProduct(int[] nums) {
        if (nums.length == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        // 排序
        Arrays.sort(nums);
        int n = nums.length;
        int ret = nums[n - 1] * nums[n - 2] * nums[n - 3];
        if (nums[0] < 0 && nums[1] < 0) {
            ret = Math.max(nums[0] * nums[1] * nums[n - 1], ret);
        }
        return ret;
    }

    // 线性扫描：只需找出最大的3个数，以及最小的2个数
    public int maximumProduct2(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums){
            if (x < min1){
                min2 = min1;
                min1 = x;
            }else if (x < min2){
                min2 = x;
            }

            if (x > max1){
                max3 = max2;
                max2 = max1;
                max1 = x;
            }else if (x > max2){
                max3 = max2;
                max2 = x;
            }else if (x > max3){
                max3 = x;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }


    public static void main(String[] args) {
        int[] nums = {-1000, -1, 2, 800, 1000};
        int[] nums2 = {-1000, -900, 0, 1, 1000};

        Solution solut = new Solution();
        System.out.println(solut.maximumProduct(nums2));
        System.out.println(solut.maximumProduct2(nums2));
    }
}
