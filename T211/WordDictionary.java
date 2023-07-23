package T211;


// 设计题
public class WordDictionary {
    class TrieNode {
        TrieNode[] tns = new TrieNode[26];
        boolean end;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // add的时候不会添加带'.'的字符串，比如这种'b.d'
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int u = word.charAt(i) - 'a';
            if (p.tns[u] == null) {
                p.tns[u] = new TrieNode();
            }
            p = p.tns[u];
        }
        p.end = true;
    }

    // search的时候可能会查找'b.d'这种字符串
    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    public boolean dfs(String s, TrieNode root, int idx) {
        int n = s.length();
        if (n == idx) {
            return root.end;
        }
        char ch = s.charAt(idx);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.tns[i] != null && dfs(s, root.tns[i], idx + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int u = ch - 'a';
            if (root.tns[u] == null) {
                return false;
            }
            return dfs(s, root.tns[u], idx + 1);
        }
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }
}
