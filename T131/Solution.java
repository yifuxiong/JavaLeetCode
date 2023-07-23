package T131;

import java.util.*;

public class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> ret = new LinkedList<>();
        if (n == 0) {
            return ret;
        }
        char[] charArray = s.toCharArray();
        backTrace(0, charArray, ret, new ArrayList<>());
        return ret;
    }

    private void backTrace(int start, char[] charArray, List<List<String>> ret, ArrayList<String> tmp) {
        if (listString(tmp).equals(catString(charArray))) {
            // 终止条件，将结果tmp加进ret中
            ret.add(new ArrayList<>(tmp));
        }
        for (int j = start; j < charArray.length; j++) {
            if (!isHuiwen(charArray, start, j)) {
                continue;
            }
            tmp.add(new String(charArray, start, j + 1 - start));
            backTrace(j + 1, charArray, ret, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean isHuiwen(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private String listString(ArrayList<String> tmp) {
        StringBuffer sb = new StringBuffer();
        for (String t : tmp) {
            sb.append(t);
        }
        return sb.toString();
    }

    private String catString(char[] charArray) {
        StringBuffer sb = new StringBuffer();
        for (char c : charArray) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aab";
        String s2 = "";

        Solution solut = new Solution();
        System.out.println(solut.partition(s));
    }
}
