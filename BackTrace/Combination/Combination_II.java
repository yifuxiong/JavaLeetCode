package BackTrace.Combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 子集问题II。【有重复】元素。
/*
* 给定一个可能 包含重复元素 的整数数组 nums，返回该数组所有可能的子集（幂集）。
输入: [1,2,2]
输出:
[
[2],
[1],
[1,2,2],
[2,2],
[1,2],
[]
]
* */
public class Combination_II {
    public List<List<Integer>> combiII(int[] nums) {
        // 先对nums排序
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        backTrace(0, nums, ans, new ArrayList<>());
        return ans;
    }

    public void backTrace(int start, int[] nums, List<List<Integer>> ans, List<Integer> tmp) {
        ans.add(new ArrayList<>(tmp));

        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            tmp.add(nums[i]);
            backTrace(i + 1, nums, ans, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2};
        int[] nums2 = {2, 2, 2};

        Combination_II cII = new Combination_II();
        System.out.println(cII.combiII(nums2));
    }
}
