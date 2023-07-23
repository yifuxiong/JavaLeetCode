package T28;

public class Solution {
    // 暴力解法
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            if (needle.charAt(0) == haystack.charAt(i)) {
                int j = 0;
                for (j = 0; j < needle.length(); j++) {
                    if (i + j < haystack.length() && needle.charAt(j) != haystack.charAt(i + j)) {
                        break;
                    } else if (i + j >= haystack.length()) {
                        return -1;
                    }
                }
                if (j == needle.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 双指针
    public int strStr2(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.equals("")) {
            return 0;
        }

        int left = 0, right = 0, index = 0;
        while (right < haystack.length() && index < needle.length()) {
            if (haystack.charAt(right) != needle.charAt(index)) {
                left++;
                right = left;
                index = 0;
            } else {
                right++;
                index++;
            }
        }
        return index == needle.length() ? left : -1;
    }

    // KMP

    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        String haystack2 = "aaaaa", needle2 = "bba";
        String haystack3 = "", needle3 = "";
        String haystack4 = "mississippi", needle4 = "issipi";

        Solution solut = new Solution();
        System.out.println(solut.strStr(haystack4, needle4));
        System.out.println(solut.strStr2(haystack4, needle4));
    }
}
