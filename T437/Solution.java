package T437;

import java.util.LinkedList;
import java.util.List;

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
    int ans = 0;
    int target;

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        preOrder(root);
        return ans;
    }

    public void dfs(TreeNode root, int targetSum, int lastSum) {
        if (root == null) {
            return;
        }
        if (lastSum + root.val == targetSum) {
            ans += 1;
        }
        lastSum += root.val;
        dfs(root.left, targetSum, lastSum);
        dfs(root.right, targetSum, lastSum);
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root, target, 0);
        preOrder(root.left);
        preOrder(root.right);
    }

    public TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();
        for (Integer n : nums) {
            if (n != null) {
                nodes.add(new TreeNode(n));
            } else {
                nodes.add(null);
            }
        }

        for (int i = 0; i < nums.length / 2; i++) {
            TreeNode root = nodes.get(i);
            if (root == null) {
                continue;
            }
            root.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nums.length) {
                root.right = nodes.get(i * 2 + 2);
            }
        }
        return nodes.get(0);
    }


    public static void main(String[] args) {
        Integer[] nums = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        int targetSum = 8;
        Integer[] nums2 = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        int targetSum2 = 22;

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums2);
        System.out.println(solut.pathSum(root, targetSum2));
    }
}
