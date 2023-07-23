package BackTrace.Combination;

// 组合问题回溯法：
// https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/

import java.util.ArrayList;
import java.util.List;

// 子集问题I。【没有重复】元素。
/*
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * */
public class Combination_I {
    public List<List<Integer>> combiI(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(0, nums, ans, new ArrayList<>());
        return ans;
    }

    public void backTrace(int start, int[] nums, List<List<Integer>> ans, List<Integer> tmp) {
        ans.add(new ArrayList<>(tmp));

        for (int i = start; i < nums.length; i++) {
            // 剪枝操作

            // 选取
            tmp.add(nums[i]);
            // 回溯
            backTrace(i + 1, nums, ans, tmp);
            // 撤销
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        Combination_I cI = new Combination_I();
        System.out.println(cI.combiI(nums));
    }
}
