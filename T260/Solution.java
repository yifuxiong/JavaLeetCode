package T260;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int n = nums.length;
        if (n == 2) {
            return nums;
        }

        for (int i = 0; i < n; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        int[] ans = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) {
                ans[i++] = entry.getKey();
                if (i == 2) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] nums2 = {-1, 0};
        int[] nums3 = {0, 1};

        Solution solut = new Solution();
        int[] ans = solut.singleNumber(nums3);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
