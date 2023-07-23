package T654;

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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return getMax(nums, 0, nums.length - 1);
    }

    public TreeNode getMax(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 找最大值
        int maxValue = nums[left];
        int maxIndex = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxValue);
        root.left = getMax(nums, left, maxIndex - 1);
        root.right = getMax(nums, maxIndex + 1, right);
        return root;
    }
}
