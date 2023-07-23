package T395;

import java.util.HashMap;
import java.util.Map;

// 分治思想：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/jie-ben-ti-bang-zhu-da-jia-li-jie-di-gui-obla/
public class Solution {
    public int longestSubstring(String s, int k) {
        // 先计算s字符串中每个字符的数量
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (char ch : map.keySet()) {
            // 如果某个字符的数量小于k，说明最终结果的字符串肯定不包括这个字符
            if (map.get(ch) < k) {
                int res = 0;
                // 从ch这里分开，分成很多个字符串
                for (String newS : s.split(String.valueOf(ch))) {
                    res = Math.max(res, longestSubstring(newS, k));
                }
                return res;
            }
        }
        // 否则，没有分割，直接返回s的长度
        return s.length();
    }

    public static void main(String[] args) {
        String s = "aaabb";
        int k = 3;

        String s2 = "ababbc";
        int k2 = 2;

        Solution solut = new Solution();
        System.out.println(solut.longestSubstring(s, k));
    }
}
