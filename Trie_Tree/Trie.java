package Trie_Tree;

// T211构造TrieNode
// T212(Trie与回溯法)
// 包含位运算01的前缀树：T421

import java.util.Arrays;

// 本题以T208(实现TrieTree)为模板，实现TrieTree代码
// https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247488490&idx=1&sn=db2998cb0e5f08684ee1b6009b974089&chksm=fd9cb8f5caeb31e3f7f67dba981d8d01a24e26c93ead5491edb521c988adc0798d8acb6f9e9d&token=1006889101&lang=zh_CN#rd
public class Trie {
    // 方法一：二维数组

    // 以下 static 成员独一份，被创建的多个 Trie 共用
    static int N = 100009;  // 直接设置为十万级
    static int[][] trie = new int[N][26];
    static int[] count = new int[N];
    static int index = 0;  // 节点索引

    // 在构造方法中完成重置 static 成员数组的操作
    // 这样做的目的是为减少 new 操作（无论有多少测试数据，上述 static 成员只会被 new 一次）
    public Trie() {
        for (int row = index; row >= 0; row--) {
            Arrays.fill(trie[row], 0);
        }
        Arrays.fill(count, 0);
        index = 0;
    }

    public void insert(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) {  // 如果是一个新的节点
                trie[p][u] = ++index;  // 索引+1
            }
            p = trie[p][u];  // 跳到当前索引，继续向下
            count[p]++;  // 经过这个索引节点的单词数+1
        }
    }

    public boolean search(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) {
                return false;
            }
            p = trie[p][u];
        }
        return count[p] != 0;
    }

    public boolean startsWith(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) {
                return false;
            }
            p = trie[p][u];
        }
        return true;
    }
}
