package T9;

public class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        System.out.println(s);

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int x = 121;
        int x2 = -121;
        int x3 = 10;

        Solution solut = new Solution();
        System.out.println(solut.isPalindrome(x3));
    }
}
