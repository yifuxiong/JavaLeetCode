package BackTrace.Arrangement;

import java.util.ArrayList;
import java.util.List;

// 排列问题回溯法：
// https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-ga-4/


// 全排列问题，无重复数字
/*
* 给定一个【没有重复】数字的序列，返回其所有可能的全排列。
输入: [1,2,3]
输出:
[
[1,2,3],
[1,3,2],
[2,1,3],
[2,3,1],
[3,1,2],
[3,2,1]
]
* */
public class Permutations_I {
    public List<List<Integer>> permuteI(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 是否使用的数组
        boolean[] used = new boolean[nums.length];
        // used数组初始化默认为false

        backTrace(nums, used, ans, new ArrayList<>());
        return ans;
    }

    public void backTrace(int[] nums, boolean[] used, List<List<Integer>> ans, List<Integer> tmp) {
        // 结束条件
        if (tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 选取
                tmp.add(nums[i]);
                // 标记：已经使用过了
                used[i] = true;
                // 进入下一层
                backTrace(nums, used, ans, tmp);
                used[i] = false;
                // 撤销
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        Permutations_I pI = new Permutations_I();
        System.out.println(pI.permuteI(nums));
    }
}
