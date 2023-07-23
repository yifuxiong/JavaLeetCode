package T301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// BSF 广度优先遍历
public class Solution2 {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> currSet = new HashSet<>();
        currSet.add(s);
        while (true) {
            // System.out.println(currSet);
            for (String str : currSet) {
                if (isValid(str)) {
                    ans.add(str);
                }
            }
            if (ans.size() > 0) {
                return ans;
            }

            Set<String> nextSet = new HashSet<>();
            for (String str : currSet) {
                for (int i = 0; i < str.length(); i++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        // 去重
                        continue;
                    }
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            currSet = nextSet;
        }
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

    public static void main(String[] args) {
        String s = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        String s4 = "(r(()()(";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.removeInvalidParentheses(s4));
    }
}
