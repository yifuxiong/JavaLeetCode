package 剑指Offer22链表中倒数第k个节点;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode q = head;
        for (int i = 0; i < k; i++){
            q = q.next;
        }
        ListNode p = head;
        while (q != null){
            p = p.next;
            q = q.next;
        }
        return p;
    }
}
