package T671;

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

// 主要思路：找一个比根节点大的最小值
public class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return -1;
        }
        return getSmaller(root, root.val);
    }

    public int getSmaller(TreeNode root, int value) {
        if (root == null) {
            return -1;
        }
        // 如果值比根节点的值大，那么保留
        if (root.val > value) {
            return root.val;
        }

        int leftVal = getSmaller(root.left, value);
        int rightVal = getSmaller(root.right, value);
        // 如果左右节点存在
        if (leftVal >= 0 && rightVal >= 0) {
            return Math.min(leftVal, rightVal);
        } else {  // 如果有一个不存在，必有一个是-1，那么取其中的较大值
            return Math.max(leftVal, rightVal);
        }
    }
}
