package Huiwen_String;

import java.util.ArrayList;
import java.util.List;

// 经典例题：T5，https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
// T132, T214, T516
public class Solution {
    // 这里针对T5

    // Manacher算法，专门解决回文字符串问题
    public String longestPalindrome(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");

        // 先添加特殊字符，把s变为长度为奇数的字符串
        for (int i = 0; i < s.length(); i++) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        // 臂长
        List<Integer> arm_len = new ArrayList<>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); i++) {
            int cur_arm_len;
            // i在右边界内
            if (right >= i) {
                // i在j为中心的字符串的对称位置
                int i_sym = j * 2 - i;
                // right-i是i的对称点i_sym的臂长
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                // 再向两边扩展
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                // 否则以i为中心点直接向两边扩展
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);

            // j是当前最长字符串的中心位置，如果j加上臂长超过右边界
            if (j + cur_arm_len > right){
                j = i;
                right = i + cur_arm_len;
            }

            // 如果当前最长字符串长度超过之前的记录
            if (cur_arm_len * 2 + 1 > end - start){
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        // 去掉'#'
        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; i++){
            if (s.charAt(i) != '#'){
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return (right - left - 2) / 2;
    }

    public static void main(String[] args) {
        String s = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "ac";

        Solution solut = new Solution();
        System.out.println(solut.longestPalindrome(s));
    }
}
