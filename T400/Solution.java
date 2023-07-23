package T400;


public class Solution {
    // 1 <= n <= 2^(31)-1
    public int findNthDigit(int n) {
        if (1 <= n && n <= 9) {
            return n;
        }
        // length从0开始计算，每数cnt个数字，数字的位数变为i位
        // [1-9]中共有9个数，都是1位数，
        // [10-99]中共有90个数，都是2位数，
        // ...
        // [10000-99999]中共有90000个数，都是5位数，
        // ...
        long length = 0;
        int cnt = 9, i = 1;
        while (length + cnt * i < n) {
            length += cnt * i;
            cnt *= 10;
            i++;
        }

        // 退出循环后，一定是从[10,100,1000,...]开始
        // 现在我们知道length起始长度，cnt这个阶段有多少个数，i位数
        // 反推出当前数字的哪一位

        // 起始[10,100,1000,...]
        long start = (long) Math.pow(10, i - 1);
        // 从起始数字往后的第几个数
        long end = (n - length - 1) / i;
        // 这里减1的原因是，我们虽然刚好数到当前数字，但是当前数字不止一位，
        // 因此单独把这个数字拿出来研究，需要把前n-length-1位去掉后另行计算

        // 这个数字上的第几位
        int pos = (int) (n - length - 1) % i;
        long number = start + end;
        // System.out.println(number);

        String num_str = String.valueOf(number);
        return num_str.charAt(pos) - '0';
    }

    // 上面的代码提交会超时
    // 改了一段代码之后就不超时了，顺便击败了100%
    // 不知道是什么原因
    public int findNthDigit2(int n) {
        if (1 <= n && n <= 9) {
            return n;
        }
        // length从0开始计算，每数cnt个数字，数字的位数变为i位
        // [1-9]中共有9个数，都是1位数，
        // [10-99]中共有90个数，都是2位数，
        // ...
        // [10000-99999]中共有90000个数，都是5位数，
        // ...
        long length = 0, cnt = 9, i = 1;
        while (length + cnt * i < n) {
            length += cnt * i;
            cnt *= 10;
            i++;
        }
        // 退出循环后，一定是从[10,100,1000,...]开始
        // 现在我们知道length起始长度，cnt这个阶段有多少个数，i位数
        // 反推出当前数字的哪一位

        long number = (long) Math.pow(10, i - 1) + (n - length - 1) / i;
        long pos = (n - length - 1) % i;
        // System.out.println(number);

        String num_str = String.valueOf(number);
        return num_str.charAt((int) pos) - '0';
    }

    public static void main(String[] args) {
        int n = 189;

        Solution solut = new Solution();
        System.out.println(solut.findNthDigit(n));
        System.out.println(solut.findNthDigit2(n));
    }
}
