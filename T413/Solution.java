package T413;

// 等差数列划分
// 题目字眼：子数组 是数组中的一个【连续序列】
public class Solution {
    // 差分+计数
    // 这里面有个细节：
    // 比如[1,2,3]有1个；
    // [1,2,3,4]有[1,2,3],[2,3,4],[1,2,3,4]3个；
    // [1,2,3,4,5]有...[3,4,5],[1,2,3,4],[2,3,4,5],[1,2,3,4,5]6个；
    // 即，每添加一个数，temp都会把前面子数组个数也加进来。
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        int diff = nums[1] - nums[0];
        int ans = 0;
        int temp = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                temp++;
            } else {
                diff = nums[i] - nums[i - 1];
                temp = 0;
            }
            ans += temp;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums2 = {1};

        Solution solut = new Solution();
        System.out.println(solut.numberOfArithmeticSlices(nums));
    }
}
