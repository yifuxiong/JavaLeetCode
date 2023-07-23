package T1744;

public class Solution2 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int m = candiesCount.length, n = queries.length;

        // 前缀和
        long[] sum = new long[m];
        sum[0] = candiesCount[0];
        for (int i = 1; i < m; ++i) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }

//        for (int i = 0; i < m; i++) {
//            System.out.print(sum[i] + " ");
//        }
//        System.out.println();

        boolean[] ans = new boolean[n];
        for (int i = 0; i < n; i++) {
            // 每天最少吃一颗
            boolean a = (queries[i][1] + 1) <= sum[queries[i][0]];
            // 每天吃最多的糖果
            boolean b = (long) (queries[i][1] + 1) * queries[i][2] >= (queries[i][0] == 0 ? 1 : sum[queries[i][0] - 1] + 1);
            ans[i] = a && b;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] candiesCount = {7, 4, 5, 3, 8};
        int[][] queries = {{0, 2, 2}, {4, 2, 4}, {2, 13, 1000000000}};

        int[] candiesCount2 = {5, 2, 6, 4, 1};
        int[][] queries2 = {{3, 1, 2}, {4, 10, 3}, {3, 10, 100}, {4, 100, 30}, {1, 3, 1}};

        Solution2 solut2 = new Solution2();
        boolean[] ans = solut2.canEat(candiesCount2, queries2);
        for (boolean a : ans) {
            System.out.println(a);
        }
    }
}
