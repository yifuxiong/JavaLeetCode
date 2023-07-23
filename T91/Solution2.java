package T91;

import java.util.Arrays;

public class Solution2 {
    public int numDecodings(String s) {
        int n = s.length();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;

        // i从1开始，实际上是从0开始
        for (int i = 1; i <= n; i++) {
            // 当前字符不为'0'
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.parseInt(s.substring(i - 2, i)) <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    // 我的思路
    public int numDecodings2(String s) {
        int n = s.length();
        if (s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[n + 1];
        // 初始化边界。因为第一个字符为'0'的情况已经排除，所以dp[0]=1了
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // 个位数
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // 两位数
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.parseInt(s.substring(i - 2, i)) <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        String s = "12";
        String s2 = "226";
        String s3 = "0";
        String s4 = "06";
        String s5 = "2611055971756562";
        String s6 = "111111111111111111111111111111111111111111111";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.numDecodings(s2));
        System.out.println(solut2.numDecodings2(s2));
    }
}
