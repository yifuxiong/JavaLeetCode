package T8;

public class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        boolean flag = true;
        long ans = 0;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 如果有符号位，那么符号位必须是首位
            if (i == 0 && s.charAt(i) == '+') {
                continue;
            } else if (i == 0 && s.charAt(i) == '-') {
                flag = false;
            } else if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                if (flag && (ans * 10 + s.charAt(i) - '0') > 2147483647) {
                    return 2147483647;
                } else if (!flag && -(ans * 10 + s.charAt(i) - '0') < -2147483648) {
                    return -2147483648;
                } else {
                    ans = ans * 10 + s.charAt(i) - '0';
                }
            } else {
                break;
            }
        }
        if (ans != 0) {
            if (!flag) {
                return -(int) ans;
            } else {
                return (int) ans;
            }
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String s = "  -4193 with words";
        String s2 = "words and 987";
        String s3 = "-91283472332";
        String s4 = "        ";
        String s5 = "  fsgweg  gggd  fa ";
        String s6 = "- 0  123";
        String s7 = "-+12 3  ";
        String s8 = "0000-12+3423";

        Solution solut = new Solution();
        System.out.println(solut.myAtoi(s6));
    }
}
