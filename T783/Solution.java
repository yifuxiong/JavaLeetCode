package T783;

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
    // 记录最小差值
    private int diff = Integer.MAX_VALUE;
    // 中序遍历，遍历上一个节点的值
    private int preNum = -1;

    public int minDiffInBST(TreeNode root) {
        traverse(root);
        return diff;
    }

    public void traverse(TreeNode root) {
        if (root != null) {
            traverse(root.left);
            if (preNum == -1) {  // 这表明是中序遍历的第一个节点
                preNum = root.val;
            } else {  // 计算与上一个节点的差值，取最小值
                diff = Math.min(diff, root.val - preNum);
                preNum = root.val;
            }
            traverse(root.right);
        }
    }

    public static TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();

        for (Integer num : nums) {
            if (num != null) {
                nodes.add(new TreeNode(num));
            } else {
                nodes.add(null);
            }
        }

        for (int i = 0; i < nodes.size() / 2; i++) {
            TreeNode tmpRoot = nodes.get(i);
            if (tmpRoot == null) {
                continue;
            }
            TreeNode left = nodes.get(i * 2 + 1);
            tmpRoot.left = left;
            if (i * 2 + 2 < nodes.size()) {
                TreeNode right = nodes.get(i * 2 + 2);
                tmpRoot.right = right;
            }
        }
        return nodes.get(0);
    }

    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {4, 2, 6, 1, 3};
        Integer[] nums2 = {1, 0, 48, null, null, 12, 49};
        Integer[] nums3 = {90, 69, null, 49, 89, null, 52};

        Solution solut = new Solution();
        TreeNode root = createTree(nums3);
        inOrder(root);
        System.out.println();

        System.out.println(solut.minDiffInBST(root));
    }
}
