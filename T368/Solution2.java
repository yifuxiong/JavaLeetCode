package T368;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution2 {
    // 自写写一遍
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        // 先排序
        Arrays.sort(nums);
        int[] f = new int[n];  // 排到这个数时最长的序列长度
        int[] g = new int[n];  // 排在这个数前面一个数的下标

        for (int i = 0; i < n; i++) {
            int curMaxLen = 1, prev = i;
            // nums[i]与它之前的数字比较
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (f[j] + 1 > curMaxLen) {
                        curMaxLen = f[j] + 1;
                        prev = j;
                    }
                }
            }
            f[i] = curMaxLen;
            g[i] = prev;
        }
        printNums(f);
        printNums(g);

        // 找到f中的【最大长度】和【对应下标】
        int maxLen = 1, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > maxLen) {
                maxLen = f[i];
                maxIndex = i;
            }
        }

        // 倒推获得最大子集
        List<Integer> ans = new LinkedList<>();
        while (ans.size() < maxLen){
            ans.add(nums[maxIndex]);
            maxIndex = g[maxIndex];
        }
        return ans;

    }

    public void printNums(int[] nums) {
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums2 = {1, 2, 4, 8};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.largestDivisibleSubset(nums2));
    }
}
