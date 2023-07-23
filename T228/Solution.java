package T228;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();

        int n = nums.length;
        if (n == 0) {
            return ret;
        }

        int start = nums[0], end = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == end) {
                    String str = "" + start;
                    ret.add(str);
                } else {
                    String str = start + "->" + end;
                    ret.add(str);
                }
                start = end = nums[i];
            } else {
                end = nums[i];
            }
        }
        // 最后i大于n了，还要把末尾的算上
        if (start == end) {
            String str = "" + start;
            ret.add(str);
        } else {
            String str = start + "->" + end;
            ret.add(str);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};
        int[] nums3 = {};
        int[] nums4 = {-1};
        int[] nums5 = {0};

        Solution solut = new Solution();
        System.out.println(solut.summaryRanges(nums));
    }
}
