package T3;

import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxSize = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            // 移除最左端的字符
            while (hashMap.containsKey(s.charAt(i))) {
                hashMap.remove(s.charAt(i - hashMap.size()));
            }
            hashMap.put(s.charAt(i), 1);
            maxSize = Math.max(maxSize, hashMap.size());
        }
        return maxSize;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        String s5 = "aab";
        String s6 = "qrsvbspk";

        Solution solut = new Solution();
        System.out.println(solut.lengthOfLongestSubstring(s));
    }
}
