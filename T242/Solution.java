package T242;

import java.util.Arrays;

public class Solution {
    public boolean isAnagram(String s, String t) {
        char[] char_s = s.toCharArray();
        char[] char_t = t.toCharArray();

        if (char_s.length != char_t.length)
            return false;

        Arrays.sort(char_s);
        Arrays.sort(char_t);

        if ((new String(char_s)).equals(new String(char_t)))
            return true;
        else return false;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        String s2 = "rat";
        String t2 = "car";

        String s3 = "a";
        String t3 = "b";

        Solution solut = new Solution();
        System.out.println(solut.isAnagram(s3, t3));
    }
}
