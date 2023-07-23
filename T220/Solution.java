package T220;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    // 超时
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        List<Long> list = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            if (list.size() < k + 1) {
                // 新添加的这个数，与list中的每一个数比较，若差的绝对值小于等于t，返回true
                for (Long j : list) {
                    // System.out.println(Math.abs((long)nums[i] - j));
                    if (Math.abs((long)nums[i] - j) <= t) {
                        return true;
                    }
                }
                list.add((long)nums[i]);
                i++;
            } else {  // 滑动窗口
                list.remove(0);
            }
            System.out.println(list);
        }
        return false;
    }

    // 改进参考：https://leetcode-cn.com/problems/contains-duplicate-iii/solution/hua-dong-chuang-kou-er-fen-sou-suo-shu-zhao-shang-/
    // 滑动窗口结合查找表，此时滑动窗口即为查找表本身（控制查找表的大小即可控制窗口大小）
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 边添加边查找
            // 查找表中是否有大于等于nums[i] - t且小于等于nums[i] + t的值
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            // 添加后，控制查找表（窗口）大小，移除窗口最左边元素
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        int t = 0;

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        int t2 = 2;

        int[] nums3 = {1, 5, 9, 1, 5, 9};
        int k3 = 2;
        int t3 = 3;

        int[] nums4 = {-2147483648, 2147483647};
        int k4 = 1;
        int t4 = 1;

        Solution solut = new Solution();
        // System.out.println(solut.containsNearbyAlmostDuplicate(nums4, k4, t4));
        System.out.println(solut.containsNearbyAlmostDuplicate2(nums3, k3, t3));
    }
}
