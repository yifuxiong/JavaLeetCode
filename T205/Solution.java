package T205;

import java.util.HashMap;

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // 首先哈希表不包含这个key
            if (!hashMap.containsKey(s.charAt(i))) {
                // 但是哈希表却包含这个value，则返回false
                if (hashMap.containsValue(t.charAt(i))) {
                    return false;
                }
                hashMap.put(s.charAt(i), t.charAt(i));
            } else {
                if (hashMap.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "egg", t = "add";
        String s2 = "foo", t2 = "bar";
        String s3 = "paper", t3 = "title";
        String s4 = "ab", t4 = "aa";

        Solution solut = new Solution();
        System.out.println(solut.isIsomorphic(s4, t4));
    }
}
