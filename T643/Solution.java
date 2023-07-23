package T643;

import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        if (k == len){
            return (double)Arrays.stream(nums).sum() / (double) k;
        }

        double maxNum = Integer.MIN_VALUE;
        long sum = 0;
        int left = 0, right;
        for (right = 0; right < len; right++) {
            if (right + 1 < k) {
                sum += nums[right];
            } else if (right + 1 == k) {
                sum += nums[right];
                maxNum = Math.max(maxNum, (double) sum / (double) k);
            } else {
                sum -= nums[left];
                left++;
                sum += nums[right];

                maxNum = Math.max(maxNum, (double) sum / (double) k);
            }
        }
        return maxNum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;

        int[] nums2 = {-1};
        int k2 = 1;

        Solution solut = new Solution();
        System.out.println(solut.findMaxAverage(nums, k));
    }
}
