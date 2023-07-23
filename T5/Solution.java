package T5;

public class Solution {
    // 解法一：动态规划
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[n][n];
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推
        // 先枚举子串长度，l这里指长度（length，不是left）
        for (int l = 2; l <= n; l++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < l; i++) {
                // 由l和i可以确定右边界，即j-i+1=l得
                int j = l + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= n) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {  // 否则字符i与字符j相等
                    if (j - i < 3) {  // 且字符串长度小于等于3
                        dp[i][j] = true;
                    } else {  // 要是字符串长度大于3，则字符串向中间开始递推
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // dp[i][j]表示dp[i...j]是回文，并且当前长度大于maxLen，记录起始位置与最大长度
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // 解法二：中心扩展算法
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数长度的子串
            int len1 = expand(s, i, i);
            // 偶数长度的子串
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                // start = 当前中心i - 左侧长度
                start = i - (len - 1) / 2;
                // end = 当前中心i + 右侧长度
                end = i + len / 2;
            }
        }
        // 左闭右开，所以end要+1
        return s.substring(start, end + 1);
    }

    // 向两边扩展的方法
    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 比如一开始left=right=0，一次循环后left=-1,right=1，但是实际长度为1-(-1)-1==1
        return right - left - 1;
    }

    // Manacher


    public static void main(String[] args) {
        String s = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "ac";

        Solution solut = new Solution();
        System.out.println(solut.longestPalindrome(s));
    }
}
