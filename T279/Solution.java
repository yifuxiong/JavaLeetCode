package T279;

public class Solution {
    // 这题是选择完全平方数的【最少数量】
    // 所以有：
    // 第一维是【总额】
    // 第二维是【不同面额的硬币】
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 初始化：每个数至少可以由1组成，即4=1+1+1+1
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        // i是金额
        for (int i = 1; i <= n; i++) {
            // j是不同面额的硬币
            for (int j = 0; j * j <= i; j++){
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        int n = 12;
        int n2 = 13;

        Solution solut = new Solution();
        System.out.println(solut.numSquares(n));
    }
}
