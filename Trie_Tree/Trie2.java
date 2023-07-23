package Trie_Tree;

// 还是T208
public class Trie2 {
    // 方法二：TrieNode
    class TrieNode {
        TrieNode[] tns = new TrieNode[26];
        boolean end;
    }

    TrieNode root;

    public Trie2() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) {  // 如果p的第u个子节点为空
                p.tns[u] = new TrieNode();  // 新建第u个子节点
            }
            p = p.tns[u];  // p跳到这个子节点上，继续向下
        }
        p.end = true;  // 直到结尾，end=true表示从根节点到这个子节点能够形成一个单词
    }

    public boolean search(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) {  // 如果访问过程中某个字节点为空
                return false;  // 直接返回false
            }
            p = p.tns[u];
        }
        return p.end;  // 遍历到最后一个字符，如果end标识为true表示有这个单词，否则没有
    }

    public boolean startsWith(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) {  // 如果访问过程中某个字节点为空
                return false;  // 直接返回false
            }
            p = p.tns[u];
        }
        return true;  // s已经遍历完了，后面不管还有没有节点，都是以s开头的字符串
    }
}
