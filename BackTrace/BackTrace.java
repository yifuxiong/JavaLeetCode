package BackTrace;

import java.util.ArrayList;
import java.util.List;


// 回溯法问题：T78, T90, T91, T115, T131, T842, T1239

// 组合问题回溯法：
// https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/
// 排列问题回溯法：
// https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-ga-4/

// T22(可以重复选择)
// T77(从前往后选择，不许回头)
// T869(选项有重复元素，有限次重复选择)
// T301(回溯法做减法)

// 本题是T90，属于组合问题
public class BackTrace {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrace(0, nums, ans, new ArrayList<>());
        return ans;
    }

    private void backtrace(int i, int[] nums, List<List<Integer>> ans, ArrayList<Integer> tmp) {
        // 结束条件：放到最终数组中
        ans.add(new ArrayList<>(tmp));

        for (int j = i; j < nums.length; j++) {
            // 判断是否需要剪枝：这里不需要

            // 进行选择
            tmp.add(nums[j]);
            // 进入下一层
            backtrace(j + 1, nums, ans, tmp);
            // 撤销选择：去掉最后一个索引
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums2 = {0};

        BackTrace bc = new BackTrace();
        System.out.println(bc.subsets(nums));
    }
}
