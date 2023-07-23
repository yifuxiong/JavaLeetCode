package T61;

class ListNode{
    int val;
    ListNode next;

    public ListNode(){}

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int len = getLen(head);
        if (len == 0 || len == 1){
            return head;
        }
        // k求mod
        if (k >= len){
            k = k % len;
        }
        k = len - k;  // 这一句很重要，仔细看是向后移动k位，所以指针从前往后遍历是遍历len-k次

        ListNode first = new ListNode(-101);
        first.next = head;

        ListNode mid = first;
        ListNode post = first;

        for (int count = 0; count < k; count++){
            mid = mid.next;
        }

        while (post.next != null){
            post = post.next;
        }

        // 闭合成环
        post.next = first.next;
        // 中间断开
        first.next = mid.next;
        mid.next = null;

        return first.next;
    }

    public static int getLen(ListNode head){
        ListNode p = head;
        int count = 0;
        while (p != null){
            count++;
            p = p.next;
        }
        return count;
    }

    public static ListNode createList(int[] nums){
        ListNode first = new ListNode(-101);
        ListNode p = first;
        for (int n: nums){
            ListNode newNode = new ListNode(n);
            p.next = newNode;
            p = newNode;
        }
        return first.next;
    }

    public static void printList(ListNode head){
        ListNode p = head;
        while (p != null){
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;

        int[] nums2 = {0, 1, 2};
        int k2 = 4;

        int[] nums3 = {};
        int k3 = 0;

        int[] nums4 = {1, 2};
        int k4 = 1;

        ListNode head = createList(nums3);
        printList(head);

        Solution solut = new Solution();
        head = solut.rotateRight(head, k3);
        printList(head);
    }
}
