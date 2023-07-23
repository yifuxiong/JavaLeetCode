package T220;

import java.util.TreeSet;

public class Solution2 {
    // 自己写一个滑动窗口
    // 内部有序的数据结构TreeSet或者TreeMap
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            // ceiling是返回大于等于的元素，不存在返回null
            Long ceil = set.ceiling((long) nums[i] - (long)t);
            if (ceil != null && ceil <= (long)nums[i] + (long)t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3, t = 0;

        int[] nums2 = {1, 5, 9, 1, 5, 9};
        int k2 = 2, t2 = 3;

        int[] nums3 = {2147483640, 2147483641};
        int k3 = 1, t3 = 100;

        int[] nums4 = {1, 2};
        int k4 = 0, t4 = 1;

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.containsNearbyAlmostDuplicate(nums4, k4, t4));
    }
}
