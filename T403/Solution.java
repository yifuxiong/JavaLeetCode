package T403;

public class Solution {
    // 自己写一遍
    public boolean canCross(int[] stones) {
        int n = stones.length;

        // 有n个石头，0->1的位置固定只能跳1
        // 之后每次跳跃，距离都是前一次距离k的基础上，k-1, k, k+1
        // idx(0)->idx(1)，跳1。这里的idx(0)实际上第1个石头
        // 1->2，最多跳2
        // 2->3，最多跳3
        // ...
        // (n-1)->上岸，最多跳n
        // 因此dp的第二维可以使n+1，从0到n

        // n个石头，n+1的距离
        boolean[][] dp = new boolean[n][n + 1];
        // 初始条件：青蛙在第1个石头上，从1个石头跳到第2个石头，只能跳1
        dp[0][1] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 因为可以间隔几个石头跳，所以也要看前面的距离
                int k = stones[i] - stones[j];
                if (k <= j + 1) {
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    if (i == n - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
        }

//        for (int i = 0; i < n; i++){
//            for (int j = 0; j <= n; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return false;
    }

    public static void main(String[] args) {
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        int[] stones2 = {0, 1, 2, 3, 4, 8, 9, 11};

        Solution solut = new Solution();
        System.out.println(solut.canCross(stones));
    }
}
