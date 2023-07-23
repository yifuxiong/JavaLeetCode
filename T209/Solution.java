package T209;

public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        sums[0] = 0;
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        for (int i = 0; i <= n; i++) {
            System.out.print(sums[i] + " ");
        }
        System.out.println();

        int minLen = 2 * n;
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (sums[j] - sums[i] >= target) {
                    System.out.println(sums[j] - sums[i]);
                    minLen = Math.min(minLen, j - i);
                }
            }
        }

        if (minLen > n) {
            return 0;
        } else {
            return minLen;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 15;

        Solution solut = new Solution();
        System.out.println(solut.minSubArrayLen(target, nums));
    }
}
