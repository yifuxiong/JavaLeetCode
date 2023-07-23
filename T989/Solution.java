package T989;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ret = new ArrayList<>();

        int lenA = A.length;
        int level = 0;
        int i = lenA - 1;

        // 如果A的长度比K长
        while (i >= 0) {
            if (K != 0) {
                ret.add(0, (A[i] + K % 10 + level) % 10);
                level = (A[i] + K % 10 + level) / 10;
                K = K / 10;
            } else {
                ret.add(0, (A[i] + K % 10 + level) % 10);
                level = (A[i] + K % 10 + level) / 10;
            }
            i--;
        }

        // 如果A的长度比K短
        while (K != 0) {
            ret.add(0, (K % 10 + level) % 10);
            level = (K % 10 + level) / 10;
            K = K / 10;
        }
        // 进位上的数如果不是0，那么最高位上再进一位
        if (level != 0) {
            ret.add(0, level);
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 0, 0};
        int K = 34;

        int[] A2 = {2, 1, 5};
        int K2 = 806;

        int[] A3 = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        int K3 = 1;

        int[] A4 = {1};
        int K4 = 999999;

        int[] A5 = {0};
        int K5 = 23;

        Solution solut = new Solution();
        System.out.println(solut.addToArrayForm(A5, K5));
    }
}
