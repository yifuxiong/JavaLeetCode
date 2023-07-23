package T420;

public class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (int i = 0; i < n; i++) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = 1;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = 1;
            } else if (Character.isDigit(ch)) {
                hasDigit = 1;
            }
        }
        int categories = hasLower + hasUpper + hasDigit;

        if (n < 6) {  // 字符串长度小于6
            return Math.max(6 - n, 3 - categories);
        } else if (n <= 20) {  // 字符串长度大于等于6，小于等于20
            // 替换次数
            int replace = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; i++) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    cnt++;
                } else {
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            replace += cnt / 3;
            return Math.max(replace, 3 - categories);
        } else {  // 字符串长度大于20
            // 替换次数和删除次数
            int replace = 0, remove = n - 20;
            // k mod 3 == 1的组数，即删除2个字符可以减少1次替换操作
            int rm2 = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; i++) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    cnt++;
                } else {
                    if (remove > 0 && cnt >= 3) {
                        if (cnt % 3 == 0) {
                            // 如果是k % 3 == 0的组，那么优先删除1个字符，减少1次替换操作
                            --remove;
                            --replace;
                        } else if (cnt % 3 == 1) {
                            // 如果是 k % 3 == 1的组，那么存下来备用
                            ++rm2;
                        }
                        // k % 3 == 2的组无需显示考虑
                    }
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            if (remove > 0 && cnt >= 3) {
                if (cnt % 3 == 0) {
                    --remove;
                    --replace;
                } else if (cnt % 3 == 1) {
                    ++rm2;
                }
            }
            replace += cnt / 3;

            // 使用 k % 3 == 1的组的数量，由剩余的替换次数、数组和剩余的删除次数共同决定
            int use2 = Math.min(Math.min(replace, rm2), remove / 2);
            replace -= use2;
            remove -= use2 * 2;

            // 由于每有一次替换次数就一定有3个连续相同的字符(k/3决定)，
            // 因此这里可以直接计算出使用 k % 3 == 2的组的数量
            int use3 = Math.min(replace, remove / 3);
            replace -= use3;
            remove -= use3 * 3;
            return (n - 20) + Math.max(replace, 3 - categories);
        }
    }
}
