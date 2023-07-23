package T107;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null){
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Queue<Integer> tmp = new LinkedList<>();
            int size = queue.size();
            for (int i =0; i < size; i++){
                TreeNode curNode = queue.poll();
                tmp.offer(curNode.val);
                if (curNode.left != null){
                    queue.offer(curNode.left);
                }
                if (curNode.right != null){
                    queue.offer(curNode.right);
                }
            }
            list.add(0, new LinkedList<>(tmp));
        }
        return list;
    }

    public static TreeNode createTree(Integer[] list) {
        List<TreeNode> tree = new LinkedList<>();

        for (int i = 0; i < list.length; i++){
            if (list[i] != null){
                tree.add(new TreeNode(list[i]));
            }
        }

        for (int i = 0; i < tree.size()/2; i++){
            TreeNode tmpRoot = tree.get(i);
            TreeNode tmpLeft = tree.get(i * 2 + 1);
            tmpRoot.left = tmpLeft;
            if (i * 2 + 2 < tree.size()){
                TreeNode tmpRight = tree.get(i * 2 + 2);
                tmpRoot.right = tmpRight;
            }
        }
        return tree.get(0);
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = createTree(list);

        Solution solut = new Solution();
        List<List<Integer>> ans = solut.levelOrder(root);
        for (List<Integer> i: ans){
            System.out.println(i);
        }
    }
}
