package T686;

public class Solution {
    // 方法一：长度不会超过2*A + B，暴力搜索
    public int repeatedStringMatch(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int ans = 0;
        while (sb.length() < b.length()) {
            ans++;
            sb.append(a);
        }
        // 结尾还要加上一个a，保证字符串长度在 A+B 到 2*A+B 之间
        sb.append(a);

        int idx = sb.indexOf(b);
        if (idx == -1) {
            return -1;
        }

        if (idx + b.length() > a.length() * ans) {
            return ans + 1;
        } else {
            return ans;
        }
    }

    // 方法二：字符串哈希。宫水三叶
    //
    // 结合「基本分析」，我们知道这本质是一个子串匹配问题，我们可以使用「字符串哈希」来解决。
    // 令 a 的长度为 n，b 的长度为 m。
    //
    // 仍然是先将 a 复制「上界」次，得到主串 ss，目的是从 s 中检测是否存在子串为 b。
    //
    // 在字符串哈希中，为了方便，我们将 ss 和 b 进行拼接，设拼接后长度为 len，
    // 那么 b 串的哈希值为 [len−m+1,len] 部分（下标从 1 开始），记为 target。
    //
    // 然后在 [1,n] 范围内枚举起点，尝试找长度为 m 的哈希值与 target 相同的哈希值。
    public int repeatedStringMatch2(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int ans = 0;
        while (sb.length() < b.length()) {
            ans++;
            sb.append(a);
        }
        sb.append(a);

        int idx = strHash(sb.toString(), b);
        if (idx == -1) {
            return -1;
        }

        if (idx + b.length() > a.length() * ans) {
            return ans + 1;
        } else {
            return ans;
        }
    }

    public int strHash(String ss, String b) {
        int P = 131;
        int n = ss.length(), m = b.length();

        String str = ss + b;
        int len = str.length();
        int[] h = new int[len + 10];
        int[] p = new int[len + 10];
        h[0] = 0;
        p[0] = 1;

        // 初始化p和h
        for (int i = 0; i < len; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + str.charAt(i);
        }

        int r = len, l = r - m + 1;
        int target = h[r] - h[l - 1] * p[r - l + 1];  // b的哈希值
        for (int i = 1; i <= n; i++) {
            int j = i + m - 1;
            int cur = h[j] - h[i - 1] * p[j - i + 1];  // 子串哈希值
            if (cur == target) {
                return i - 1;
            }
        }
        return -1;
    }

    // 方法三：KMP。宫水三叶
    public int repeatedStringMatch3(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int ans = 0;
        while (sb.length() < b.length()) {
            ans++;
            sb.append(a);
        }
        sb.append(a);

        int idx = strStr(sb.toString(), b);
        if (idx == -1) {
            return -1;
        }

        if (idx + b.length() > a.length() * ans) {
            return ans + 1;
        } else {
            return ans;
        }
    }

    public int strStr(String ss, String pp) {
        if (pp.isEmpty()) {
            return 0;
        }

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 在实际编码时，通常我会往原串和匹配串头部追加一个空格（哨兵）。
        // 原串和匹配串前面都加空格，使其下标从1开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建next数组，数组长度为匹配串pp的长度（next数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i=2, j=0开始，i小于等于匹配串长度【构造i从2开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j=next(j)
            while (j > 0 && p[i] != p[j + 1]) {
                j = next[j];
            }
            // 匹配成功的话，先让j++
            if (p[i] == p[j + 1]) {
                j++;
            }
            // 更新next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i=1, j=0开始，i小于等于原串长度【匹配i从1开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j=next(j)
            while (j > 0 && s[i] != p[j + 1]) {
                j = next[j];
            }
            // 匹配成功的话，先让j++，结束本次循环后i++
            if (s[i] == p[j + 1]) {
                j++;
            }
            // 整一段匹配成功，直接返回下标
            if (j == m) {
                return i - m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String a = "abcd";
        String b = "cdabcdab";

        String a2 = "aaac";
        String b2 = "aac";

        Solution solut = new Solution();
        System.out.println(solut.repeatedStringMatch(a, b));
        System.out.println(solut.repeatedStringMatch2(a, b));
        System.out.println(solut.repeatedStringMatch3(a, b));
    }
}
