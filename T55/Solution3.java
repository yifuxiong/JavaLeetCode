package T55;

public class Solution3 {
    // 官方：贪心算法(尽可能达到最远的位置)
    // 因为最远能到达某个位置，就一定能到达它前面的任何位置。
    public boolean canJump(int[] nums) {
        // 初始化最远距离为0
        int maxLen = 0;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (maxLen >= i){
                maxLen = Math.max(maxLen, i + nums[i]);
            }else{
                // 要是最远只能到达当前索引位置的前面，那么说明到不了终点
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        int[] nums3 = {0, 2, 3};
        int[] nums4 = {1, 0, 2, 3};
        int[] nums5 = {0};
        int[] nums6 = {0, 0};
        int[] nums7 = {1, 0};
        int[] nums8 = {2, 0, 2};
        int[] nums9 = {3, 0, 8, 2, 0, 0, 1};

        Solution3 solut3 = new Solution3();
        System.out.println(solut3.canJump(nums9));
    }
}
