package DFS.T971;

import java.util.Deque;
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
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> ans = new LinkedList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        // 先序遍历
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                ans.add(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }

        System.out.println(ans);

        return ans;
    }

    public static TreeNode createBTree(Integer[] nums) {
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

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, null, null, 4, 5};
        int[] voyage = {1, 3, 4, 5, 2};

        Solution solut = new Solution();
        TreeNode root = createBTree(nums);
        solut.flipMatchVoyage(root, voyage);
    }
}
