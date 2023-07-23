package T368;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 用到了最长递增子序列（LIS）的思路，T300
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        int[] g = new int[n];

        for (int i = 0; i < n; i++) {
            // 至少包含自身一个数，因此起始长度为1，由自身转移而来
            int len = 1, prev = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    // 如果能接在更长的序列后面，则更新【最大长度】&【从何转移而来】
                    if (f[j] + 1 > len) {
                        len = f[j] + 1;
                        prev = j;
                    }
                }
            }
            // 记录【最终长度】&【从何转移而来】
            f[i] = len;
            g[i] = prev;
        }

        // 遍历所有的f[i]，取得【对应下标】&【最大长度】
        int max = -1, idx = -1;
        for (int i = 0; i < n; i++) {
            if (f[i] > max) {
                idx = i;
                max = f[i];
            }
        }

        // 使用g[]数组回溯出具体方案
        List<Integer> answer = new LinkedList<>();
        while (answer.size() != max) {
            answer.add(nums[idx]);
            idx = g[idx];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums2 = {1, 2, 4, 8};

        Solution solut = new Solution();
        System.out.println(solut.largestDivisibleSubset(nums));
    }
}
