package T91;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    // 回溯法超时
    public int numDecodings(String s) {
        List<List<String>> ans = new LinkedList<>();
        char[] arr = s.toCharArray();
        backTrace(0, s, arr, ans, new ArrayList<>());

        System.out.println(ans);

        return ans.size();
    }

    public void backTrace(int start, String s, char[] arr, List<List<String>> ans, ArrayList<String> tmp) {
        // 如果每段拼接起来仍然是一个整个，添加tmp
        if (catStr(tmp).equals(s)) {
            ans.add(new ArrayList<>(tmp));
        }

        for (int i = start; i < arr.length; i++) {
            String catS = new String(arr, start, i + 1 - start);
            // 剪枝
            if (catS.charAt(0) == '0' || catS.length() > 2) {
                continue;
            }
            if (Integer.parseInt(catS) > 26 || Integer.parseInt(catS) < 1){
                continue;
            }
            tmp.add(catS);
            backTrace(i + 1, s, arr, ans, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 将tmp中的每段字符串拼接
    public String catStr(ArrayList<String> tmp) {
        StringBuffer sb = new StringBuffer();
        for (String s : tmp) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "12";
        String s2 = "226";
        String s3 = "0";
        String s4 = "06";
        String s5 = "2611055971756562";
        String s6 = "111111111111111111111111111111111111111111111";

        Solution solut = new Solution();
        System.out.println(solut.numDecodings(s5));
    }
}
