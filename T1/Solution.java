package T1;

import java.util.HashMap;
import java.util.Map;

// 这题不能排序，因为排序之后下标就变了
public class Solution {
    // 时间复杂度O(n2)
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    break;
                }
            }
        }
        return ans;
    }

    // 时间复杂度O(n)
    public int[] twoSum2(int[] nums, int target) {
        int[] ans = new int[2];

        // hashMap<num, index>
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i], i);
            }
        }

        System.out.println(hashMap);

        for (int i = 0; i < nums.length; i++){
            int a = target - nums[i];
            if (hashMap.containsKey(a)){
                if (i != hashMap.get(a)){
                    ans[0] = i;
                    ans[1] = hashMap.get(a);
                    break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] nums2 = {3, 2, 4};
        int target2 = 6;

        int[] nums3 = {3, 3};
        int target3 = 6;

        Solution solut = new Solution();
        int[] ans = solut.twoSum(nums3, target3);
        System.out.println(ans[0] + " " + ans[1]);
        int[] ans2 = solut.twoSum2(nums3, target3);
        System.out.println(ans2[0] + " " + ans2[1]);
    }
}
