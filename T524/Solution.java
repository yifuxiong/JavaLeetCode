package T524;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        // 先对字典排序，按照字符串的长度从大到小排列
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        // System.out.println(dictionary);

        // 双指针
        for (String d : dictionary) {
            int dind = 0, sind = 0;
            while (dind < d.length() && sind < s.length()) {
                if (d.charAt(dind) == s.charAt(sind)) {
                    dind++;
                }
                sind++;
            }
            if (dind == d.length()) {
                return d;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "abpcplea";

        List<String> dictionary = new ArrayList<>();
        dictionary.add("ale");
        dictionary.add("apple");
        dictionary.add("monkey");
        dictionary.add("plea");

        Solution solut = new Solution();
        System.out.println(solut.findLongestWord(s, dictionary));
    }
}
