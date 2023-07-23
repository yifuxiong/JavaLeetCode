package T217;

import java.util.HashSet;
import java.util.Hashtable;

public class Solution {
    // 我的思路：哈希表
    public boolean containsDuplicate(int[] nums) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for (int i : nums) {
            if (!hashtable.containsKey(i)) {
                hashtable.put(i, 1);
            } else {
                hashtable.put(i, new Integer(hashtable.get(i) + 1));
            }
        }
        // System.out.println(hashtable);

        for (int i: hashtable.values()){
            if (i > 1){
                return true;
            }
        }
        return false;
    }

    // 改进，hashSet不能插入重复元素
    public boolean containsDuplicate2(int[] nums){
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i: nums){
            // 如果hashSet插入不了，说明当前插入元素重复了
            if (!hashSet.add(i)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};

        Solution solut = new Solution();
        System.out.println(solut.containsDuplicate(nums3));
        System.out.println(solut.containsDuplicate2(nums3));
    }

}
