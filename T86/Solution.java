package T86;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

// 要保证相对位置
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null){
            return null;
        }

        // 新建两个带头结点的链表
        ListNode psHead = new ListNode(0);
        ListNode pbHead = new ListNode(0);

        // 头指针分别指向这两个链表
        ListNode ps = psHead;
        ListNode pb = pbHead;

        ListNode p = head;
        while (p != null){
            // 插入小链表
            if (p.val < x){
                ps.next = new ListNode(p.val);
                ps = ps.next;
            }
            // 否则插入大链表
            else{
                pb.next = new ListNode(p.val);
                pb = pb.next;
            }
            p = p.next;
        }
        // 注意这里psHead和pbHead都是带头结点的链表
        ps.next = pbHead.next;
        return psHead.next;
    }

    public static ListNode createList(int[] list){
        // 带头结点
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 0; i < list.length; i++){
            p.next = new ListNode(list[i]);
            p = p.next;
        }
        return head.next;
    }

    public static void printList(ListNode head){
        ListNode p = head;
        while (p != null){
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] list = {1, 4, 3, 2, 5, 2};
        int x = 3;

        ListNode head = createList(list);
        printList(head);

        Solution solut = new Solution();
        ListNode newHead = solut.partition(head, x);
        printList(newHead);
    }
}
