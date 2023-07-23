package T692;

import java.util.*;

public class Solution {
    // 哈希表+排序
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        // System.out.println(hashMap);

        List<String> ans = new ArrayList<>(hashMap.keySet());
        Collections.sort(ans, (a, b) -> {
            if (hashMap.get(a).equals(hashMap.get(b))) {
                // 字符串频率相等，按照字符顺序排序
                return a.compareTo(b);
            } else {
                // 字符串频率不等，则按照频率排序
                return hashMap.get(b) - hashMap.get(a);
            }
        });

        return ans.subList(0, k);
    }

    // 优先队列（堆）
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> hashMap = new HashMap<>();
        for (String word: words){
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }

        // 哈希表按顺序排列好
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                // 频率相等，比较字符顺序；否则比较频率
                return entry1.getValue() == entry2.getValue() ? entry2.getKey().compareTo(entry1.getKey()) : entry1.getValue() - entry2.getValue();
            }
        });

        // 把hashMap中的entry依次装入队列
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            pq.offer(entry);
            // 若队列的容量大于k了，则前面进来的出队
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // 此时队列是增序排列
        List<String> ret = new ArrayList<String>();
        while (!pq.isEmpty()) {
            ret.add(pq.poll().getKey());
        }
        // 逆序
        Collections.reverse(ret);
        return ret;
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;

        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k2 = 4;

        Solution solut = new Solution();
        System.out.println(solut.topKFrequent(words2, k2));
        System.out.println(solut.topKFrequent2(words2, k2));
    }
}
