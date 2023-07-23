package T229;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    // 所有超过n/3的数，至少0个，至多2个
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        int candi1 = nums[0];
        int count1 = 0;
        int candi2 = nums[0];
        int count2 = 0;

        // 投票
        for (int i = 0; i < nums.length; i++) {
            if (candi1 == nums[i]) {
                count1++;
                continue;
            }
            if (candi2 == nums[i]) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                candi1 = nums[i];
                count1 = 1;
                continue;
            }
            if (count2 == 0) {
                candi2 = nums[i];
                count2 = 1;
                continue;
            }

            // 否则的话
            count1--;
            count2--;
        }

        // 计数
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (candi1 == nums[i]) {
                count1++;
            } else if (candi2 == nums[i]) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            ans.add(candi1);
        }
        if (count2 > nums.length / 3) {
            ans.add(candi2);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.majorityElement(nums));
    }
}
