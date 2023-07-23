package BackTrace.Arrangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 字符串的全排列
/*
* 输入一个字符串，打印出该字符串中字符的所有排列。
* 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
示例:
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
* */

// 这与Permutations_II一样的思路，完全照搬。记得先对字符串的每个字符进行排序。
public class Permutations_String {
    public List<String> permuteStr(String str) {
        char[] chrs = str.toCharArray();
        // 排序
        Arrays.sort(chrs);
        for (char ch : chrs) {
            System.out.print(ch + " ");
        }
        System.out.println();

        List<String> ans = new ArrayList<>();
        boolean[] used = new boolean[chrs.length];
        backTrace(chrs, used, ans, new ArrayList<>());
        return ans;
    }

    public void backTrace(char[] chrs, boolean[] used, List<String> ans, List<Character> tmp) {
        if (tmp.size() == chrs.length) {
            StringBuffer sb = new StringBuffer();
            for (char ch : tmp) {
                sb.append(ch);
            }
            ans.add(new String(sb));
            return;
        }
        for (int i = 0; i < chrs.length; i++) {
            if (!used[i]) {
                // 判重剪枝
                if (i > 0 && chrs[i] == chrs[i - 1] && !used[i - 1]) {
                    // !used[i-1]表示相同字符，从左往右数第一个没有使用到，
                    // 既然你前面一个相同字符都没使用到，哪轮得到你。
                    continue;
                }
                // 选取
                tmp.add(chrs[i]);
                used[i] = true;
                backTrace(chrs, used, ans, tmp);
                used[i] = false;
                // 撤回
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String str = "cbca";

        Permutations_String pS = new Permutations_String();
        System.out.println(pS.permuteStr(str));
    }
}


/*
 * 再次总结：“排列”类型问题和“子集、组合”问题不同在于：
 *
 * “排列”问题使用used数组来标识选择列表，
 * 而“子集、组合”问题则使用start参数。
 * 另外还需注意两种问题的判重剪枝！！
 * */
