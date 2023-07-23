package T448;

import java.util.ArrayList;
import java.util.List;

// 超时
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            ans.add(i);
        }
        System.out.println(ans);

        for (int a: nums){
            // 删除第一次出现的元素
            // 转换成Object对象，这样删除的就不是索引而是值了
            ans.remove(Integer.valueOf(a));
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};

        Solution solut = new Solution();
        System.out.println(solut.findDisappearedNumbers(nums));
    }
}
