package T1178;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 两个条件：1.包含puzzle[i]的首字母 2.words[i]的所有字母都可以在puzzles[i]中找到
public class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        // 先状态压缩
        Map<Integer, Integer> frequency = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                // 这里顺便把上一个二进制数字与当前二进制数字相加了
                mask |= (1 << (ch - 'a'));
            }
            // 二进制数mask，计算其中1的个数，并判断是否小于7
            // 这是因为每个位上1表示一个字母，因为puzzle[i]是由7个不同字母组成的，
            // 如果超过7个1了，说明有8个不同的字母，肯定在puzzle[i]中找不到，因此可以直接舍去
            if (Integer.bitCount(mask) <= 7) {
                // mask是26位的，而Integer的最大位数有31位，因此可以用Integer来存储
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        // 再匹配
        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0;

            // 枚举子集
            int mask = 0;
            // puzzle[1: N-1]
            for (int i = 1; i < 7; i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;  // 这里的subset表示后6位组成的最大子集
            do {
                // 这里相当于把puzzle[0]和puzzle[1: N-1]转换成二进制相加了
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask;  // 这里相当于往前遍历
            } while (subset != mask);  // 这里是枚举后6位组成的不同子集
            ans.add(total);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        Solution solut = new Solution();
        System.out.println(solut.findNumOfValidWords(words, puzzles));
    }
}
