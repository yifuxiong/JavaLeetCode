package T650;

public class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        // 初始化
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            // 先赋一个值，后面再更新
            dp[i] = i;
            for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                if (dp[i] % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 10;

        Solution solut = new Solution();
        System.out.println(solut.minSteps(n));
    }
}
