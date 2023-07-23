package T190;

public class Solution {
    // 超时
    public int reverseBits(int n) {
        int ans = 0, power = 31;
        while (n != 0) {
            ans |= ((n & 1) << power);  // 右边第一位的数放到31位上，第二位放到30位上，...
            n = (n >> 1);
            power--;
        }
        return ans;
    }

    //
    public int reverseBits3(int n) {
        int ans = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            ans |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return ans;
    }

    public int reverseBits2(int n) {
        // >> 带符号右移，>>> 无符号右移
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;

        /*
        * 其中
        * 0x55555555;  // 01010101010101010101010101010101
        * 0x33333333;  // 00110011001100110011001100110011
        * 0x0f0f0f0f;  // 00001111000011110000111100001111
        * 0x00ff00ff;  // 00000000111111110000000011111111
        * */
    }

    public static void main(String[] args) {
        int n = 0b00000010100101000001111010011100;
        int n2 = 0b11111111111111111111111111111101;

        Solution solut = new Solution();
        System.out.println(solut.reverseBits(n));
        System.out.println(solut.reverseBits2(n));
        System.out.println(solut.reverseBits3(n));
    }
}
