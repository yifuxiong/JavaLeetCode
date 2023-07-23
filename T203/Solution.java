package T203;


import java.util.LinkedList;
import java.util.List;

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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        ListNode first = new ListNode(-1);
        first.next = head;
        // printList(first);

        ListNode cur = first.next;
        ListNode pre = first;
        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }

        return first.next;
    }

    public ListNode createList(int[] nums) {
        if (nums.length < 1){
            return null;
        }
        ListNode first = new ListNode(-1);
        List<ListNode> nodes = new LinkedList<>();
        for (int n : nums) {
            // 生成节点
            ListNode node = new ListNode(n);
            nodes.add(node);
            // 建立关系
            first.next = node;
            first = node;
        }
        return nodes.get(0);
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
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        int val = 6;

        int[] nums2 = {};
        int val2 = 1;

        int[] nums3 = {7, 7, 7, 7};
        int val3 = 7;

        Solution solut = new Solution();
        ListNode head = solut.createList(nums2);
        // solut.printList(head);

        head = solut.removeElements(head, val2);
        solut.printList(head);
    }
}
