package T160;


import java.util.LinkedList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;

        int lenA = getLength(headA);
        int lenB = getLength(headB);
        int diff = lenA - lenB;

        if (diff > 0) {
            // a长于b，a先走
            while (diff > 0) {
                p = p.next;
                diff--;
            }
        } else if (diff < 0) {
            // a短于b，b先走
            while (diff < 0) {
                q = q.next;
                diff++;
            }
        }
        while (p != null && q != null) {
            if (p == q) {  // 判断地址？不是判断值相等。爷吐了
                return p;
            }
            p = p.next;
            q = q.next;
        }

        return null;
    }

    public int getLength(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }

    public ListNode createList(int[] nums) {
        ListNode first = new ListNode(-1);
        List<ListNode> nodes = new LinkedList<ListNode>();
        for (int n : nums) {
            ListNode node = new ListNode(n);
            first.next = node;
            nodes.add(node);
            first = first.next;
        }

        return nodes.get(0);
    }

    public void printListNode(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] listA = {4, 1, 8, 4, 5};
        int[] listB = {5, 0, 1, 8, 4, 5};

        Solution solut = new Solution();
        ListNode headA = solut.createList(listA);
        ListNode headB = solut.createList(listB);
        // solut.printListNode(headA);
        // solut.printListNode(headB);

        ListNode intersectNode = solut.getIntersectionNode(headA, headB);
    }
}
