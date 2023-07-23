package T993;

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
    private int xParent = -1;
    private int xLevel = 0;
    private int yParent = -1;
    private int yLevel = 0;

    public boolean isCousins(TreeNode root, int x, int y) {
        getParentAndLevel(root, x, y, -1, 0);
        if (xParent != yParent && xLevel == yLevel) {
            return true;
        }
        return false;
    }

    public void getParentAndLevel(TreeNode root, int x, int y, int parent, int level) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            xParent = parent;
            xLevel = level;
        } else if (root.val == y) {
            yParent = parent;
            yLevel = level;
        } else {
            getParentAndLevel(root.left, x, y, root.val, level + 1);
            getParentAndLevel(root.right, x, y, root.val, level + 1);
        }
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

        for (int i = 0; i < nodes.size() / 2; i++) {
            TreeNode root = nodes.get(i);
            if (root == null) {
                continue;
            }
            root.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nodes.size()) {
                root.right = nodes.get(i * 2 + 2);
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
        Integer[] nums = new Integer[]{1, 2, 3, 4};
        int x = 4, y = 3;
        Integer[] nums2 = new Integer[]{1, 2, 3, null, 4, null, 5};
        int x2 = 5, y2 = 4;
        Integer[] nums3 = new Integer[]{1, 2, 3, null, 4};
        int x3 = 2, y3 = 3;

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums2);
        solut.inOrder(root);
        System.out.println();

        System.out.println(solut.isCousins(root, x2, y2));
    }
}
