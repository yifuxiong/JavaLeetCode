package T290;

import java.util.HashMap;

public class Solution {
    /**
     * 左边遍历字符，右边遍历字符串（以空格为单位）
     */
    public boolean wordPattern(String pattern, String s) {
        String[] str = s.split(" ");

        // 如果长度不等，直接返回false
        if (pattern.length() != str.length)
            return false;

        HashMap<Character, String> hashMap = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++){
            if (!hashMap.containsKey(pattern.charAt(i))){
                // 如果哈希表中不包含这个key，但是包含这个value了，说明key和value不是一一对应
                if (hashMap.containsValue(str[i])){
                    return false;
                }
                // 否则是一一对应，就添加到哈希表中
                hashMap.put(pattern.charAt(i), str[i]);
            } else{
                if (!hashMap.get(pattern.charAt(i)).equals(str[i])){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";

        String pattern2 = "abba";
        String str2 = "dog cat cat fish";

        String pattern3 = "aaaa";
        String str3 = "dog cat cat dog";

        String pattern4 = "abba";
        String str4 = "dog dog dog dog";

        Solution solut = new Solution();
        System.out.println(solut.wordPattern(pattern, str));
    }
}

