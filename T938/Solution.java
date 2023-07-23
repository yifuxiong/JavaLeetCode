package T938;

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

public class Solution {
    private int sum;
    public int rangeSumBST(TreeNode root, int low, int high) {
        sum = 0;
        return getSum(root, low, high);
    }

    public int getSum(TreeNode root, int low, int high){
        if (root != null){
            getSum(root.left, low, high);
            if (root.val >= low && root.val <= high){
                sum += root.val;
            }
            getSum(root.right, low, high);
        }
        return sum;
    }

    public TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != null) {
                nodes.add(new TreeNode(nums[i]));
            } else {
                nodes.add(null);
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

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {10, 5, 15, 3, 7, null, 18};
        int low = 7, high = 15;

        Integer[] nums2 = {10, 5, 15, 3, 7, 13, 18, 1, null, 6};
        int low2 = 6, high2 = 10;

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums2);
        solut.inOrder(root);
        System.out.println();

        System.out.println(solut.rangeSumBST(root, low2, high2));
    }
}
