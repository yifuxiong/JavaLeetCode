package T92;

class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置一个pre, cur, nxt三个节点
        // 临时pre
        ListNode first = new ListNode(-1);
        first.next = head;

        ListNode pre = first;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode nxt;
        for (int i = 0; i < right - left; i++) {
            nxt = cur.next;  // cur一直是刚开始pre的下一个节点，即2
            cur.next = nxt.next;
            nxt.next = pre.next;  // pre的下一个节点
            pre.next = nxt;
        }

        return first.next;
    }

    public static void main(String[] args) {
        // 创建链表
        ListNode first = new ListNode(0);
        ListNode p = first;
        for (int i = 0; i <= 5; i++) {
            ListNode new_node = new ListNode(i);
            p.next = new_node;
            p = new_node;
        }

        // 为什么下面这个例子不行？
//        int[] nums = {-1, 0, -2};
//        for (int i = 0; i < nums.length; i++) {
//            ListNode new_node = new ListNode(nums[i]);
//            p.next = new_node;
//            p = new_node;
//        }

        int m = 2, n = 4;
        Solution solut = new Solution();
        solut.reverseBetween(first.next, m, n);
        // 打印结果
        ListNode q = first.next;
        while (q != null) {
            System.out.print(q.value + " ");
            q = q.next;
        }
        System.out.println();

    }
}
