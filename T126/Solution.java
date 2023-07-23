package T126;

import java.util.*;

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)){
            return ans;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Map<String, List<String>> hashMap = new HashMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                // 下一层
                for (String s : getNextWord(wordList, word)) {
                    if (!visited.contains(s)) {
                        List<String> slist = hashMap.getOrDefault(word, new ArrayList<>());
                        slist.add(s);
                        hashMap.put(word, slist);
                        if (s.equals(endWord)) {
                            List<String> elist = new ArrayList<>();
                            elist.add(endWord);
                            hashMap.put(s, elist);
                            break;
                        }
                        queue.offer(s);
                        visited.add(s);
                    }
                }
            }
        }
        // 打印
        System.out.println(hashMap);

        List<String> list = new ArrayList<>();
        dfs(ans, hashMap, list, beginWord, endWord);

        return ans;
    }

    public void dfs(List<List<String>> ans, Map<String, List<String>> hashMap, List<String> list, String word, String endWord) {
        if (word.equals(endWord)) {
            list.add(word);
            ans.add(list);
            return;
        } else if (!hashMap.containsKey(word)) {
            // 递归不下去了，终止条件
            list.add(word);
            return;
        } else {
            list.add(word);
        }
        for (String name : hashMap.get(word)) {
            List<String> newList = new ArrayList<>();
            for (String l : list) {
                newList.add(l);
            }
            dfs(ans, hashMap, newList, name, endWord);
        }
    }

    public List<String> getNextWord(List<String> wordList, String word) {
        List<String> ret = new ArrayList<>();

        char[] array = word.toCharArray();
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char ch = array[i];
            array[i] = '0';
            for (int j = 0; j < wordList.size(); j++) {
                String str = wordList.get(j);
                String preStr = str.substring(0, i);
                String postStr = str.substring(i + 1);
                String strstr = (preStr.concat("0")).concat(postStr);
                if (strstr.equals(new String(array))) {
                    ret.add(str);
                }
            }
            array[i] = ch;
        }
        return ret;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        String beginWord2 = "hit", endWord2 = "cog";
        List<String> wordList2 = new ArrayList<>();
        wordList2.add("hot");
        wordList2.add("dot");
        wordList2.add("dog");
        wordList2.add("lot");
        wordList2.add("log");


        String beginWord3 = "a", endWord3 = "c";
        List<String> wordList3 = new ArrayList<>();
        wordList3.add("a");
        wordList3.add("b");
        wordList3.add("c");

        Solution solut = new Solution();
        System.out.println(solut.findLadders(beginWord3, endWord3, wordList3));
    }
}
