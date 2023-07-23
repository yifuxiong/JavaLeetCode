package T115;

// 方法一：递归+减枝
// https://leetcode-cn.com/problems/distinct-subsequences/solution/di-gui-jian-zhi-dptong-su-yi-dong-by-mei-ap79/
public class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return 0;
        }

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        return numDistinctHelp(0, 0, sc, tc);
    }

    /*
     * @param i s串的起始下标
     * @param j t串的起始下标
     * @param sc
     * @param tc
     * @return 以s串i为起点,t串j为起点能匹配的次数
     * */
    public int numDistinctHelp(int i, int j, char[] sc, char[] tc) {
        int n = sc.length;
        int m = tc.length;

        //  剪枝条件：当t串的长度(m - j)大于s串的长度(n - i)的时候，一定匹配不出来
        if (m - j > n - i) {
            return 0;
        }
        // j >= m表示匹配完成整个t，因此返回1
        if (j >= m) {
            return 1;
        }

        // 如果i字符和j字符相同，那么可以接着下一个字符来进行匹配
        // 或者不选择当前i字符，从i+1字符位置再匹配j
        if (sc[i] == tc[j]) {
            return numDistinctHelp(i + 1, j + 1, sc, tc) + numDistinctHelp(i + 1, j, sc, tc);
        } else {
            // 不相等，那么直接从i+1个字符匹配j字符
            return numDistinctHelp(i + 1, j, sc, tc);
        }
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";

        String s2 = "babgbag";
        String t2 = "bag";

        Solution solut = new Solution();
        System.out.println(solut.numDistinct(s2, t2));
    }
}
