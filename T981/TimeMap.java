package T981;

import java.util.*;

public class TimeMap {
    Map<String, List<String>> foobar;
    Map<Integer, String[]> bartime;

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
        foobar = new HashMap<String, List<String>>();
        bartime = new TreeMap<Integer, String[]>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void set(String key, String value, int timestamp) {
        List<String> list = foobar.getOrDefault(key, new ArrayList<>());
        list.add(value);
        // 一个key对应多个value
        foobar.put(key, list);
        // 当前时间戳对应map
        String[] str = new String[2];
        str[0] = key;
        str[1] = value;
        bartime.put(timestamp, str);
    }

    public String get(String key, int timestamp) {
        if (!foobar.containsKey(key)){
            return "";
        }
        List<String> list = foobar.get(key);
        if (list.isEmpty()) {
            return "";
        } else {
            for (Map.Entry<Integer, String[]> entry: bartime.entrySet()) {
                Integer cur = entry.getKey();
                String k = entry.getValue()[0];
                String v = entry.getValue()[1];
                if (k.equals(key) && list.contains(v) && cur <= timestamp){
                    return v;
                }
            }
            return "";
        }
    }

    public static void main(String[] args) {

        TimeMap tm = new TimeMap();
        tm.set("rtzoj", "kuexwze", 1);
        tm.set("xcywxndnz", "herqmazp", 2);
        System.out.println(tm.get("xcywxndnz", 3));
        tm.set("rtzoj", "dgpguflin", 4);
        System.out.println(tm.get("xcywxndnz", 5));
        tm.set("dgpguflin", "lvrexco", 6);
        tm.set("xcywxndnz", "dgpguflin", 7);
        System.out.println(tm.get("xcywxndnz", 8));
        tm.set("rtzoj", "wxqixmxs", 9);
        System.out.println(tm.get("xcywxndnz", 10));
        tm.set("kuexwze", "lvrexco", 11);
        System.out.println(tm.get("dgpguflin", 12));
        tm.set("lvrexco", "wxqixmxs", 13);

        System.out.println(tm.get("xcywxndnz", 14));
        tm.set("herqmazp", "vjfhio", 15);
        System.out.println(tm.get("dgpguflin", 16));
        System.out.println(tm.get("herqmazp", 17));
        System.out.println(tm.get("herqmazp", 18));
        System.out.println(tm.get("rtzoj", 19));
        System.out.println(tm.get("herqmazp", 20));
        System.out.println(tm.get("herqmazp", 21));
        tm.set("kuexwze", "vjfhio", 22);
        tm.set("dgpguflin", "qrkihrb", 23);
        tm.set("kuexwze", "dgpguflin", 24);
        System.out.println(tm.get("rtzoj", 25));
        System.out.println(tm.get("dgpguflin", 26));
        tm.set("herqmazp", "rtzoj", 27);
        tm.set("lvrexco", "iztpo", 28);
        System.out.println(tm.get("lvrexco", 29));
        tm.set("kuexwze", "lvrexco", 30);
    }
}
