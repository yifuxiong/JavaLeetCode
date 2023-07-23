package T987;

import java.util.*;

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

// 硬是堆出来的
public class Solution {
    private Map<String, List<Integer>> hashMap;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        hashMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] getCoord1 = o1.split(",");
                String[] getCoord2 = o2.split(",");
                int o1row = Integer.parseInt(getCoord1[0]);
                int o1col = Integer.parseInt(getCoord1[1]);
                int o2row = Integer.parseInt(getCoord2[0]);
                int o2col = Integer.parseInt(getCoord2[1]);

                if (o1col < o2col) {
                    return -1;
                } else if (o1col == o2col) {
                    if (o1row < o2row) {
                        return -1;
                    } else if (o1row > o2row) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 1;
                }
            }
        });
        dfs(root, "0,0");
        // System.out.println(hashMap);

        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> temp = new TreeMap<>();
        for (Map.Entry<String, List<Integer>> entry : hashMap.entrySet()) {
            String coord = entry.getKey();
            List<Integer> list = entry.getValue();
            Collections.sort(list);

//            // 打印
//            System.out.print(coord + ":");
//            for (int i = 0; i < list.size(); i++) {
//                System.out.print(list.get(i) + " ");
//            }
//            System.out.println();

            // 先按照列从小到大排序，然后按照行从小到大排序
            String[] getCoord = coord.split(",");
            int col = Integer.parseInt(getCoord[1]);

            List<Integer> t = temp.getOrDefault(col, new ArrayList<>());
            for (int i = 0; i < list.size(); i++) {
                t.add(list.get(i));
            }
            temp.put(col, t);
        }
        // System.out.println(temp);
        for (List<Integer> l : temp.values()) {
            ans.add(l);
        }
        return ans;
    }

    public void dfs(TreeNode root, String coord) {
        if (root == null) {
            return;
        }

        String[] getCoord = coord.split(",");
        int row = Integer.parseInt(getCoord[0]);
        int col = Integer.parseInt(getCoord[1]);
        List<Integer> list = hashMap.getOrDefault(coord, new ArrayList<>());
        list.add(root.val);
        hashMap.put(coord, list);

        dfs(root.left, (row + 1) + "," + (col - 1));
        dfs(root.right, (row + 1) + "," + (col + 1));
    }

    public TreeNode createTree(Integer[] nums) {
        List<TreeNode> nodes = new LinkedList<>();
        for (Integer num : nums) {
            if (num == null) {
                nodes.add(null);
            } else {
                nodes.add(new TreeNode(num));
            }
        }

        for (int i = 0; i < nodes.size() / 2; i++) {
            TreeNode root = nodes.get(i);
            root.left = nodes.get(i * 2 + 1);
            if (i * 2 + 2 < nodes.size()) {
                root.right = nodes.get(i * 2 + 2);
            }
        }

        return nodes.get(0);
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        Integer[] nums = {3, 9, 20, null, null, 15, 7};
        Integer[] nums2 = {1, 2, 3, 4, 6, 5, 7};
        Integer[] nums3 = {3, 1, 4, 0, 2, 2};

        Solution solut = new Solution();
        TreeNode root = solut.createTree(nums2);
        // solut.preOrder(root);
        System.out.println(solut.verticalTraversal(root));
    }
}
