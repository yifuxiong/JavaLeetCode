package DFS.T538;

import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    private int preSum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null){
            convertBST(root.right);
            root.val = root.val + preSum;
            preSum = root.val;
            convertBST(root.left);
        }
        return root;
    }

    public static TreeNode createBTree(Integer[] nums){
        List<TreeNode> nodes = new LinkedList<>();

        // 1.生成节点
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != null) {
                nodes.add(new TreeNode(nums[i]));
            } else {
                nodes.add(null);
            }
        }

        // 2.建立连接
        for (int i = 0; i < nodes.size() / 2; i++) {
            if (nodes.get(i) == null) {
                continue;
            }
            TreeNode tmpRoot = nodes.get(i);
            tmpRoot.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nodes.size()) {
                tmpRoot.right = nodes.get(i * 2 + 2);
            }
        }
        return nodes.get(0);
    }

    public static void inOrder(TreeNode root){
        if (root != null){
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args){
        Integer[] nums = {4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
        TreeNode root = createBTree(nums);
        inOrder(root);
        System.out.println();

        Solution solut = new Solution();
        root = solut.convertBST(root);
        inOrder(root);
    }
}
