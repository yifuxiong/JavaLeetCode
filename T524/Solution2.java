package T524;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    // 官方：方法三：动态规划，这个就是我想要的答案
    public String findLongestWord(String s, List<String> dictionary) {
        int m = s.length();
        int[][] f = new int[m + 1][26];
        // 边界初始化
        Arrays.fill(f[m], m);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i) == (char) ('a' + j)) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }

        String res = "";
        for (String t : dictionary) {
            boolean match = true;
            int j = 0;
            for (int i = 0; i < t.length(); i++) {
                if (f[j][t.charAt(i) - 'a'] == m) {
                    // m表示已经超出边界了，即不匹配
                    match = false;
                    break;
                }
                j = f[j][t.charAt(i) - 'a'] + 1;  // 从后一个位置开始计数
            }
            if (match) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abpcplea";

        List<String> dictionary = new ArrayList<>();
        dictionary.add("ale");
        dictionary.add("apple");
        dictionary.add("monkey");
        dictionary.add("plea");

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.findLongestWord(s, dictionary));
    }
}
