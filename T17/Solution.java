package T17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 回溯法：全排列问题【不管重复的情况】，那就是Permutation_I问题
public class Solution {
    private Map<Character, List<String>> hashMap;

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        hashMap = new HashMap<>();
        hashMap.put('2', new ArrayList<>());
        hashMap.get('2').add("a");
        hashMap.get('2').add("b");
        hashMap.get('2').add("c");
        hashMap.put('3', new ArrayList<>());
        hashMap.get('3').add("d");
        hashMap.get('3').add("e");
        hashMap.get('3').add("f");
        hashMap.put('4', new ArrayList<>());
        hashMap.get('4').add("g");
        hashMap.get('4').add("h");
        hashMap.get('4').add("i");
        hashMap.put('5', new ArrayList<>());
        hashMap.get('5').add("j");
        hashMap.get('5').add("k");
        hashMap.get('5').add("l");
        hashMap.put('6', new ArrayList<>());
        hashMap.get('6').add("m");
        hashMap.get('6').add("n");
        hashMap.get('6').add("o");
        hashMap.put('7', new ArrayList<>());
        hashMap.get('7').add("p");
        hashMap.get('7').add("q");
        hashMap.get('7').add("r");
        hashMap.get('7').add("s");
        hashMap.put('8', new ArrayList<>());
        hashMap.get('8').add("t");
        hashMap.get('8').add("u");
        hashMap.get('8').add("v");
        hashMap.put('9', new ArrayList<>());
        hashMap.get('9').add("w");
        hashMap.get('9').add("x");
        hashMap.get('9').add("y");
        hashMap.get('9').add("z");

        backTrace(0, digits, ans, new ArrayList<>());
        return ans;
    }

    public void backTrace(int level, String digits, List<String> ans, List<String> tmp) {
        if (level + 1 > digits.length()) {
            StringBuffer sb = new StringBuffer();
            for (String s : tmp) {
                sb.append(s);
            }
            ans.add(sb.toString());
            return;
        }

        List<String> table = hashMap.get(digits.charAt(level));
        for (int i = 0; i < table.size(); i++) {
            // 选取
            tmp.add(table.get(i));
            // 进入下一层
            backTrace(level + 1, digits, ans, tmp);
            // 撤销
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "2233";
        String digits2 = "";
        String digits3 = "9";

        Solution solut = new Solution();
        System.out.println(solut.letterCombinations(digits2));
    }
}
