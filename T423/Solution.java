package T423;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String originalDigits(String s) {
        /*
         * zero  'z'
         * two  'w'
         * four  'u'
         * six  'x'
         * eight  'g'
         * one  'o' -> zero, two, four
         * three 'h' -> eight
         * five  'f' -> four
         * seven  's' -> six
         * nine  'n' -> one, seven
         * */
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hashMap.put(s.charAt(i), hashMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        // System.out.println(hashMap);

        int[] nums = new int[10];
        nums[0] = hashMap.getOrDefault('z', 0);
        nums[2] = hashMap.getOrDefault('w', 0);
        nums[4] = hashMap.getOrDefault('u', 0);
        nums[6] = hashMap.getOrDefault('x', 0);
        nums[8] = hashMap.getOrDefault('g', 0);

        StringBuffer sb = new StringBuffer();
        // 从0到9开始试
        if (hashMap.get('o') != null) {
            nums[1] = hashMap.get('o') - nums[0] - nums[2] - nums[4];
        }
        if (hashMap.get('h') != null) {
            nums[3] = hashMap.get('h') - nums[8];
        }
        if (hashMap.get('f') != null) {
            nums[5] = hashMap.get('f') - nums[4];
        }
        if (hashMap.get('s') != null) {
            nums[7] = hashMap.get('s') - nums[6];
        }
        // 9这里有个小细节，不要用n计算，否则"nnei"这种字符串容易计算错误。
        // 推荐用'i'计算9，因为'n'在9中出现2次，'e'在3中出现2次，而'i'只出现一次
        if (hashMap.get('i') != null) {
            nums[9] = hashMap.get('i') - nums[5] - nums[6] - nums[8];
        }

        for (int i = 0; i < 10; i++) {
            for (int a = 0; a < nums[i]; a++) {
                sb.append(i);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "owoztneoer";  // 012
        String s2 = "fviefuro";  // 45
        String s3 = "neo";  // 1
        String s4 = "nnei";  // 9
        String s5 = "zerozero";  // 00

        Solution solut = new Solution();
        System.out.println(solut.originalDigits(s5));
    }
}
