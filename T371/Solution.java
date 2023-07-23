package T371;

public class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = (a & b) << 1;  // 进位
            a = a ^ b;  // 除去进位位置，其余位置上的数
            b = tmp;
        }
        return a;
    }

    // 显示的还是十进制
    public int getBinary(int a) {
        int ans = 0;
        int i = 0;
        while (a != 0) {
            ans += (a % 2) << i;
            a /= 2;
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solut = new Solution();
        System.out.println(solut.getSum(1, 2));
        System.out.println(solut.getBinary(10));
    }
}
