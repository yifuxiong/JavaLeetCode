package T132;

import java.util.ArrayList;
import java.util.List;

// T131题的回溯法解这题，发现超时了
public class Solution {
    protected int num;

    public int minCut(String s) {
        int n = s.length();
        if (n == 0 || n == 1) {
            return 0;
        }
        this.num = n - 1;
        List<List<String>> ret = new ArrayList<>();
        char[] charArray = s.toCharArray();
        backTrace(0, charArray, ret, new ArrayList<>());
        // System.out.println(ret);
        for (List<String> list: ret){
            this.num = Math.min(this.num, list.size() - 1);
        }
        return this.num;
    }

    private void backTrace(int start, char[] charArray, List<List<String>> ret, ArrayList<String> tmp) {
        if (listString(tmp).equals(catString(charArray))) {
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
        String s2 = "ababababababababababababcbabababababababababababa";

        Solution solut = new Solution();
        System.out.println(solut.minCut(s));
    }
}
