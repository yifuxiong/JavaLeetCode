package T509;

public class Solution {
    // 动态规划
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 一般做法：递归，可能会超时
    public int fib2(int n) {
        if (n == 1 || n == 2)
            return 1;
        return fib2(n - 1) + fib2(n - 2);
    }

    public static void main(String[] args) {
        int n = 2;
        int n2 = 3;
        int n3 = 4;

        Solution solut = new Solution();
        System.out.println(solut.fib(n3));
        System.out.println(solut.fib2(n3));
    }
}
