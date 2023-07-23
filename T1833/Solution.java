package T1833;

import java.util.Arrays;

public class Solution {
    // 今天考的是排序
    public int maxIceCream(int[] costs, int coins) {
        quickSort(costs, 0, costs.length - 1);

        int count = 0;
        for (int cost : costs) {
            if (coins - cost >= 0) {
                coins -= cost;
                count++;
            }
        }
        return count;
    }

    // 写个快速排序
    public void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int pos = partition(nums, low, high);
            quickSort(nums, low, pos - 1);
            quickSort(nums, pos + 1, high);
        }
    }

    public int partition(int[] nums, int low, int high) {
        int keyPoint = nums[low];
        while (low < high) {
            while (low < high && keyPoint <= nums[high]) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && keyPoint >= nums[low]) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = keyPoint;
        return low;
    }

    public static void main(String[] args) {
        int[] costs = {1, 3, 2, 4, 1};
        int coins = 7;

        int[] costs2 = {10, 6, 8, 7, 7, 8};
        int coins2 = 5;

        int[] costs3 = {1, 6, 3, 1, 2, 5};
        int coins3 = 20;

        Solution solut = new Solution();
        System.out.println(solut.maxIceCream(costs, coins));
    }
}
