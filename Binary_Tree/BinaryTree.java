package Binary_Tree;

// T94，中序遍历非递归
// T173，二叉搜索树的递归写法

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree {
    // 创建完全二叉树
    public static TreeNode createTree(Integer[] list) {
        LinkedList<TreeNode> tree = new LinkedList<>();

        // 1.生成节点
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                tree.add(new TreeNode(list[i]));
            } else {
                tree.add(null);
            }
        }

        // 2.建立关系
        for (int i = 0; i < tree.size() / 2; i++) {
            TreeNode tmpRoot = tree.get(i);
            if (tmpRoot == null) {
                continue;
            }
            tmpRoot.left = tree.get(i * 2 + 1);
            if (i * 2 + 2 < tree.size()) {
                tmpRoot.right = tree.get(i * 2 + 2);
            }
        }
        return tree.get(0);
    }

    // 层次遍历
    public static void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            System.out.print(curNode.val + " ");
            if (curNode.left != null) {
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }
        }
    }

    // 中序遍历
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        // 千万记住，创建完全二叉树的时候，要凑足(2^h-1, h=1,2...)个节点
        // list中一定要有(2^h-1, h=1,2...)个节点，哪怕是null的节点下面，也得有null填充
        Integer[] list = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        Integer[] list2 = {5, 2, 3, null, null, 2, 4, null, null, null, null, 3, 1, null, null};
        /**
         *               5
         *            /     \
         *           3       6
         *         /  \     /  \
         *        2    4   N    8
         *       / \  / \ / \  / \
         *      1  N  N N N N  7 9
         */

        // 创建一棵二叉树
        TreeNode root = createTree(list2);

        // 层次遍历
        levelTraverse(root);
        System.out.println();

        // 中序遍历
        inOrder(root);
        System.out.println();
    }
}
