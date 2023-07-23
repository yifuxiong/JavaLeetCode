package T160;

import java.util.LinkedList;
import java.util.List;

// 题目给的参数和给的测试用例不相符，搞得我重新写一遍
// 然后发现第一遍写的是对的
public class Solution2 {
    public ListNode getIntersectionNode(int intersectVal, ListNode headA, ListNode headB, int skipA, int skipB) {
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
        int intersectVal = 8;
        int[] listA = {4, 1, 8, 4, 5};
        int[] listB = {5, 0, 1, 8, 4, 5};
        int skipA = 2, skipB = 3;

        Solution2 solut2 = new Solution2();
        ListNode headA = solut2.createList(listA);
        ListNode headB = solut2.createList(listB);
        solut2.printListNode(headA);
        solut2.printListNode(headB);

        // ListNode intersectNode = solut2.getIntersectionNode(intersectVal, headA, headB, skipA, skipB);
        // System.out.println(intersectNode.val);
    }
}
