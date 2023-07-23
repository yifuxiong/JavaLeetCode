package T671;

import java.util.LinkedList;
import java.util.Queue;

// 主要思路：找一个比根节点大的最小值
public class Solution2 {
    // 广度优先遍历做一遍
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null){
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 立一个flag，表示是否有交换的过程
        boolean flag = false;
        int value = root.val;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            // 每一层的size大小
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();

                if (node == null){
                    continue;
                }
                if (node.val > value && node.val <= ans){
                    flag = true;
                    ans = node.val;
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        if (!flag){
            return -1;
        }else{
            return ans;
        }
    }
}
