package T1734;

// 别人的思路：
// https://leetcode-cn.com/problems/decode-xored-permutation/solution/gong-shui-san-xie-note-bie-pian-li-yong-zeh6o/
public class Solution2 {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] ans = new int[n];

        // 求得除了ans[n-1]的所有异或结果
        int a = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }

        // 求得ans的所有异或结果
        int b = 0;
        for (int i = 1; i <= n; i++) {
            // ans数组是前n个正整数的排列
            b ^= i;
        }

        // 求得ans[n-1]后，从后往前做
        ans[n - 1] = a ^ b;
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = encoded[i] ^ ans[i + 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] encoded = {7, 5, 6, 11, 14, 15, 11, 6};

        Solution2 solut2 = new Solution2();
        int[] perm = solut2.decode(encoded);

        for (int i = 0; i < perm.length; i++) {
            System.out.print(perm[i] + " ");
        }
        System.out.println();
    }
}
