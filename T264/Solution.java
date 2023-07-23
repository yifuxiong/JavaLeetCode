package T264;

import java.util.Vector;

public class Solution {
    // 动态规划
    public int nthUglyNumber(int n) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);

        int key2 = 0, key3 = 0, key5 = 0;
        int i = 0;
        while (i < n - 1) {
            int a = vector.get(key2) * 2;
            int b = vector.get(key3) * 3;
            int c = vector.get(key5) * 5;
            int next = Math.min(Math.min(a, b), c);
            vector.add(next);

            if (next == a) {
                key2++;
            }
            if (next == b) {
                key3++;
            }
            if (next == c) {
                key5++;
            }
            i++;
        }
        return vector.get(vector.size() - 1);
    }

    public static void main(String[] args) {
        int n = 7;

        Solution solut = new Solution();
        System.out.println(solut.nthUglyNumber(n));
    }
}
