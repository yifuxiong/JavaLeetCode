package T1018;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> ret = new ArrayList<>();
        long sum = 0;
        for (int i = 0; i < A.length; i++) {
            // 这个地方对sum来个取余，否则会溢出
            sum = sum * 2 % 5 + A[i];
            System.out.println("sum=" + sum);
            if (sum % 5 == 0) {
                ret.add(true);
            } else {
                ret.add(false);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] A = {0, 1, 1};
        int[] A2 = {1, 1, 1};
        int[] A3 = {0, 1, 1, 1, 1, 1};
        int[] A4 = {1, 1, 1, 0, 1};
        int[] A5 = {1, 1, 0, 0, 0, 1, 0, 0, 1};
        // A6 Integer类型越界了
        int[] A6 = {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1};
        // A7 overflow溢出
        int[] A7 = {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0};
        Solution solut = new Solution();
        List<Boolean> ret = solut.prefixesDivBy5(A7);
        System.out.println(ret);
    }
}
