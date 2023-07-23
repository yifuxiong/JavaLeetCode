package T448;

import java.util.ArrayList;
import java.util.List;

// 官方做法
public class Solution2 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int num: nums){
            int i = (num - 1) % n;
            nums[i] += n;
        }

        for (int i = 0; i < n; i++){
            if (nums[i] <= n){
                ans.add(i+1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.findDisappearedNumbers(nums));
    }
}
