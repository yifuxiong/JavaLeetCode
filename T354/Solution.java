package T354;

import java.util.Arrays;

// 这题就是T300的增强版，完全一模一样
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes[0].length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return b[1] - a[1];
        });

        for (int[] env : envelopes) {
            System.out.print("<" + env[0] + ", " + env[1] + "> ");
        }
        System.out.println();

        // 每次遍历到一个envelope，遍历它前面比他env[i][1]小的个数
        // 因为env[i][0]肯定是递增排列的，而env[i][1]是按照env[i][0]不同递增，相同递减排列
        int res = 1;
        int[] f = new int[envelopes.length];
        Arrays.fill(f, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    // 实际上f[i]=f[j]+1没错，但是因为中间可能掺杂了多个f[i]，f[i]取最大的
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int[][] envelopes2 = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}};

        Solution solut = new Solution();
        System.out.println(solut.maxEnvelopes(envelopes2));
    }
}
