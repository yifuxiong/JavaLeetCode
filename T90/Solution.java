package T90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // 由于包含重复的数字，所以我们把数组先排序，再进行回溯法
    // 若回溯过程中，本次遍历与上次遍历的数字相同，那么就pass，
    // 这样就可以避免重复结果
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        backTrace(0, nums, res, new ArrayList<>());
        return res;
    }

    /**
     * @param start 起始索引
     * @param nums  遍历的数组
     * @param res   最终返回的结果
     * @param tmp   临时数组
     */
    public static void backTrace(int start, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        // 结束条件：放到最终数组中
        res.add(new ArrayList<Integer>(tmp));

        // 选择列表
        for (int i = start; i < nums.length; i++) {
            // 判断是否需要枝减：此处需要去重
            // 只要本次遍历的数字与上次相同，就跳过
            if (i > start && nums[i] == nums[i - 1]) {
                // 注意这里是i > start
                // start之前没有重复，再保证i > start之后也不会重复，整体就不会重复
                // 若写成i > 0，则会把[1, 2, 2]这样的结果也去掉
                continue;
            }

            // 进行选择
            tmp.add(nums[i]);
            // 进入下一层
            backTrace(i + 1, nums, res, tmp);
            // 撤销选择：开始新一轮选择，把上次选择的放回去
            tmp.remove(tmp.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        int[] nums2 = {0};

        Solution solut = new Solution();
        List<List<Integer>> res = solut.subsetsWithDup(nums2);
        System.out.println(res);
    }
}
