package T472;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Trie {
    Trie[] children;
    boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
}

public class Solution {
    // 只生成一棵前缀树
    Trie trie = new Trie();

    // 前缀树 + 深度优先遍历
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        // 按照字符串长度从小到大排序
        Arrays.sort(words, (a, b) -> {
            return a.length() - b.length();
        });

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() == 0) {
                continue;
            }

            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    public boolean dfs(String word, int start) {
        if (word.length() == start) {  // 边界条件
            return true;
        }

        Trie node = trie;
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            node = node.children[index];
            if (node == null) {
                return false;
            }
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 前缀树的插入操作
    public void insert(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                // 生成一个新节点
                node.children[index] = new Trie();
            }
            // 顺着这个节点继续找下去
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        String[] words2 = {"cat", "dog", "catdog"};

        Solution solut = new Solution();
        System.out.println(solut.findAllConcatenatedWordsInADict(words2));
    }
}
