package Hanoi;

public class Solution2 {
    // 3个柱子，将第n层的盘子从最左侧移动到最右侧的最少次数
    public int M3(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * M3(n - 1) + 1;
    }

    // 4个柱子，将第n层的盘子从最左侧移动到最右侧的最少次数
    public int M4(int n) {
        // m是一个很大的数
        int tmp, m = 1 << 16;

        if (1 == n) {
            return 1;
        }

        for (int i = 1; i < n; i++) {
            tmp = 2 * M4(n - i) + ((1 << i) - 1);
            // m是较小值
            if (tmp < m) {
                m = tmp;
            }
        }
        return m;
    }

    // 4个柱子
    public int M4_c(int n) {
        if (n == 1) {
            return 1;
        }

        int maxNum = Integer.MAX_VALUE;
        int cur = 0;
        for (int i = 1; i < n; i++) {
            // 当前值
            cur = 2 * M4_c(n - i) + ((int) Math.pow(2, i) - 1);
            if (cur < maxNum) {
                maxNum = cur;
            }
        }
        return maxNum;
    }

    // 动态规划
    public int M4_mem(int n) {
        int tmp, m = 1 << 16;
        int[] M4_tab = new int[32];
        M4_tab[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i + 1; j++) {
                tmp = 2 * M4_tab[i - j] + ((1 << j) - 1);
                if (tmp < m) {
                    m = tmp;
                }
            }
            M4_tab[i] = m;
            m = 1 << 16;
        }
        return M4_tab[n - 1];
    }

    public static void main(String[] args) {
        int n = 10;
        Solution2 solut = new Solution2();
        System.out.println(solut.M3(n));
        System.out.println(solut.M4(n));
        System.out.println(solut.M4_c(n));
        System.out.println(solut.M4_mem(n));
    }
}
