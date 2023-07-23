package T483;

// 最小好进制：
// 对于给定的整数N，如果N的k（k>=2）进制数的所有数位全为1，则称k是N的一个好进制。

// 提示：等比数列的前N项和公式 Sn=[a1*(1-q^n)]/(1-q), q!=1。
// ps：这里的a1是q^0=1；n是最后转换成111...11的位数（官方题解详细说明）；q是我们要找的k。
// 注意：上溢。

// 建议看官方题解，写的很详细。
public class Solution {
    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);

        // m从高到低遍历，m的范围是[1, logk(n))
        // 且logk(n)最大为log2(10^18)，这里的10^18是n的上限
        // 得出结论：1 <= m < log2(n)。m等于1时，返回n-1。
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));

        // 进入循环遍历
        for (int m = mMax; m > 1; m--) {
            // k的取值为m次根号n
            int k = (int) Math.pow(nVal, 1.0 / m);

            long mul = 1, sum = 1;
            // 等比数列求和
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }

    public static void main(String[] args) {
        String n = "13";
        String n2 = "4681";
        String n3 = "1000000000000000000";

        Solution solut = new Solution();
        System.out.println(solut.smallestGoodBase(n));
    }
}
