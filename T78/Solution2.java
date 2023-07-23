package T78;

import java.util.ArrayList;
import java.util.List;

// 回溯法：https://leetcode-cn.com/problems/subsets/solution/hui-su-suan-fa-by-powcai-5/
public class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(0, nums, ans, new ArrayList<>());
        return ans;
    }

    private void backTrace(int start, int[] nums, List<List<Integer>> ans, ArrayList<Integer> tmp) {
        // 终止条件，将结果temp加进ans中
        ans.add(new ArrayList<>(tmp));

        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backTrace(i + 1, nums, ans, tmp);  // 递归进入下一层，继续选择
            tmp.remove(tmp.size() - 1);  // 撤销选择，去掉最后一个索引
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums2 = {0};

        Solution2 solut = new Solution2();
        System.out.println(solut.subsets(nums));
    }
}
