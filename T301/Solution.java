package T301;

import java.util.ArrayList;
import java.util.List;

// 回溯法 + 剪枝
public class Solution {
    List<String> ans;

    public List<String> removeInvalidParentheses(String s) {
        ans = new ArrayList<>();

        // 得到需要去除的左右括号数量
        int lremove = 0, rremove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lremove++;
            } else if (s.charAt(i) == ')') {
                if (lremove > 0) {
                    lremove--;
                } else {
                    rremove++;
                }
            }
        }
        backTrack(s, 0, 0, 0, lremove, rremove);
        return ans;
    }

    // 回溯 + 剪枝
    public void backTrack(String str, int start, int lcount, int rcount, int lremove, int rremove) {
        if (lremove == 0 && rremove == 0) {
            if (isValid(str)) {
                ans.add(str);
            }
            return;
        }

        for (int i = start; i < str.length(); i++) {
            if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                // 字符相同属于重复情况
                if (str.charAt(i) == '(') lcount++;
                else if (str.charAt(i) == ')') rcount++;
                continue;
            }
            if (lremove + rremove > str.length() - i) {
                // 剩下要去除的字符数量大于剩下的字符串长度
                return;
            }

            if (lremove > 0 && str.charAt(i) == '(') {
                // lremove减1，并且去掉这个字符'('
                backTrack(str.substring(0, i) + str.substring(i + 1), i, lcount, rcount, lremove - 1, rremove);
            }
            if (rremove > 0 && str.charAt(i) == ')') {
                // rremove减1，并且去掉这个字符'('
                backTrack(str.substring(0, i) + str.substring(i + 1), i, lcount, rcount, lremove, rremove - 1);
            }

            // lremove和rremove为0之后，继续后续的括号计数
            if (str.charAt(i) == '(') {
                lcount++;  // 后续左括号数量加1
            } else if (str.charAt(i) == ')') {
                rcount++;  // 后续右括号数量加1
            }
            if (rcount > lcount) {
                // 一旦出现右括号数量大于左括号数量
                // 说明有一种右括号在左括号前面的非法情况
                break;
            }
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

        Solution solut = new Solution();
        System.out.println(solut.removeInvalidParentheses(s4));
    }
}
