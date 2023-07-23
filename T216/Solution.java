package T216;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums = new int[10];
        for (int i = 1; i <= 9; i++) {
            nums[i]++;
        }
        backTrack(nums, 0, 0, k, n, ans, new ArrayList<>());
        return ans;
    }

    public int getSum(List<Integer> tmp) {
        int sum = 0;
        for (int t : tmp) {
            sum += t;
        }
        return sum;
    }

    public void backTrack(int[] nums, int pos, int level, int k, int n, List<List<Integer>> ans, List<Integer> tmp) {
        if (level == k) {
            if (getSum(tmp) == n) {
                ans.add(new ArrayList<>(tmp));
            }
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            // 剪枝
            if (nums[i] == 0) {
                continue;
            }

            tmp.add(i);
            nums[i]--;
            backTrack(nums, i + 1, level + 1, k, n, ans, tmp);
            nums[i]++;
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int k = 3, n = 7;
        int k2 = 3, n2 = 9;

        Solution solut = new Solution();
        System.out.println(solut.combinationSum3(k2, n2));
    }
}
