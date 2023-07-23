package T82;

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
    public ListNode deleteDuplicates(ListNode head) {
        if (getLen(head) < 2){
            return head;
        }

        ListNode first = new ListNode(-101);
        first.next = head;

        ListNode pre = first;
        ListNode cur = pre.next;

        while (cur.next != null) {
            if (cur.next.val != cur.val){
                pre.next = cur;
                pre = cur;
                cur = cur.next;
            }else{
                while (cur.next != null && cur.next.val == cur.val){
                    cur = cur.next;
                }
                if (cur.next == null){
                    pre.next = null;
                }else {
                    cur = cur.next;
                    pre.next = cur;
                }
            }
        }
        return first.next;
    }

    public static int getLen(ListNode head){
        ListNode p = head;

        int len = 0;
        while (p != null){
            len++;
            p = p.next;
        }
        return len;
    }

    public static ListNode createList(int[] nums) {
        ListNode first = new ListNode(-101);
        ListNode p = first;
        for (int n : nums) {
            ListNode newNode = new ListNode(n);
            p.next = newNode;
            p = newNode;
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
        int[] nums = {1, 2, 3, 3, 4, 4, 5};
        int[] nums2 = {1, 1, 1, 2, 3};
        int[] nums3 = {1, 1, 2, 2, 3, 3};

        ListNode head = createList(nums3);
        printList(head);

        Solution solut = new Solution();
        head = solut.deleteDuplicates(head);
        printList(head);
    }
}
