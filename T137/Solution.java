package T137;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 哈希表
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        System.out.println(hashMap);

        int ans = nums[0];
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) {
                ans = entry.getKey();
                break;
            }
        }
        return ans;
    }

    // 位数统计
    public int singleNumber2(int[] nums) {
        int[] cnt = new int[32];
        for (int n : nums) {
            // 遍历每一位
            for (int i = 0; i < 32; i++) {
                if (((n >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((cnt[i] % 3 & 1) == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }

    // 数字电路
    public int singleNumber3(int[] nums) {
        int one = 0, two = 0;
        for (int n : nums) {
            one = one ^ n & ~two;
            two = two ^ n & ~one;
        }
        return one;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 2};
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};

        Solution solut = new Solution();
        System.out.println(solut.singleNumber(nums2));
        System.out.println(solut.singleNumber2(nums2));
        System.out.println(solut.singleNumber3(nums2));
    }
}
