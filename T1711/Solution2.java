package T1711;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int countPairs(int[] deliciousness) {
        // 先找到最大值
        int MOD = (int) (10e9 + 7);
        int maxVal = 0;
        for (int d : deliciousness) {
            maxVal = Math.max(maxVal, d);
        }

        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int n = deliciousness.length;

        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        return pairs;
    }

    public int countPairs2(int[] deliciousness) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int maxVal = 0;
        for (int d : deliciousness) {
            maxVal = Math.max(maxVal, d);
        }

        int ans = 0;
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= maxVal * 2; j <<= 1) {
                int b = j - deliciousness[i];
                ans = (ans + hashMap.getOrDefault(b, 0)) % (int) (10e9 + 7);
            }
            // 放到这里是为了减少重复计算
            hashMap.put(deliciousness[i], hashMap.getOrDefault(deliciousness[i], 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] deliciousness = {64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64};
        int[] deliciousness2 = {149, 107, 1, 63, 0, 1, 6867, 1325, 5611, 2581, 39, 89, 46, 18, 12, 20, 22, 234};
        Solution2 solut2 = new Solution2();
        System.out.println(solut2.countPairs(deliciousness));
        System.out.println(solut2.countPairs2(deliciousness));
    }
}
