package T49;

import java.util.*;

public class Solution {
    // 排序
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 把每个字符串装换成字符数组
            char[] arr = str.toCharArray();
            // 再排序，非静态方法，会改变原值
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        ret.addAll(map.values());
        return ret;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<Integer>> hashMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++){
            String str = strs[i];
            // 排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);
            List<Integer> list = hashMap.getOrDefault(key, new ArrayList());
            list.add(i);
            hashMap.put(key, list);
        }

        for (String key: hashMap.keySet()){
            List<Integer> list = hashMap.get(key);
            List<String> a = new ArrayList<>();
            for (int i = 0; i < list.size(); i++){
                a.add(strs[list.get(i)]);
            }
            ans.add(a);
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Solution solut = new Solution();
        List<List<String>> ret = solut.groupAnagrams(strs);
        // 遍历打印
        for (List<String> list : ret) {
            System.out.println(list);
        }
    }
}
