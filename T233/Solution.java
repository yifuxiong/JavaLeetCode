package T233;

public class Solution {
    // 超时
    public int countDigitOne(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            int remain = i;
            while (remain != 0) {
                if (remain % 10 == 1) {
                    sum++;
                }
                remain = remain / 10;
            }
        }
        return sum;
    }

    // 改进
    // https://leetcode-cn.com/problems/number-of-digit-one/solution/shu-xue-fen-xi-ni-ming-bai-liao-yao-by-62e64arp6t/
    public int countDigitOne2(int n) {
        int sum = 0;
        int remain = n;
        // 修正值
        long i = 1;
        while (remain > 0) {
            if (remain % 10 == 0) {
                sum += (remain / 10) * i;
            }
            if (remain % 10 == 1) {
                sum += (remain / 10) * i + (n % i) + 1;
            }
            if (remain % 10 > 1) {
                sum += (remain / 10) * i + i;
            }
            remain = remain / 10;
            i = i * 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 13;

        Solution solut = new Solution();
        System.out.println(solut.countDigitOne(n));
        System.out.println(solut.countDigitOne2(n));
    }
}
