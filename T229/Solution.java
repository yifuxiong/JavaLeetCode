package T229;

import java.util.ArrayList;
import java.util.List;

// 投票法
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ret;
        }

        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;
        // 投票阶段
        for (int i = 0; i < nums.length; i++) {
            // 前4个if语句，只要有一个符合条件，直接执行下一个nums[i]
            if (cand1 == nums[i]) {
                count1++;
                continue;
            }
            if (cand2 == nums[i]) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                cand1 = nums[i];
                count1++;
                continue;
            }
            if (count2 == 0) {
                cand2 = nums[i];
                count2++;
                continue;
            }
            // 前四条语句都不满足，再count--
            count1--;
            count2--;
        }

        // 计数阶段
        count1 = 0;
        count2 = 0;
        int n = nums.length / 3 + 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cand1) count1++;
            else if (nums[i] == cand2) count2++;
        }
        if (count1 >= n) ret.add(cand1);
        if (count2 >= n) ret.add(cand2);

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        int[] nums2 = {1};
        int[] nums3 = {1, 1, 1, 3, 3, 2, 2, 2};
        int[] nums4 = {2, 1, 1, 3, 1, 4, 5, 6};

        Solution solut = new Solution();
        System.out.println(solut.majorityElement(nums3));
    }
}
