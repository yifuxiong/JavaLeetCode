package T705;

// 方法一肯定是不满意的
// 方法二我们用拉链法的思想，结果方法二超出时间限制，方法一没有
public class MyHashSet2 {
    Node[] nodes;

    /** Initialize your data structure here. */
    public MyHashSet2() {
        nodes = new Node[10009];
    }

    public void add(int key) {
        int idx = getIndex(key);

        Node loc = nodes[idx], tmp = loc;
        if (loc != null){
            Node pre = null;
            while (tmp != null){
                if (tmp.key == key){
                    return;
                }
                pre = tmp;
                tmp = tmp.next;
            }
            tmp = pre;
        }
        Node node = new Node(key);


        // 头插法
        node.next = loc;
        nodes[idx] = node;

        // 尾插法
        if (tmp != null){
            tmp.next = node;
        }else{
            nodes[idx] = node;
        }
    }

    public void remove(int key) {
        int idx = getIndex(key);
        Node loc = nodes[idx];

        if (loc != null){
            Node pre = null;
            while (loc != null){
                if (loc.key == key){
                    if (pre != null){
                        pre.next = loc.next;
                    }else{
                        nodes[idx] = loc.next;
                    }
                    return ;
                }
                pre = loc;
                loc = loc.next;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int idx = getIndex(key);
        Node loc = nodes[idx];
        if (loc != null){
            while (loc != null){
                if (loc.key == key){
                    return true;
                }
                loc = loc.next;
            }
        }
        return false;
    }

    static class Node{
        private int key;
        private Node next;

        private Node(int key){
            this.key = key;
        }
    }

    public int getIndex(int key){
        int hash = Integer.hashCode(key);
        hash ^= (hash >>> 16);
        return hash % nodes.length;
    }
}
