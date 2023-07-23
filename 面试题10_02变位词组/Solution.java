package 面试题10_02变位词组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();

        Map<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String getS = getNumString(strs[i]);
            List<String> list = hashMap.getOrDefault(getS, new ArrayList<>());
            list.add(strs[i]);
            hashMap.put(getS, list);
        }
        // System.out.println(hashMap);

        for (List<String> list: hashMap.values()){
            ans.add(list);
        }
        return ans;
    }

    public String getNumString(String str) {
        int[] nums = new int[26];
        for (int i = 0; i < str.length(); i++) {
            nums[str.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Solution solut = new Solution();
        System.out.println(solut.groupAnagrams(strs));
    }
}
