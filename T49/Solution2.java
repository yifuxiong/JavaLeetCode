package T49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<Integer>> hashMap = new HashMap<>();

        int n = strs.length;
        for (int i = 0; i < n; i++) {
            String str = getStr(strs[i]);
            if (!hashMap.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                hashMap.put(str, list);
            } else {
                List<Integer> list = hashMap.get(str);
                list.add(i);
                hashMap.put(str, list);
            }
        }
        System.out.println(hashMap);

        for (String s : hashMap.keySet()) {
            List<Integer> list = hashMap.get(s);
            List<String> a = new ArrayList<>();
            for (int i : list) {
                a.add(strs[i]);
            }
            ans.add(a);
        }
        return ans;
    }

    public String getStr(String str) {
        int[] chars = new int[26];
        for (int j = 0; j < str.length(); j++) {
            chars[str.charAt(j) - 'a'] += 1;
        }

        // 找到问题了
        for (int i = 0; i < 26; i++) {
            System.out.print(chars[i] + " ");
        }
        System.out.println();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // strs2是个特殊例子，运行一遍发现两者的key字符串的形式相同
        String[] strs2 = {"bdddddddddd", "bbbbbbbbbbc"};

        Solution2 solut2 = new Solution2();
        List<List<String>> ret = solut2.groupAnagrams(strs2);
        // 遍历打印
        for (List<String> list : ret) {
            System.out.println(list);
        }
    }
}
