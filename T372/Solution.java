package T372;

public class Solution {
    static final int MOD = 1337;

    // 倒序遍历
    public int superPow(int a, int[] b) {
        int ans = 1;
        for (int i = b.length - 1; i >= 0; i--) {
            ans = (int) ((long) ans * pow(a, b[i] % MOD));
            a = pow(a, 10);
        }
        return ans;
    }

    // 秦九韶算法
    public int superPow2(int a, int[] b) {
        int ans = 1;
        for (int e : b) {
            ans = (int) ((long) pow(ans, 10) * pow(a, e) % MOD);
        }
        return ans;
    }

    public int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if (n % 2 != 0) {
                res = (int) ((long) res * x % MOD);
            }
            x = (int) ((long) x * x % MOD);
            n /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 2;
        int[] b = {1, 0};

        Solution solut = new Solution();
//        int x = 2, n = 7;
//        System.out.println(solut.pow(x, n));
        System.out.println(solut.superPow(a, b));
        System.out.println(solut.superPow2(a, b));
    }
}
