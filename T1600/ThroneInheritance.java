package T1600;

import java.util.*;

public class ThroneInheritance {
    String king;
    // 父子表
    Map<String, List<String>> hashMap;
    // 死亡表
    Set<String> hashSet;

    public ThroneInheritance(String kingName) {
        hashMap = new HashMap<>();
        hashSet = new HashSet<>();

        // 初始化
        king = kingName;
        hashMap.put(kingName, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        hashMap.get(parentName).add(childName);
        hashMap.put(childName, new ArrayList<>());
    }

    public void death(String name) {
        hashSet.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> inheritance = new ArrayList<>();
        dfs(inheritance, king);
        return inheritance;
    }

    public void dfs(List<String> inheritance, String king){
        if (!hashSet.contains(king)){
            inheritance.add(king);
        }
        for (String name: hashMap.get(king)){
            dfs(inheritance, name);
        }
    }

    public static void main(String[] args) {
        ThroneInheritance t = new ThroneInheritance("king");
        t.birth("king", "andy");
        t.birth("king", "bob");
        t.birth("king", "catherine");
        t.birth("king", "matthew");
        t.birth("king", "alex");
        t.birth("king", "asha");
        System.out.println(t.getInheritanceOrder());
        t.death("bob");
        System.out.println(t.getInheritanceOrder());
    }
}
