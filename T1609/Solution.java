package T1609;

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
    // 本题可以用层次遍历（广度优先遍历）
    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean odd = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = !odd ? 0 : 1000001;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (odd && (node.val % 2 == 1 || node.val >= pre)) {
                    // 奇数层，偶数值，单减
                    return false;
                } else if (!odd && (node.val % 2 == 0 || node.val <= pre)) {
                    // 偶数层，奇数值，单增
                    return false;
                }
                pre = node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            odd = !odd;
        }
        return true;
    }

    public TreeNode createTree(Integer[] list) {
        // 构造节点
        int n = list.length;
        List<TreeNode> tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (list[i] == null) {
                tree.add(null);
            } else {
                tree.add(new TreeNode(list[i]));
            }
        }

        // 建立关系
        for (int i = 0; i < n / 2; i++) {
            TreeNode tmpRoot = tree.get(i);
            if (tmpRoot == null) {
                continue;
            }
            tmpRoot.left = tree.get(i * 2 + 1);
            if (i * 2 + 2 < n) {
                tmpRoot.right = tree.get(i * 2 + 2);
            }
        }
        return tree.get(0);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        inOrder(root.left);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        Integer[] list = {1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2};
        Integer[] list2 = {5, 4, 2, 3, 3, 7};
        Integer[] list3 = {5, 9, 1, 3, 5, 7};
        Integer[] list4 = {1};
        Integer[] list5 = {11, 8, 6, 1, 3, 9, 11, 30, 20, 18, 16, 12, 10, 4, 2, 17};
        Integer[] list6 = {2, 12, 8, 5, 9, null, null, 18, 16};

        Solution solut = new Solution();
        TreeNode root = solut.createTree(list);
        // solut.inOrder(root);
        System.out.println(solut.isEvenOddTree(root));
    }
}
