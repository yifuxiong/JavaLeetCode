package T645;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 哈希表
    public int[] findErrorNums(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        System.out.println(hashMap);

        int[] ans = new int[2];
        int start = 1, len = nums.length;
        while (start <= len) {
            if (!hashMap.containsKey(start)) {
                ans[1] = start;
                break;
            }
            start++;
        }
        start = 1;
        while (start <= len) {
            // 这一趟只是找重复的，没有的键直接跳过
            if (!hashMap.containsKey(start)) {
                start++;
                continue;
            }
            if (hashMap.get(start) > 1) {
                ans[0] = start;
                break;
            }
            start++;
        }
        return ans;
    }

    // 投机取巧
    public int[] findErrorNums2(int[] nums) {
        int[] ans = new int[2];
        int[] counter = new int[nums.length + 1];

        for (int num: nums) {
            counter[num]++;
        }

        for (int i = 1; i < counter.length; i++){
            if (counter[i] == 0) {
                ans[1] = i;
            }else if (counter[i] == 2){
                ans[0] = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4};
        int[] nums2 = {2, 2};
        int[] nums3 = {3, 2, 2};

        Solution solut = new Solution();
        // 哈希表
        int[] ans = solut.findErrorNums(nums3);
        System.out.println("[" + ans[0] + "," + ans[1] + "]");
        // 排序
        int[] ans2 = solut.findErrorNums2(nums3);
        System.out.println("[" + ans2[0] + "," + ans2[1] + "]");
    }
}
