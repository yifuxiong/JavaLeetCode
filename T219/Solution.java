package T219;

import java.util.HashSet;

/**
 * 思路：用散列表来维护这个k大小的滑动窗口。
 * <p>
 * 算法：在之前的方法中，我们知道了对数时间复杂度的 搜索 操作是不够的。在这个方法里面，我们需要一个支持在常量时间内完成 搜索，删除，插入 操作的数据结构，那就是散列表。这个算法的实现跟方法二几乎是一样的。
 * <p>
 * 遍历数组，对于每个元素做以下操作：
 * - 在散列表中搜索当前元素，如果找到了就返回 true。
 * - 在散列表中插入当前元素。
 * - 如果当前散列表的大小超过了 k， 删除散列表中最旧的元素。
 * 返回 false。
 */
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            else set.add(nums[i]);
            if (set.size() > k) {
                // set的size大于k的时候，说明前k个数没有重复，这时候表示窗口要滑动了
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;

        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;

        Solution solut = new Solution();
        System.out.println(solut.containsNearbyDuplicate(nums3, k3));

    }
}
