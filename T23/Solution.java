package T23;

import java.util.Map;
import java.util.TreeMap;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode first = new ListNode(-10001);
        ListNode head = first;

        int n = lists.length;
        if (n == 0) {
            return head;
        }

        Map<Integer, Integer> hashMap = new TreeMap<>();
        for (ListNode list : lists) {
            ListNode p = list;
            while (p != null) {
                hashMap.put(p.val, hashMap.getOrDefault(p.val, 0) + 1);
                p = p.next;
            }
        }
        // System.out.println(hashMap);

        for (Map.Entry<Integer, Integer> entry: hashMap.entrySet()){
            int value = entry.getKey();
            int len = entry.getValue();
            for (int i = 0; i < len; i++) {
                first.next = new ListNode(value);
                first = first.next;
            }
        }

        return head.next;
    }
}
