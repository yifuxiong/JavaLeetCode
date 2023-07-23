package T725;

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = getLen(head);
        int quotient = n / k, remainder = n % k;

        ListNode[] parts = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = quotient + (i < remainder ? 1 : 0);
            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }
            // 下一段的头结点
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return parts;
    }

    public int getLen(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }

    public ListNode createList(int[] nums) {
        ListNode first = new ListNode(-1);
        ListNode tail = first;
        first.next = tail;
        for (int i = 0; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            tail.next = node;
            tail = node;
        }
        return first.next;
    }

    public void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Solution solut = new Solution();
        ListNode head = solut.createList(nums);
        solut.printList(head);

        ListNode[] ret = solut.splitListToParts(head, 5);
        for (int i = 0; i < ret.length; i++) {
            ListNode root = ret[i];
            ListNode p = root;
            while (p != null) {
                System.out.print(p.val + " ");
                p = p.next;
            }
            System.out.println();
        }
    }
}
