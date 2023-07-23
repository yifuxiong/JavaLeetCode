package T338;

public class Solution {
    // 动态规划--最低有效位
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];

        ans[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                ans[i] = ans[i - 1] + 1;  // 这里的+1表示除2的余数
            } else {  // 0, 2, 4, 8, ...或者3, 6, 12, ...你会发现它们1的个数是相同的
                ans[i] = ans[i / 2];
            }
        }
        return ans;
    }

    // 动态规划--最高有效位
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];

        ans[0] = 0;
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            // 2的整数幂都是1个1，比如0, 1, 10, 100, 1000
            // 判断这个数是否是2的整数幂，可知10 & 01 == 0, 100 & 011 == 0
            // 即n & (n-1) == 0，表示n是2的整数幂
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            // 比如ans[7]=ans[3]+1, ans[3]=ans[2]+1, ans[2]=ans[0]+1
            ans[i] = ans[i - highBit] + 1;
        }
        return ans;
    }

    // 动态规划--设置最低位
    public int[] countBits3(int num) {
        int[] ans = new int[num + 1];

        ans[0] = 0;
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int num = 2;
        int num2 = 5;

        Solution solut = new Solution();
        int[] ans = solut.countBits3(num2);
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
