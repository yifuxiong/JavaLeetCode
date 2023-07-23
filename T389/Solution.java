package T389;

import java.util.HashMap;

public class Solution {
    // 哈希表
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == null) {
                hashMap.put(s.charAt(i), 1);
            } else {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            }
        }
        System.out.println(hashMap);

        for (int i = 0; i < t.length(); i++) {
            // 如果当前这个字母不存在，或者这个字母重复遍历，就返回
            if (hashMap.get(t.charAt(i)) == null || hashMap.get(t.charAt(i)) == 0) {
                return t.charAt(i);
            } else {
                hashMap.put(t.charAt(i), hashMap.get(t.charAt(i)) - 1);
            }
        }
        return ' ';
    }

    // 改进：位运算。如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。
    // https://leetcode-cn.com/problems/find-the-difference/solution/hua-jie-suan-fa-389-zhao-bu-tong-by-guanpengchn/
    public char findTheDifference2(String s, String t) {
        // 这题有个隐藏条件，字符串t的长度长于字符串s
        char ans = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            /**
             * 一个数和0做XOR运算等于本身：a⊕0 = a
             * 一个数和其本身做XOR运算等于 0：a⊕a = 0
             * XOR 运算满足交换律和结合律：a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
             *
             * 所以，这里只要和自己做异或，结果为0
             * 而0和其他数做异或，结果是那个数
             *
             * 因此，只要有两个相同的数，结果为0
             * 最后结果变成0⊕0⊕0⊕...⊕0⊕某个数，这个数就是落单的那个数
             */
            ans ^= s.charAt(i);
            ans ^= t.charAt(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";

        String s2 = "";
        String t2 = "y";

        String s3 = "a";
        String t3 = "aa";

        String s4 = "ae";
        String t4 = "aea";

        Solution solut = new Solution();
        System.out.println(solut.findTheDifference(s4, t4));
        System.out.println(solut.findTheDifference2(s4, t4));
    }
}
