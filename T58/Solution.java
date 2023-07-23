package T58;

public class Solution {
    public int lengthOfLastWord(String s) {
        int len = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n && isL(s.charAt(i + 1)) && s.charAt(i) == ' ') {
                break;
            }
            if (isL(s.charAt(i))) {
                len++;
            }
        }
        return len;
    }

    public boolean isL(char ch) {
        if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "Hello World";
        String s2 = "   fly me   to   the moon  ";
        String s3 = "luffy is still joyboy";

        Solution solut = new Solution();
        System.out.println(solut.lengthOfLastWord(s3));
    }
}
