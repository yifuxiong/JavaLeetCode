package T517;

import java.util.Arrays;

public class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int total = Arrays.stream(machines).sum();
        if (total % n != 0) {
            return -1;
        }

        int avg = total / n;  // 最终每个位置上的数字
        int ans = 0, sum = 0;
        for (int num : machines) {
            num -= avg;  // num>0即需要减少的衣服数，<0则是需要补充的衣服数
            sum += num;  // 组A需要减少或者补充的衣服数
            ans = Math.max(ans, Math.max(Math.abs(sum), num));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] machines = {4, 0, 4, 0};

        Solution solut = new Solution();
        System.out.println(solut.findMinMoves(machines));
    }
}
