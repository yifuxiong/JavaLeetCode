package T677;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];  // 26个小写字母
        isEnd = false;
    }

    // 插入节点
    public void insert(String word) {
        Trie node = this;  // 指向根节点

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];  // node指向后面一个子节点
        }
        // 最后一个节点标记为true，表示从根节点到这个节点之间的所有节点能够组成一个单词
        node.isEnd = true;
    }

    // 根据前缀字符串查找
    public Trie searchPrefix(String prefix) {
        Trie node = this;  // 指向根节点

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    // 判断有无以prefix为前缀的单词
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // 返回以prefix为前缀的所有单词
    public List<String> getWordsByPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        if (startsWith(prefix)) {
            Trie node = searchPrefix(prefix);
            if (node.isEnd){  // 如果这个前缀也是个单词，那么也加到列表中去
                words.add(prefix);
            }
            func(words, node, prefix);
        }
        return words;
    }

    // 函数getWordsByPrefix()调用的递归函数
    public void func(List<String> words, Trie node, String prefix) {
        for (int i = 0; i < 26; i++) {
            Trie childNode = node.children[i];
            if (childNode == null) {
                continue;
            }
            String str = prefix + String.valueOf((char) (i + 97));
            if (childNode.isEnd && !words.contains(str)) {
                words.add(str);
            }
            func(words, childNode, str);
        }
    }

    public static void main(String[] args) {
        Trie node = new Trie();
        node.insert("apple");
        List<String> words = node.getWordsByPrefix("ap");
        System.out.println(words);
        node.insert("app");
        List<String> words2 = node.getWordsByPrefix("ap");
        System.out.println(words2);
        node.insert("apple");
        List<String> words3 = node.getWordsByPrefix("apple");
        System.out.println(words3);
    }
}

public class MapSum {
    private Map<String, Integer> hashMap;
    private Trie node;

    public MapSum() {
        hashMap = new HashMap<String, Integer>();
        node = new Trie();
    }

    public void insert(String key, int val) {
        node.insert(key);
        hashMap.put(key, val);
    }

    public int sum(String prefix) {
        List<String> words = node.getWordsByPrefix(prefix);
        if (words.size() == 0) {
            return 0;
        } else {
            int ans = 0;
            for (String str : words) {
                ans += hashMap.get(str);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MapSum ms = new MapSum();
        ms.insert("apple", 3);
        System.out.println(ms.sum("ap"));
        ms.insert("app", 2);
        System.out.println(ms.sum("ap"));
        ms.insert("apple", 5);
        ms.insert("apple", 1);
        System.out.println(ms.sum("apple"));
    }
}
