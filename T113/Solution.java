package T113;

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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new LinkedList<>();
        Deque<Integer> stack = new LinkedList<>();
        dfs(root, targetSum, 0, ans, stack);
        return ans;
    }

    public void dfs(TreeNode root, int targetSum, int sum, List<List<Integer>> ans, Deque<Integer> stack) {
        if (root == null) {
            return;
        }
        stack.offerLast(root.val);
        if (root.left == null && root.right == null) {
            if (targetSum == root.val + sum) {
                ans.add(new LinkedList<>(stack));
            }
        }
        if (root.left != null) {
            dfs(root.left, targetSum, root.val + sum, ans, stack);
        }
        if (root.right != null) {
            dfs(root.right, targetSum, root.val + sum, ans, stack);
        }
        stack.pollLast();
    }

    public TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();
        for (Integer n : nums) {
            if (n == null) {
                nodes.add(null);
            } else {
                nodes.add(new TreeNode(n));
            }
        }

        for (int i = 0; i < nodes.size() / 2; i++) {
            TreeNode tmpRoot = nodes.get(i);
            if (tmpRoot == null) {
                continue;
            }
            tmpRoot.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nodes.size()) {
                tmpRoot.right = nodes.get(i * 2 + 2);
            }
        }
        return nodes.get(0);
    }

    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp == null) {
                    continue;
                }
                System.out.print(tmp.val + " ");
                queue.offer(tmp.left);
                queue.offer(tmp.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        int targetSum = 22;

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums);
        solut.levelOrder(root);
        System.out.println(solut.pathSum(root, targetSum));
    }
}
