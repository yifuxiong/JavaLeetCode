package T830;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ret = new ArrayList<>();

        int start = 0, end = 0;
        while (end < s.length()) {
            // 换字符了
            if (s.charAt(start) != s.charAt(end)) {
                int len = end - start;
                if (len >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end - 1);
                    ret.add(list);
                }
                start = end;
            }
            end++;
        }
        if (end != start && end - start >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end - 1);
            ret.add(list);
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "abbxxxxzzy";
        String s2 = "abc";
        String s3 = "abcdddeeeeaabbbcd";
        String s4 = "aaabb";

        Solution solut = new Solution();
        System.out.println(solut.largeGroupPositions(s4));
    }
}
