package Insert_sort;

// T179
public class Solution {
    public int[] insertSort(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return nums;
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                int temp = nums[i];
                int k = i;
                for (int j = i - 1; j >= 0; j--) {
                    if (temp < nums[j]) {
                        nums[j + 1] = nums[j];
                    } else {
                        k = j + 1;
                        break;
                    }
                    if (j == 0){
                        k = 0;
                    }
                }
                nums[k] = temp;
            }
        }

        return nums;
    }

    public static void printNums(int[] nums) {
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 2};

        Solution solut = new Solution();
        nums = solut.insertSort(nums);
        printNums(nums);
    }
}
