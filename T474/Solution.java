package T474;

public class Solution {
    // 转换成0-1背包问题
    /*
    * dp[i][j][k] 表示输入字符串在子区间 [0, i]
    * 能够使用 j 个 0 和 k 个 1 的字符串的最大数量
    *
    * dp[i][j][k]={
dp[i−1][j][k],  (不选择当前考虑的字符串，至少是这个数值)
dp[i−1][j−当前字符串使用0的个数][k−当前字符串使用1的个数]+1  (选择当前考虑的字符串)
    * */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        // 初始化。i=0这一行全为0，所以就省略了

        for (int i = 1; i <= len; i++) {  // 注意：i从1开始，因为有一位偏移
            int[] count = countZeroAndOne(strs[i - 1]);
            int zeros = count[0];
            int ones = count[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    } else {
                        // 至少是上一个字符串中包含0和1的个数
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    // strs中每个字符串的 0 和 1 的个数
    public int[] countZeroAndOne(String str) {
        int[] cnt = new int[2];
        for (char c : str.toCharArray()) {
            cnt[c - '0']++;
        }
        return cnt;
    }

    // 空间优化，二维数组
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        dp[0][0] = 0;

        for (String s : strs) {
            int[] count = countZeroAndOne(s);
            int zeros = count[0];
            int ones = count[1];
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;

        String[] strs2 = {"10", "0", "1"};
        int m2 = 1, n2 = 1;

        Solution solut = new Solution();
        System.out.println(solut.findMaxForm(strs, m, n));
        System.out.println(solut.findMaxForm2(strs, m, n));
    }
}
