package 两数相加;


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = getLen(l1) >= getLen(l2) ? getLen(l1) : getLen(l2);

        ListNode p = l1, q = l2;
        int upLevel = 0;
        while (p != null && q != null) {
            int sum = (p.val + q.val) % 10;
            p.val = sum + upLevel;
            // 更新进位
            upLevel = (p.val + q.val) / 10;

            p = p.next;
            q = q.next;
        }
        // l1后面添加一个新节点
        if (upLevel != 0){
            ListNode newNode = new ListNode(upLevel);
            p.next = newNode;
            p = newNode;
        }
        return l1;
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

    public ListNode createList(int[] nums) {
        ListNode first = new ListNode(-1);

        List<ListNode> nodes = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            nodes.add(node);
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
        int[] l1 = {2, 4, 3}, l2 = {5, 6, 4};
        int[] l12 = {9, 9, 9, 9, 9, 9, 9}, l22 = {9, 9, 9, 9};

        Solution solut = new Solution();
        ListNode list1 = solut.createList(l1);
        ListNode list2 = solut.createList(l2);
        solut.printList(list1);
        solut.printList(list2);

        ListNode listSum = solut.addTwoNumbers(list1, list2);
        solut.printList(listSum);
    }
}
