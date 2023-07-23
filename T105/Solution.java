package T105;

import java.util.HashMap;

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
    HashMap<Integer, Integer> hashMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }

        TreeNode root = createTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }

    public TreeNode createTree(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        if (p_start > p_end) {
            return null;
        }

        int rootVal = preorder[p_start];
        int index = hashMap.get(rootVal);
        TreeNode tmpRoot = new TreeNode(rootVal);

        tmpRoot.left = createTree(preorder, p_start + 1, p_start + (index - i_start), inorder, i_start, index - 1);
        tmpRoot.right = createTree(preorder, p_start + 1 + (index - i_start), p_end, inorder, index + 1, i_end);
        return tmpRoot;
    }
}
