package T300;

public class Solution2 {
    // 看了T368，再回头看T300，感觉T300的确一般
    // 时间复杂度O(n^2)可以做到 -> 见Solution
    // O(nlogn)呢？ -> 单调栈

    // 我先用O(n^2)写一遍
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        // 记录遍历到当前数字时，最长序列的长度
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            int curMaxlen = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (f[j] + 1 > curMaxlen) {
                        curMaxlen = f[j] + 1;
                    }
                }
            }
            f[i] = curMaxlen;
        }
        // printNums(f);

        int ans = 0;
        for (int i = 0; i < f.length; i++) {
            if (f[i] > ans) {
                ans = f[i];
            }
        }

        return ans;
    }

    public void printNums(int[] nums) {
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.lengthOfLIS(nums3));
    }
}
