package T1734;

public class Solution {
    // 我的方法：结果验证没错，但是不符合条件（整数数组 perm ，它是前n个正整数的排列）
    public int[] decode(int[] encoded) {
        int n = encoded.length;
        int[] perm = new int[n + 1];

        perm[n / 2] = encoded[n / 2 - 1] ^ encoded[n / 2];

        // 后半部分
        for (int i = n / 2 + 1; i < n + 1; i++) {
            perm[i] = perm[i - 1] ^ encoded[i - 1];
        }

        // 前半部分
        for (int i = n / 2 - 1; i >= 0; i--) {
            perm[i] = perm[i + 1] ^ encoded[i];
        }

        return perm;
    }

    public static void main(String[] args) {
        int[] encoded = {7, 5, 6, 11, 14, 15, 11, 6};

        Solution solut = new Solution();
        int[] perm = solut.decode(encoded);

        for (int i = 0; i < perm.length; i++) {
            System.out.print(perm[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < perm.length - 1; i++) {
            encoded[i] = perm[i] ^ perm[i + 1];
        }

        for (int i = 0; i < encoded.length; i++) {
            System.out.print(encoded[i] + " ");
        }
        System.out.println();
    }
}
