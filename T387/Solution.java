package T387;

import java.util.HashMap;

public class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), 1);
            } else {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            }
        }
        System.out.println(hashMap);

        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 数组计数，比哈希表快
    public int firstUniqChar2(String s) {
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++){
            if (arr[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String s2 = "loveleetcode";

        Solution solut = new Solution();
        System.out.println(solut.firstUniqChar(s2));
        System.out.println(solut.firstUniqChar2(s2));
    }
}
