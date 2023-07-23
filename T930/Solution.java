package T930;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        // 初始化
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // key对应前缀和的值，value对应这个值的个数
        Map<Integer, Integer> hashMap = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < preSum.length; i++) {
            int b = preSum[i] - goal;
            if (hashMap.containsKey(b)) {
                ans += hashMap.get(b);
            }
            // hashMap不要先初始化，放到这里赋值，类似三角形计算，可以避免重复计算
            hashMap.put(preSum[i], hashMap.getOrDefault(preSum[i], 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;

        int[] nums2 = {0, 0, 0, 0, 0};
        int goal2 = 0;

        Solution solut = new Solution();
        System.out.println(solut.numSubarraysWithSum(nums, goal));
    }
}
