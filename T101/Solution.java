package T101;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Solution {
    // 无非是：
    // 左子树的左节点与右子树的有节点比较
    // 左子树的右节点与右子树的左节点比较
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // 每一层的size大小
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode leftNode = queue.poll();
                TreeNode rightNode = queue.poll();
                if (leftNode == null && rightNode == null) {
                    continue;
                }
                if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                    return false;
                }
                // 以下存入顺序不能乱

                // 左子树的左节点与右子树的有节点比较
                queue.offer(leftNode.left);
                queue.offer(rightNode.right);
                // 左子树的右节点与右子树的左节点比较
                queue.offer(leftNode.right);
                queue.offer(rightNode.left);
            }
        }
        return true;
    }

    public TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();
        // 创建节点
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != null) {
                nodes.add(new TreeNode(nums[i]));
            } else {
                nodes.add(null);
            }
        }

        // 建立关系
        for (int i = 0; i < nodes.size() / 2; i++) {
            TreeNode tmpRoot = nodes.get(i);
            tmpRoot.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nodes.size()) {
                tmpRoot.right = nodes.get(i * 2 + 2);
            }
        }
        return nodes.get(0);
    }

    // 层次遍历
    public void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    System.out.print(node.val + " ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 2, 3, 4, 4, 3};
        Integer[] nums2 = {1, 2, 2, null, 3, null, 3};
        Integer[] nums3 = {1, 2, 2, 3, 3, 4, 4};

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums3);
        solut.levelTraverse(root);
        System.out.println();

        System.out.println(solut.isSymmetric(root));
    }
}
