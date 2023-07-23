package T721;

import java.util.*;

class UnionFind {
    private int[] parent;

    public UnionFind(int len) {
        parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

}

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 每个email都对应一个唯一的索引
        Map<String, Integer> emailToIndex = new HashMap<>();
        // 每个email都对应一个（不一定唯一的）名字
        Map<String, String> emailToName = new HashMap<>();

        // email索引计数
        int emailCount = 0;
        // 遍历一遍accouts，将所有email映射一个唯一索引，将某几个email映射一个名字
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    // 映射唯一索引
                    emailToIndex.put(email, emailCount++);
                    // 映射名字
                    emailToName.put(email, name);
                }
            }
        }

        UnionFind unionFind = new UnionFind(emailCount);
        for(List<String> account: accounts){
            // 每个账户名字后第一个email
            String firstEmail = account.get(1);
            // 这个首email对应的索引
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            // 每个账户下，依次遍历其他email，将首email与其他email建立连接
            for (int i = 2; i<size; i++){
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                // 同一个账户下的第一个email索引和后面的email索引建立联系
                unionFind.union(firstIndex, nextIndex);
            }
        }

        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email: emailToIndex.keySet()){
            // 找到每个email的根节点的索引
            int index = unionFind.find(emailToIndex.get(email));
            // 新建一个账户列表
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<>());
            account.add(email);
            // 根节点索引对应email列表，组成的哈希表
            indexToEmails.put(index, account);
        }

        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails: indexToEmails.values()){
            Collections.sort(emails);
            // 先找出email列表中的首email，通过首email找用户名字
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            // 这个账户添加所有的email
            account.addAll(emails);
            merged.add(account);
        }

        return merged;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        List<String> account = new ArrayList<>();
        account.add("John");
        account.add("johnsmith@mail.com");
        account.add("john00@mail.com");
        List<String> account2 = new ArrayList<>();
        account2.add("John");
        account2.add("johnnybravo@mail.com");
        List<String> account3 = new ArrayList<>();
        account3.add("John");
        account3.add("johnsmith@mail.com");
        account3.add("john_newyork@mail.com");
        List<String> account4 = new ArrayList<>();
        account4.add("Mary");
        account4.add("mary@mail.com");
        accounts.add(account);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);

        Solution solut = new Solution();
        List<List<String>> ret = solut.accountsMerge(accounts);
        System.out.println(ret);
    }
}
