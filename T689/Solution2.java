package T689;

public class Solution2 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;

        Solution2 solut2 = new Solution2();
        int[] ans = solut2.maxSumOfThreeSubarrays(nums, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
