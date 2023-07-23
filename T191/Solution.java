package T191;

public class Solution {
    public int hammingWeight(int n) {
        int ans = 0;

        int count = 32;
        int mask = 1;  // 用掩码来做
        while (count > 0) {
            if ((n & mask) != 0) {
                ans++;
            }
            mask = (mask << 1);
            count--;
        }
        return ans;
    }

    // 自己写一遍
    public int hammingWeight2(int n) {
        int ret = 0;
        int mask = 1;

        int count = 32;
        while (count > 0) {
            if ((n & mask) != 0) {
                ret++;
            }
            mask = mask << 1;
            count--;
        }
        return ret;
    }

    public static void main(String[] args) {
        // 二进制表示法，开头加上0b
        int n = 0b00000000000000000000000000001011;
        int n2 = 0b00000000000000000000000010000000;
        int n3 = 0b11111111111111111111111111111101;

        Solution solut = new Solution();
        System.out.println(solut.hammingWeight(n));
        System.out.println(solut.hammingWeight2(n));
    }
}
