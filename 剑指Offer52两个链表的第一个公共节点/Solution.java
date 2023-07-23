package 剑指Offer52两个链表的第一个公共节点;


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// 本题与主站 160 题相同
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLen(headA);
        int lenB = getLen(headB);


        ListNode pA = headA;
        ListNode pB = headB;
        int lenDiff = Math.abs(lenA - lenB);

        if (lenA > lenB) {
            for (int i = 0; i < lenDiff; i++) {
                pA = pA.next;
            }
        }

        if (lenB > lenA) {
            for (int i = 0; i < lenDiff; i++) {
                pB = pB.next;
            }
        }

        while (pA != null && pB != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }

    public int getLen(ListNode head) {
        int len = 0;

        ListNode p = head;
        while (p != null) {
            p = p.next;
            len++;
        }

        return len;
    }


    // 方法二：文强的方法
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        if (pA == null || pB == null) {
            return null;
        }

        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }
            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }
}
