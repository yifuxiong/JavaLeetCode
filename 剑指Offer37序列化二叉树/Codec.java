package 剑指Offer37序列化二叉树;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Codec {
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

        return root;
    }


    // Encodes a tree to a single string.
    // 相当于是levelOrder()
    public String serialize2(TreeNode root) {
        List<String> list = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmpRoot = queue.poll();
                if (tmpRoot == null) {
                    list.add("null");
                    continue;
                }
                list.add(tmpRoot.val + "");
                queue.offer(tmpRoot.left);
                queue.offer(tmpRoot.right);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    // 相当于是createTree()
    public TreeNode deserialize2(String data) {
        String[] array = data.split(",");
        List<TreeNode> nodes = new ArrayList<>();
        // 1.生成node节点
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("null")) {
                nodes.add(null);
            } else {
                nodes.add(new TreeNode(Integer.valueOf(array[i])));
            }
        }
        // 2.建立关系
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

    public static void main(String[] args) {
        String data = "1,2,3,null,null,4,5";
        String data2 = "1,2,null";
        String data3 = "5,2,3,null,null,2,4,3,1";

        Codec cc = new Codec();
        TreeNode root = cc.deserialize(data3);
        String str = cc.serialize(root);
        System.out.println(str);
    }
}
