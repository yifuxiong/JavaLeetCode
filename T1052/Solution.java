package T1052;

public class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;

        // 先计算老板不管生不生气时，顾客满意时间
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }

        // 再计算老板控制脾气，顾客额外增加的满意时间
        int increase = 0, maxIncrease = 0;
        int left = 0, right = 0;
        while (right < n) {
            // 小于窗口大小
            if (right < left + X - 1) {
                if (grumpy[right] == 1) {
                    increase += customers[right];
                }
                right++;
            } else if (right == left + X - 1){
                if (grumpy[right] == 1) {
                    increase += customers[right];
                }
                right++;
                maxIncrease = Math.max(increase, maxIncrease);
            }else {
                // 整个窗口开始右移
                if (grumpy[left] == 1) {
                    increase -= customers[left];
                }
                if (grumpy[right] == 1) {
                    increase += customers[right];
                }
                left++;
                right++;
                maxIncrease = Math.max(increase, maxIncrease);
            }
        }
        return total + maxIncrease;
    }

    public static void main(String[] args) {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;

        Solution solut = new Solution();
        System.out.println(solut.maxSatisfied(customers, grumpy, X));
    }
}
