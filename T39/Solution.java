package T39;

import java.util.ArrayList;
import java.util.List;

// 这题是【组合问题总和】【无重复元素】【每个元素无限使用】
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(0, candidates, ans, new ArrayList<>(), target, 0);
        return ans;
    }

    public void backTrace(int start, int[] candidates, List<List<Integer>> ans, List<Integer> tmp, int target, int sum) {
        if (target == sum) {
            ans.add(new ArrayList(tmp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum > target) {
                continue;
            }
            // 选取
            tmp.add(candidates[i]);
            // 回溯
            backTrace(i, candidates, ans, tmp, target, sum + candidates[i]);
            // 撤销
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        int[] candidates2 = {2, 3, 5};
        int target2 = 8;

        Solution solut = new Solution();
        System.out.println(solut.combinationSum(candidates2, target2));
    }
}
