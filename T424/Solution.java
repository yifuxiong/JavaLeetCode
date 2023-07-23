package T424;

public class Solution {
    private int[] map = new int[26];

    public int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int left = 0, right = 0;
        int historyCharMax = 0;

        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);

            // 如果当前字符串中的出现次数最多的字母个数+K大于串长度，那么这个串就是满足条件的
            // 如果historyCharMax+K没有超过窗口长度，那么滑动窗口移动
            if (right - left + 1 > historyCharMax + k) {
                // 窗口左侧第一个字母的最大串长度--
                map[chars[left] - 'A']--;
                // 窗口右移
                left++;
                // 这里不需要right++，因为right在for循环中，会自动++
            }
        }

        return chars.length - left;
    }

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;

        Solution solut = new Solution();
        System.out.println(solut.characterReplacement(s, k));
    }
}
