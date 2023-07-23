package T724;

public class Solution {
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        // 左边所有数的和
        int leftSum = 0;
        // 右边所有数的和
        int rightSum = 0;
        for (int i = 0; i < len; i++){
            rightSum += nums[i];
        }

        for (int i = 0; i < len; i++){
            if (i - 1 >= 0){
                leftSum += nums[i - 1];
            }
            rightSum -= nums[i];
            if (leftSum == rightSum){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
        int[] nums2 = {1, 2, 3};
        int[] nums3 = {};

        Solution solut = new Solution();
        System.out.println(solut.pivotIndex(nums3));
    }
}
