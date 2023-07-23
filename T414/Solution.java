package T414;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution {
    // 这个代码可以通过，但不是题目想要的终极代码
    // 题目要求时间复杂度O(n)
    public int thirdMax(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        // System.out.println(hashSet);

        if (hashSet.size() < 3) {  // 取最大
            Iterator<Integer> iter = hashSet.iterator();
            int ans = Integer.MIN_VALUE;
            while (iter.hasNext()) {
                ans = Math.max(ans, iter.next());
            }
            return ans;
        } else {
            int n = hashSet.size();
            int[] newNums = new int[n];
            int i = 0;
            Iterator<Integer> iter = hashSet.iterator();
            while (iter.hasNext()) {
                int num = iter.next();
                newNums[i++] = num;
            }

            return topK(newNums, 3);
        }
    }

    // 小根堆
    public void adjust(int[] nums, int low, int high) {
        int temp = nums[low];
        int i = low;
        int j = i * 2 + 1;  // 左子树

        while (j <= high) {
            if (j + 1 <= high && nums[j] > nums[j + 1]) {
                j = j + 1;
            }
            if (temp > nums[j]) {
                nums[i] = nums[j];
                i = j;
                j = i * 2 + 1;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }

    // topK
    public int topK(int[] nums, int k) {
        int n = nums.length;
        int[] heap = new int[k];
        // 初始化小根堆
        for (int i = 0; i < k; i++) {
            heap[i] = nums[i];  // 复制前k个数
        }

        // 1.前k个排序
        for (int i = k / 2 - 1; i >= 0; i--) {
            adjust(heap, i, k - 1);
        }

        // 2.从k到n-1，选取topK
        for (int i = k; i < n; i++) {
            if (heap[0] < nums[i]) {
                heap[0] = nums[i];
                adjust(heap, 0, k - 1);
            }
        }

        // 3.如果是堆排序，就将整个堆逆序；如果只是输出topK，直接返回首个元素
//        for (int i = k - 1; i >= 0; i--) {
//            int temp = heap[0];
//            heap[0] = heap[i];
//            heap[i] = temp;
//            adjust(heap, 0, i - 1);
//        }

        return heap[0];
    }

    // 时间复杂度O(n)
    public int thirdMax2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;

        for (long num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (first > num && num > second) {
                third = second;
                second = num;
            } else if (second > num && num > third) {
                third = num;
            }
        }
        if (third == Long.MIN_VALUE) {  // 如果第3大的数没变，说明没有第3大的数
            return (int) first;
        } else {
            return (int) third;
        }
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
        int[] nums2 = {2, 2, 3, 1};
        int[] nums3 = {1, 5, 6, 3, 4, 2, 7};
        int[] nums4 = {-2147483648, -2147483648, -2147483648, -2147483648, 1, 1, 1, -1, -2};
        int[] nums5 = {-1, -1, -2, -2, -1, -2};

        Solution solut = new Solution();
        // System.out.println(solut.thirdMax(nums4));
        System.out.println(solut.thirdMax2(nums4));

        // topK
//        int[] heap = solut.topK(nums, 3);
//        for (int i = 0; i < heap.length; i++) {
//            System.out.print(heap[i] + " ");
//        }
//        System.out.println();
    }
}
