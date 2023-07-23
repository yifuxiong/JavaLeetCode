package BackTrace.Combination;


import java.util.ArrayList;
import java.util.List;

// 组合总和问题。【无重复】元素。
/*
* 给定一个无重复元素的数组 candidates 和一个目标数 target，
* 找出 candidates 中所有可以使数字和为 target 的组合。
* candidates 中的数字可以无限制重复被选取。
输入: candidates = [1,2,3], target = 3,
所求解集为:
[
[1,1,1],
[1,2],
[3]
]
* */
public class Combination_Sum {
    public List<List<Integer>> combiSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(0, nums, ans, new ArrayList<>(), target, 0);
        return ans;
    }

    public void backTrace(int start, int[] nums, List<List<Integer>> ans, List<Integer> tmp, int target, int sum) {
        if (target == sum) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (sum > target) {
                continue;
            }
            // 选取
            tmp.add(nums[i]);
            // 回溯
            // i不用+1(重复利用)，并更新当前状态的sum
            backTrace(i, nums, ans, tmp, target, sum + nums[i]);
            // 撤销
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 3;

        int[] nums2 = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        int target2 = 12;

        Combination_Sum cS = new Combination_Sum();
        System.out.println(cS.combiSum(nums2, target2));
    }
}
