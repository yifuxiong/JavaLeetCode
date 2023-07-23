package T430;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    Node(int val) {
        this.val = val;
    }
}


public class Solution {
    // 递归
    public Node flatten(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        while (head != null) {
            if (head.child == null) {
                head = head.next;
            } else {
                Node tmp = head.next;  // 先将后面的其他节点保存下来
                Node chead = flatten(head.child);  // 将子节点递归展平
                head.next = chead;
                chead.prev = head;
                head.child = null;  // 断开子节点

                while (head.next != null) {
                    head = head.next;
                }  // 遍历到child那条链的最后
                head.next = tmp;  // 重新连接从有child节点断开的那个地方
                if (tmp != null) {
                    tmp.prev = head;
                }
                head = tmp;
            }
        }
        return dummy.next;
    }

    // 迭代
    public Node flatten2(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        for (;head != null;) {
            if (head.child == null) {
                head = head.next;
            } else {  // 遇到第一个有子节点的节点
                Node tmp = head.next;  // 先将后面的其他节点保存下来
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;

                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = tmp;
                if (tmp != null) {
                    tmp.prev = last;
                }
                head = head.next;  // 注意上面有一句head.next = child，进入子节点继续迭代
            }
        }
        return dummy.next;
    }
}
