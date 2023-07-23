package T62;

public class Solution {
    // 方法一：动态规划
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        // 设置边界条件
        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            f[0][j] = 1;
        }

        // 开始遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    // 方法二：数学思路，排列组合
    public int uniquePaths2(int m, int n) {
        /**
         * 从左上角到右下角的过程，总共需要移动(m-1)+(n-1)次，即m+n-2次。
         * 其中向下m-1次，向右n-1次。
         * 故总共C(m+n-2)_(m-1)或者C(m+n-2)_(n-1)次
         * 结果为(m+n-2).(m+n-1)...n/(m-1)!
         * 也可以变形为(m+n-2)!/[(m-1)!(n-1)!]
         */
        long res = 1;
        // i从n开始到 <(n+m-1)，即i从n乘到(n+m-2)；j从1到 <m，即j从1乘到(m-1)
        for (int i = n, j = 1; j < m; i++, j++) {
            res = res * i / j;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        Solution solut = new Solution();
        int m = 3;
        int n = 3;
        System.out.println(solut.uniquePaths(m, n));
        System.out.println(solut.uniquePaths2(m, n));
    }
}
