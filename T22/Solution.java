package T22;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<String> ans;
    String[] s;
    int len;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        s = new String[2];
        s[0] = "(";
        s[1] = ")";
        len = n * 2;

        backTrack(s, len, "");
        return ans;
    }

    public boolean isValid(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else if (str.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }

    public void backTrack(String[] s, int len, String str) {
        if (str.length() == len) {
            if (isValid(str)) {
                ans.add(str);
            }
            return;
        }

        // 两个选项："(", ")"
        for (int i = 0; i < 2; i++) {
            // 剪枝
            if (i > 0 && s[i].equals(s[i - 1])) {
                // 这一次选择的字符串与上一次相同，比如都是"("，或者都是")"
                continue;
            }
            str = str + s[i];
            backTrack(s, len, str);
            str = str.substring(0, str.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 2;

        Solution solut = new Solution();
        System.out.println(solut.generateParenthesis(n));
    }
}
