package T1218;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // LIS(最长严格递增子序列，动态规划)
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        if (n == 1) {
            return 1;
        }

        int ans = 0;
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] + difference == arr[i]) {
                    // dp[i] = dp[j] + 1，这里取其中的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }

//        // 打印看看dp中的结果
//        for (int i = 0; i < n; i++){
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();

        return ans;
    }

    // 一样的思路，哈希表优化
    public int longestSubsequence2(int[] arr, int difference) {
        int n = arr.length;
        if (n == 1) {
            return 1;
        }

        int ans = 1;
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer val = hashMap.get(arr[i] - difference);
            if (val != null) {
                hashMap.put(arr[i], val + 1);
                ans = (ans > val + 1 ? ans : val + 1);
            } else {
                hashMap.put(arr[i], 1);
            }
        }

        System.out.println(hashMap);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 7, 8, 5, 3, 4, 2, 1};
        int difference = -2;

        Solution solut = new Solution();
        System.out.println(solut.longestSubsequence(arr, difference));  // TLE
        System.out.println(solut.longestSubsequence2(arr, difference));
    }
}
