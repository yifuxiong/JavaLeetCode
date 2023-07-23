package T132;

import java.util.Arrays;

// 官方：动态规划
public class Solution2 {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];

        // 给g中每行每列都赋值为true
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], true);
        }

        // 判断回文
        // 从右到左
        for (int i = n - 1; i >= 0; i--) {
            // 从左到右
            for (int j = i + 1; j < n; j++) {
                g[i][j] = (s.charAt(i) == s.charAt(j) && g[i + 1][j - 1]);
            }
        }

        // f[i]表示前缀s[0...i]的最少分割次数
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        // 遍历每行
        for (int i = 0; i < n; i++) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        String s = "aab";
        String s2 = "ababababababababababababcbabababababababababababa";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.minCut(s));
    }
}
