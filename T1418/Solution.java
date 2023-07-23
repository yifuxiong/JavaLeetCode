package T1418;

import java.util.*;

public class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        // <桌号，食物>
        Map<Integer, Map<String, Integer>> hashMap = new TreeMap<>();
        // 食物清单
        Set<String> hashSet = new TreeSet<>();

        for (List<String> order : orders) {
            hashSet.add(order.get(2));
            Map<String, Integer> food = hashMap.getOrDefault(Integer.parseInt(order.get(1)), new HashMap<>());
            food.put(order.get(2), food.getOrDefault(order.get(2), 0) + 1);
            hashMap.put(Integer.parseInt(order.get(1)), food);
        }
        // System.out.println(hashMap);
        // System.out.println(hashSet);

        List<List<String>> ans = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        for (String s : hashSet) {
            title.add(s);
        }
        ans.add(title);
        // System.out.println(title);

        for (Map.Entry<Integer, Map<String, Integer>> entry : hashMap.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(entry.getKey()));
            for (int i = 1; i < title.size(); i++) {
                if (entry.getValue().containsKey(title.get(i))) {
                    list.add(String.valueOf(entry.getValue().get(title.get(i))));
                } else {
                    list.add("0");
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> table1 = new ArrayList<>();
        table1.add("David");
        table1.add("3");
        table1.add("Ceviche");
        List<String> table2 = new ArrayList<>();
        table2.add("Corina");
        table2.add("10");
        table2.add("Beef Burrito");
        List<String> table3 = new ArrayList<>();
        table3.add("David");
        table3.add("3");
        table3.add("Fried Chicken");
        List<String> table4 = new ArrayList<>();
        table4.add("Carla");
        table4.add("5");
        table4.add("Water");
        List<String> table5 = new ArrayList<>();
        table5.add("Carla");
        table5.add("5");
        table5.add("Ceviche");
        List<String> table6 = new ArrayList<>();
        table6.add("Rous");
        table6.add("3");
        table6.add("Ceviche");

        List<List<String>> orders = new ArrayList<>();
        orders.add(table1);
        orders.add(table2);
        orders.add(table3);
        orders.add(table4);
        orders.add(table5);
        orders.add(table6);

        Solution solut = new Solution();
        System.out.println(solut.displayTable(orders));
    }
}
