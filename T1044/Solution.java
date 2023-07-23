package T1044;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    long[] h, p;

    // 方法一：字符串哈希 + 二分
    public String longestDupSubstring(String s) {
        int P = 1313131, n = s.length();

        // 构造哈希数组h和次方数组p
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        // 初始化
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }

        String ans = "";
        int l = 0, r = n;
        while (l < r) {
            // int mid = l + r + 1 >> 1;
            int mid = l + (r - l + 1) / 2;
            // 检查s中是否包含有长度为mid的重复子串
            String t = check(s, mid);
            if (t.length() != 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }

    public String check(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            // 得到s[i...j]，长度为len，这一段字符串的哈希值
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(cur)) {
                return s.substring(i - 1, j);
            }
            set.add(cur);
        }
        return "";
    }

    // 方法二：后缀数组 or DC3
    public String longestDupSubstring2(String s) {
        return "";
    }

    public static void main(String[] args) {
        String s = "banana";
        String s2 = "abcd";

        Solution solut = new Solution();
        System.out.println(solut.longestDupSubstring(s));
    }
}
