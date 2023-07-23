package T3;

import java.util.*;

public class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        } else if (n < 2) {
            return 1;
        }

        int maxLen = 1;
        Map<Character, Integer> hashMap = new HashMap<>();

        for(int i = 0; i < n; i++){
            while (hashMap.containsKey(s.charAt(i))){
                // 每次只移出最左端的字符即可
                hashMap.remove(s.charAt(i - hashMap.size()));
            }
            // 不管有没有重复，都要put进去
            hashMap.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, hashMap.size());
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        String s5 = "aab";
        String s6 = "qrsvbspk";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.lengthOfLongestSubstring(s5));
    }
}
