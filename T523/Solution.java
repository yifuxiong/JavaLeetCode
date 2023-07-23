package T523;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 超出时间限制
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        // 初始化
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }
        // printDp(dp);

        // 子数组大小至少为2
        for (int i = 0; i <= n - 2; i++) {
            for (int j = i + 2; j <= n; j++) {
                int sum = dp[j] - dp[i];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printDp(int[] dp) {
        for (int d : dp) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    // 优化
    public boolean checkSubarraySum2(int[] nums, int k) {
        int m = nums.length;
        if (m < 2){
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++){
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)){
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2){
                    return true;
                }
            }else{
                map.put(remainder, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;

        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;

        int[] nums3 = {23, 2, 6, 4, 7};
        int k3 = 13;

        Solution solut = new Solution();
        System.out.println(solut.checkSubarraySum(nums3, k3));
        System.out.println(solut.checkSubarraySum2(nums3, k3));
    }
}
