package T38;

public class Solution {
    public String countAndSay(int n) {
        String[] strs = new String[n + 1];
        strs[0] = "0";
        strs[1] = "1";

        if (n < 2) {
            return strs[n];
        }
        for (int i = 2; i <= n; i++) {
            String str = strs[i - 1];
            StringBuffer sb = new StringBuffer();
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) == str.charAt(j - 1)) {
                    count++;
                } else {
                    // 把上一个个数和数字插进来
                    sb.append(count);
                    sb.append(str.charAt(j - 1));
                    count = 1;
                }
            }
            // 结束后把当前个数和数字插进来
            sb.append(count);
            sb.append(str.charAt(str.length() - 1));
            strs[i] = sb.toString();
        }

        return strs[n];
    }

    public static void main(String[] args) {
        int n = 6;

        Solution solut = new Solution();
        System.out.println(solut.countAndSay(n));
    }
}
