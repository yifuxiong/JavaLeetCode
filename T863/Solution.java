package T863;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    private List<Integer> ans;
    private Map<TreeNode, TreeNode> parentMap;
    private List<Integer> visited;  // 去除重复访问的元素

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ans = new ArrayList<>();
        parentMap = new HashMap<>();
        visited = new ArrayList<>();
        // 建立父节点关系
        getParent(root, null);

        // 向三个方向递归：左子树、右子树、父节点
        dfs(target, k);
        return ans;
    }

    public void getParent(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        parentMap.put(root, parent);
        getParent(root.left, root);
        getParent(root.right, root);
    }

    public void dfs(TreeNode root, int distance) {
        if (root == null || visited.contains(root.val)) {
            return;
        }
        visited.add(root.val);
        if (distance == 0) {
            ans.add(root.val);
            return;
        }
        dfs(root.left, distance - 1);
        dfs(root.right, distance - 1);
        dfs(parentMap.get(root), distance - 1);
    }
}
