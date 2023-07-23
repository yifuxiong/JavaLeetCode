package T173;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// 二叉搜索树的递归写法需要建立一个栈
public class BSTIterator {
    private TreeNode root;
    // 建立一个栈
    private Deque<TreeNode> stack;

    // 把这棵树转换成二叉搜索树迭代器
    public BSTIterator(TreeNode root) {
        this.root = root;
        stack = new LinkedList<>();
    }

    public int next() {
        // 先疯狂向左
        while (root != null){
            stack.push(root);
            root = root.left;
        }
        // 向左遍历到没有，出栈
        root = stack.pop();
        int ans = root.val;
        // 再向右一次，之后继续疯狂向左
        root = root.right;
        return ans;
    }

    public boolean hasNext() {
        return root != null || !stack.isEmpty();
    }

    // 新建一棵树
    public static TreeNode createTree(Integer[] nums) {
        // 1.生成链表节点
        List<TreeNode> nodes = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != null) {
                nodes.add(new TreeNode(nums[i]));
            }else{  // null也要作为空节点加进去
                nodes.add(null);
            }
        }

        // 2.建立节点关系
        for (int i = 0; i < nodes.size() / 2; i++) {
            if (nodes.get(i) == null){
                continue;
            }
            TreeNode tmpRoot = nodes.get(i);
            tmpRoot.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nodes.size()) {
                tmpRoot.right = nodes.get(i * 2 + 2);
            }
        }
        return nodes.get(0);
    }

    // 中序遍历这棵树
    public static void inOrder(TreeNode root){
        if (root == null){
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        Integer[] nums = {7, 3, 15, null, null, 9, 20};
        TreeNode root = createTree(nums);
        inOrder(root);
        System.out.println("**********");
        BSTIterator bst = new BSTIterator(root);
        System.out.println(bst.next());
        System.out.println(bst.next());
        System.out.println(bst.hasNext());
        System.out.println(bst.next());
        System.out.println(bst.hasNext());
        System.out.println(bst.next());
        System.out.println(bst.hasNext());
        System.out.println(bst.next());
        System.out.println(bst.hasNext());
    }
}