package T633;

public class Solution {
    // 枚举
    public boolean judgeSquareSum(int c) {
        int upBoundary = (int) Math.sqrt(c);

        for (int a = 0; a <= upBoundary; a++) {
            int b = (int) Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }

    // 双指针
    public boolean judgeSquareSum2(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);

        while (left <= right) {
            int ans = left * left + right * right;
            if (ans == c) {
                return true;
            } else if (ans < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    // 费马平方和
    public boolean judgeSquareSum3(int c) {
        for (int i = 2, cnt = 0; i * i <= c; i++, cnt = 0) {
            while (c % i == 0 && ++cnt > 0) {
                c /= i;
            }
            if (i % 4 == 3 && cnt % 2 != 0) {
                // 4k + 3的形式，且是奇数
                return false;
            }
        }
        return c % 4 != 3;
    }

    public static void main(String[] args) {
        int c = 5;

        Solution solut = new Solution();
        System.out.println(solut.judgeSquareSum(c));
        System.out.println(solut.judgeSquareSum2(c));
        System.out.println(solut.judgeSquareSum3(c));
    }
}
