package T783;

import java.util.LinkedList;
import java.util.List;

// 这个方法还是不行，只能计算当前节点与当前根节点的差值，最后求最小
public class Solution2 {
    // 必须把diff设置为全局变量，因为取不到递归过程中临时变量
    private int diff;

    public int minDiffInBST(TreeNode root) {
        diff = Integer.MAX_VALUE;
        traverse(root);
        return diff;
    }

    // 我这个思路就不对了，这种写法只针对父节点与其子节点的差值。
    // 而有时候最小差值并不是在父节点与子节点上找，而是中序遍历的前后两个节点上。
    public void traverse(TreeNode root) {
        if (root != null) {
            traverse(root.left);
            if (root.left != null) {
                diff = Math.min(diff, root.val - root.left.val);
            }
            if (root.right != null) {
                diff = Math.min(diff, root.right.val - root.val);
            }
            traverse(root.right);
        }
    }

    public static TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != null) {
                nodes.add(new TreeNode(nums[i]));
            } else {
                nodes.add(null);
            }
        }

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

        Solution2 solut2 = new Solution2();
        TreeNode root = createTree(nums3);
        inOrder(root);
        System.out.println();

        System.out.println(solut2.minDiffInBST(root));
    }
}
