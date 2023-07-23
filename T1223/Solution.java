package T1223;

// 抄的标答，三维dp
public class Solution {
    static final int MOD = (int) 1e9 + 7;

    public int dieSimulator(int n, int[] rollMax) {
        int[][][] d = new int[n + 1][6][16];
        for (int j = 0; j < 6; j++) {
            d[1][j][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            d[i][p][1] = (d[i][p][1] + d[i - 1][j][k]) % MOD;
                        } else if (k + 1 <= rollMax[j]) {
                            d[i][p][k + 1] = (d[i][p][k + 1] + d[i - 1][j][k]) % MOD;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res = (res + d[n][j][k]) % MOD;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] rollMax = {1, 1, 2, 2, 2, 3};
        // 34

        int n2 = 2;
        int[] rollMax2 = {1, 1, 1, 1, 1, 1};
        // 30

        int n3 = 3;
        int[] rollMax3 = {1, 1, 1, 2, 2, 3};
        // 181

        Solution solut = new Solution();
        System.out.println(solut.dieSimulator(n3, rollMax3));
    }
}
