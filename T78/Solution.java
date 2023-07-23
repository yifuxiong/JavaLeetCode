package T78;

import java.util.ArrayList;
import java.util.List;

// 官方：方法一：迭代法实现子集枚举
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        // 要返回的结果
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> tmp = new ArrayList<>();
        // 这里 1 << n 等于8，因为n为3，1向右移动3位变成二进制1000，即为8
        for (int mask = 0; mask < (1 << n); mask++) {
            tmp.clear();
            // n等于3，i从0到2
            for (int i = 0; i < n; i++){
                // 1 << i，1分别向左移动0,1,2位，分别等于1,2,4，二进制为001,010,100，即分别对应二进制的各个位
                // 而mask从0到7，(mask & (1 << i))即是在说，0到7化成二进制，位上不是0就遍历到
                if ((mask & (1 << i)) != 0){
                    tmp.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(tmp));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] nums2 = {0};

        Solution solut = new Solution();
        System.out.println(solut.subsets(nums));
    }
}
