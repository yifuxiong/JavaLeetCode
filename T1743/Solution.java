package T1743;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // 我的思路：单向构造
    public int[] restoreArray(int[][] adjacentPairs) {
        // 找到只有一个关联值的那个元素，不是头就是尾
        Map<Integer, Integer> nums = new HashMap<>();
        // 记录每个元素及其关联的元素
        Map<Integer, List<Integer>> elem = new HashMap<>();

        for (int[] adjacent : adjacentPairs) {
            int left = adjacent[0], right = adjacent[1];
            nums.put(left, nums.getOrDefault(left, 0) + 1);
            nums.put(right, nums.getOrDefault(right, 0) + 1);

            List<Integer> listLeft = elem.getOrDefault(left, new ArrayList<>());
            listLeft.add(right);
            elem.put(left, listLeft);
            List<Integer> listRight = elem.getOrDefault(right, new ArrayList<>());
            listRight.add(left);
            elem.put(right, listRight);
        }

        // 反正找到头或者尾
        int start = -1;
        for (int i : nums.keySet()) {
            if (nums.get(i) == 1) {
                start = i;
                break;
            }
        }

        int n = adjacentPairs.length;
        int[] ans = new int[n + 1];
        ans[0] = start;
        // 与头或尾只有一个元素连接
        ans[1] = elem.get(start).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = elem.get(ans[i]);
            if (list.get(0) == ans[i - 1]) {
                ans[i + 1] = list.get(1);
            } else {
                ans[i + 1] = list.get(0);
            }
        }

        return ans;
    }

    // 双向构造（双指针）
    // https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/solution/gong-shui-san-xie-yi-ti-shuang-jie-dan-x-elpx/

    public static void main(String[] args) {
        int[][] adjacentPairs = {{2, 1}, {3, 4}, {3, 2}};
        int[][] adjacentPairs2 = {{4, -2}, {1, 4}, {-3, 1}};
        int[][] adjacentPairs3 = {{100000, -100000}};

        Solution solut = new Solution();
        int[] ans = solut.restoreArray(adjacentPairs3);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
