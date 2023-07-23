package T897;

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
    private List<TreeNode> nodes;
    // 中序遍历之后生成新的树
    public TreeNode increasingBST(TreeNode root) {
        nodes = new LinkedList<>();
        nodes = mid(root);

        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).left = null;
            if (i + 1 < nodes.size())
                nodes.get(i).right = nodes.get(i + 1);
        }

//        for (int i = 0; i < nodes.size(); i++) {
//            System.out.println(nodes.get(i).val);
//        }
        return nodes.get(0);
    }

    public List<TreeNode> mid(TreeNode root) {
        if (root != null) {
            mid(root.left);
            nodes.add(root);
            mid(root.right);
        }
        return nodes;
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

//        for (int i = 0; i < nodes.size(); i++) {
//            if (nodes.get(i) != null)
//                System.out.println(nodes.get(i).val);
//        }

        return nodes.get(0);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, 7, 9};

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums);
        solut.inOrder(root);
        System.out.println();

        solut.increasingBST(root);
    }
}
