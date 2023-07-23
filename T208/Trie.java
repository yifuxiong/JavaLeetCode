package T208;

// TrieNode root = new TrieNode;
// TrieNode node = this.root;
class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

public class Trie {
    // 把这个Trie看做Node类型
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        // 看做根节点？TrieNode node = this.root
        Trie node = this;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';

            if (node.children[index] == null) {
                // 生成一个新节点
                node.children[index] = new Trie();
            }
            // 继续查找子节点
            node = node.children[index];
        }
        // 结束设置isEnd为True
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public Trie searchPrefix(String prefix) {
        // 看做根节点？TrieNode node = this.root
        Trie node = this;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';

            if (node.children[index] == null) {
                return null;
            }
            // 继续向子节点遍历
            node = node.children[index];
        }
        return node;
    }

    public static void main(String[] args) {
        String[] funcName = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        String[][] params = {{}, {"apple"}, {"apple"}, {"app"}, {"app"}, {"app"}, {"app"}};

        Trie trie = new Trie();
        for (int i = 1; i < funcName.length; i++){
            if (funcName[i].equals("insert")){
                System.out.println(funcName[i] + ", " + params[i][0]);
                trie.insert(params[i][0]);
            }else if (funcName[i].equals("search")){
                System.out.print(funcName[i] + ", " + params[i][0] + " ");
                System.out.println(trie.search(params[i][0]));
            }else if (funcName[i].equals("startsWith")){
                System.out.print(funcName[i] + ", " + params[i][0] + " ");
                System.out.println(trie.startsWith(params[i][0]));
            }
        }
    }
}
