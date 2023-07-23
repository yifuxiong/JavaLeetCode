package T189;

public class Solution {
    // 分三步翻转，[0, k+1)翻转，[k+1, n)翻转，最后整个数组n翻转
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        k = n - 1 - k;
        int temp;

        // [0, k+1)翻转
        int mid1 = k / 2;
        for (int i = 0; i <= mid1; i++) {
            temp = nums[i];
            nums[i] = nums[k - i];
            nums[k - i] = temp;
        }
        // printNums(nums);

        // [k+1, n)翻转
        int mid2 = (k + n) / 2;
        for (int i = k + 1; i <= mid2; i++) {
            temp = nums[i];
            nums[i] = nums[k + n - i];
            nums[k + n - i] = temp;
        }
        // printNums(nums);

        // 整个数组翻转
        int mid3 = n / 2;
        for (int i = 0; i < mid3; i++) {
            temp = nums[i];
            nums[i] = nums[n - 1 - i];
            nums[n - 1 - i] = temp;
        }
    }

    public static void printNums(int[] nums) {
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;

        Solution solut = new Solution();
        solut.rotate(nums, k);
        printNums(nums);
    }
}
