package BackTrace.Arrangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 全排列问题，有重复数字
/*
* 给定一个可【包含重复】数字的序列，返回所有不重复的全排列。
输入: [1,2,2]
输出:
[
[1,2,2],
[2,1,2],
[2,2,1]
]
* */
public class Permutations_II {
    public List<List<Integer>> permuteII(int[] nums) {
        // 先对nums排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backTrace(nums, used, ans, new ArrayList<>());
        return ans;
    }

    public void backTrace(int[] nums, boolean[] used, List<List<Integer>> ans, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 剪枝
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true) {
                    continue;
                }
                // 选取
                tmp.add(nums[i]);
                // 回溯
                used[i] = true;
                backTrace(nums, used, ans, tmp);
                used[i] = false;
                // 撤销
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2};

        Permutations_II pII = new Permutations_II();
        System.out.println(pII.permuteII(nums));
    }
}
