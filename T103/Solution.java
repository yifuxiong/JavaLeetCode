package T103;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        // 普通队列，只有一端能够先进先出
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            // 双端队列，两端都能先进先出
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            // 遍历完一层，把这一层加入到返回的ans中
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean left = true;

        while (!queue.isEmpty()) {
            Deque<Integer> tmpList = new LinkedList<>();
            // queue这一层的size
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (left) {
                    tmpList.offerLast(node.val);
                } else {
                    tmpList.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(new LinkedList<>(tmpList));
            left = !left;
        }
        return list;
    }

    // 给定一个列表，创建一个完全二叉树
    public static TreeNode createTree(Integer[] list) {
        LinkedList<TreeNode> tree = new LinkedList<>();

        // 1.生成节点
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                tree.add(new TreeNode(list[i]));
            }
        }

        // 2.建立关系
        for (int i = 0; i < tree.size() / 2; i++) {
            TreeNode tmpRoot = tree.get(i);
            TreeNode tmpLeft = tree.get(i * 2 + 1);
            tmpRoot.left = tmpLeft;
            // 右子树
            if (i * 2 + 2 < tree.size()) {
                TreeNode tmpRight = tree.get(i * 2 + 2);
                tmpRoot.right = tmpRight;
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
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = createTree(list);
        levelTraverse(root);

        Solution solut = new Solution();
        List<List<Integer>> ans = solut.zigzagLevelOrder2(root);
        for (List<Integer> i : ans) {
            System.out.println(i);
        }
    }
}
