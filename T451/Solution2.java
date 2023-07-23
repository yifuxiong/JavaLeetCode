package T451;

import java.util.*;

public class Solution2 {
    public String frequencySort(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hashMap.put(s.charAt(i), hashMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        System.out.println(hashMap);

        // 对hashMap排序
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((o1, o2) -> (o2.getValue() - o1.getValue()));
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            queue.offer(entry);
        }
        System.out.println(queue);

        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> m = queue.poll();
            for (int i = 0; i < m.getValue(); i++) {
                sb.append(m.getKey());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        String s2 = "cccaaa";
        String s3 = "Aabb";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.frequencySort(s3));
    }
}
