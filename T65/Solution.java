package T65;

public class Solution {
    public boolean isNumber(String s) {
        // '.'最多只能出现一次
        int count = 0;
        // 'e'最多只能出现一次
        int counte = 0;
        // 'e'的后面不能出现小数
        boolean flag = true;

        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            // 首先判断符号位
            if (chs[i] == '+' || chs[i] == '-') {
                // 单单只是符号位就必须在首位 or 【1e+1】这种形式的符号位
                if (i == 0 || (chs[i - 1] == 'e' || chs[i - 1] == 'E')) {
                    continue;
                } else {
                    return false;
                }
            } else if (chs[i] == 'e' || chs[i] == 'E') {
                // 'e'的后面不能出现小数
                flag = false;

                // 'e'的个数最多只能有1个
                counte++;
                if (counte > 1){
                    return false;
                }

                // 判断 'e': 前面的一个字符是数字
                if (i > 0 && (chs[i - 1] - '0' >= 0 && chs[i - 1] - '0' <= 9) || (i > 1 && chs[i - 1] == '.' && chs[i - 2] - '0' > 0 && chs[i - 2] - '0' <= 9)) {
                    // 或者e的前面是一个'.'，'.'的前面有数字且不为0
                    if (i < chs.length - 2 && (chs[i + 1] == '+' || chs[i + 1] == '-') && (chs[i + 2] - '0' >= 0 && chs[i + 2] - '0' <= 9)) {
                        // 并且后面的一个字符是符号，符号位后面紧跟数字
                        continue;
                    } else if (i < chs.length - 1 && (chs[i + 1] - '0') >= 0 && (chs[i + 1] - '0') <= 9) {
                        // 并且后面的一个字符是数字
                        continue;
                    } else {
                        return false;
                    }
                }else {
                    return false;
                }
            } else if (chs[i] == '.') {
                if (flag == false){
                    return false;
                }
                // 先加1
                count++;
                // 再判断 '.': 并且'.'的个数只能有1个
                if (count > 1) {
                    return false;
                }
                if ((i > 0 && chs[i - 1] - '0' >= 0 && chs[i - 1] - '0' <= 9) || (i < chs.length - 1 && chs[i + 1] - '0' >= 0 && chs[i + 1] - '0' <= 9)) {
                    continue;
                } else {
                    return false;
                }
            } else if ((chs[i] - '0') >= 0 && (chs[i] - '0') <= 9) {
                // 如果不是前3种字符，那么就判断是否是数字
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "0";
        String s2 = "e";
        String s3 = ".";
        String s4 = ".1";

        String s5 = "0e+1";
        String s6 = "0e";
        String s7 = "-.9";
        String s8 = "inf";
        String s9 = ".1.";
        String s10 = "3.5e+3.5e+3.5";
        String s11 = "3.5e+3.5";
        String s12 = "4e+";
        String s13 = "46.e3";
        String s14 = "0.e";
        String s15 = "3e6.5";

        Solution solut = new Solution();
        System.out.println(solut.isNumber(s15));

    }
}
