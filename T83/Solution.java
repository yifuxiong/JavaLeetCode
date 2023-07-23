package T83;

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode first = new ListNode(-101);
        first.next = head;

        ListNode pre = first;
        ListNode cur = pre.next;
        while (cur != null) {
            if (cur.val == pre.val) {
                cur = cur.next;
            } else {
                pre.next = cur;
                pre = cur;
                cur = cur.next;
            }
        }
        pre.next = cur;
        return first.next;
    }

    public static ListNode createList(int[] nums) {
        ListNode first = new ListNode(-101);
        ListNode p = first;
        for (int n : nums) {
            ListNode newNode = new ListNode(n);
            p.next = newNode;
            p = p.next;
        }
        return first.next;
    }

    public static void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int[] nums2 = {1, 1, 2, 3, 3};
        int[] nums3 = {};

        Solution solut = new Solution();
        ListNode head = createList(nums3);
        printList(head);

        head = solut.deleteDuplicates(head);
        printList(head);
    }
}
