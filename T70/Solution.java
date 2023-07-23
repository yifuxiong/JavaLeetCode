package T70;

public class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // 初始条件
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 2;
        int n2 = 3;
        int n3 = 5;

        Solution solut = new Solution();
        System.out.println(solut.climbStairs(n3));
    }
}
