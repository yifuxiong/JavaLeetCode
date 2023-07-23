package T40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 这是【组合总和问题】【有重复元素】【每个元素只能使用一次】
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(0, candidates, ans, new ArrayList<>(), target, 0);
        return ans;
    }

    public void backTrace(int start, int[] candidates, List<List<Integer>> ans, List<Integer> tmp, int target, int sum) {
        if (target == sum) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (sum > target) {
                continue;
            }
            // 注意：这里的选择是同一层的相同元素，不是下一层的相同元素
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            tmp.add(candidates[i]);
            backTrace(i + 1, candidates, ans, tmp, target, sum + candidates[i]);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;

        Solution solut = new Solution();
        System.out.println(solut.combinationSum2(candidates, target));
    }
}
