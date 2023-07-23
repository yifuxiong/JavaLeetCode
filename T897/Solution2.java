package T897;

import java.util.LinkedList;
import java.util.List;

public class Solution2 {
    private TreeNode res;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode firstNode = new TreeNode(-1);
        res = firstNode;
        mid(root);

//        TreeNode node = firstNode.right;
//        while (node != null){
//            System.out.println(node.val);
//            node = node.right;
//        }

        return firstNode.right;
    }

    public void mid(TreeNode root){
        if (root != null) {
            mid(root.left);

            // 在中序遍历过程中修改节点指向
            res.right = root;
            root.left = null;
            res = root;

            mid(root.right);
        }
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
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, 7, 9};

        Solution2 solut2 = new Solution2();
        TreeNode root = solut2.createTree(nums);
        // solut2.inOrder(root);
        System.out.println();

        solut2.increasingBST(root);
    }
}
