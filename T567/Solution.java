package T567;

import java.util.Arrays;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int k = s1.length(), n = s2.length();
        if (k > n) {
            return false;
        }
        // 先把s1转换为数组，排好序
        char[] S1 = s1.toCharArray();
        Arrays.sort(S1);
        // System.out.println(S1);

        int left = 0, right = k - 1;
        // 滑动窗口
        while (right < n) {
            char[] S2 = s2.substring(left, right + 1).toCharArray();
            Arrays.sort(S2);
            // System.out.println(S2);

            if (!Arrays.equals(S1, S2)){
                left++;
                right++;
            }else{
                // 相等返回true
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";

        String s12 = "ab";
        String s22 = "eidboaoo";

        Solution solut = new Solution();
        System.out.println(solut.checkInclusion(s12, s22));
    }
}
