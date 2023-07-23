package T60;

import java.util.ArrayList;
import java.util.List;

// 这题如果用回溯法就必须要优化，否则一定会TLE
// 要不就用数学方法解决
public class Solution {
    public String getPermutation(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i]++;
        }

        backTrack(nums, 1, ans, new ArrayList<>(), k);
        System.out.println(ans);

        StringBuffer sb = new StringBuffer();
        for (int a : ans.get(k - 1)) {
            sb.append(a);
        }
        return sb.toString();
    }

    public void backTrack(int[] nums, int level, List<List<Integer>> ans, List<Integer> tmp, int k) {
        if (level == nums.length) {
            if (ans.size() <= k){
                ans.add(new ArrayList<>(tmp));
            }
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            // 剪枝
            if (nums[i] == 0) {
                continue;
            }

            tmp.add(i);
            nums[i]--;
            backTrack(nums, level + 1, ans, tmp, k);
            nums[i]++;
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 3, k = 3;
        int n2 = 4, k2 = 9;
        int n3 = 3, k3 = 1;
        int n4 = 9, k4 = 100;

        Solution solut = new Solution();
        System.out.println(solut.getPermutation(n4, k4));
    }
}
