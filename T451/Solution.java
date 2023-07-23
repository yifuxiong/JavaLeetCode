package T451;

import java.util.*;

public class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hashMap.put(s.charAt(i), hashMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        // System.out.println(hashMap);

        // 这里用集合自带的排序方法排序
        ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(hashMap.entrySet());
        Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : entries) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    // 桶排序
    // 关于频次问题可以使用桶排序来完成。可以以出现最大频次作为桶的个数，尽量减少桶的个数
    // https://leetcode-cn.com/problems/sort-characters-by-frequency/solution/dui-pai-xu-priorityqueue-shou-xie-dui-tong-pai-xu-/
    public String frequencySort2(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        }

        int[] frequency = new int[128];
        char[] chars = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            frequency[chars[i]]++;
        }

        int maxNum = 0;
        for (int num : frequency) {
            if (num > maxNum) {
                maxNum = num;
            }
        }

        // 1.初始化桶，注意这个是桶数组，单个桶的最大容量是maxNum
        List<Character>[] buckets = new ArrayList[maxNum + 1];

        // 2.装桶
        for (int k = 0; k < 128; k++){
            if (frequency[k] == 0)
                continue;
            int value = frequency[k];
            // 当期桶为空，创建一个新桶
            // 懂了！这里是关键一步，把频率作为下标，这样就默认排序了
            if (buckets[value] == null){
                buckets[value] = new ArrayList<>();
            }
            buckets[value].add((char)k);
        }

        StringBuffer sb = new StringBuffer();
        // 3.将桶中的元素倒出，从后向前倒出
        for (int j = buckets.length - 1; j >= 0; j--){
            if (buckets[j] == null)
                continue;
            for (char value: buckets[j]){
                while (frequency[value] > 0){
                    sb.append(value);
                    frequency[value]--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        String s2 = "cccaaa";
        String s3 = "Aabb";
        String s4 = "raaeaedere";

        Solution solut = new Solution();
        System.out.println(solut.frequencySort(s4));
        System.out.println(solut.frequencySort2(s4));
    }
}
