package T1723;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        List<List<Integer>> res = new ArrayList<>();
        traceBack(0, jobs, res, new ArrayList<>());
        System.out.println(res);
        return 0;
    }

    public void traceBack(int start, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));

        for (int i = start; i < nums.length; i++) {
//            if (){
//                // 剪枝
//            }
            tmp.add(nums[i]);
            traceBack(i + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] jobs = {3, 2, 3};
        int k = 3;

        int[] jobs2 = {1, 2, 4, 7, 8};
        int k2 = 2;

        Solution solut = new Solution();
        System.out.println(solut.minimumTimeRequired(jobs, k));
    }
}
