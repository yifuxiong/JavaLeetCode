package T1707;

import java.util.Arrays;

class Trie {
    Trie left = null;
    Trie right = null;
}

// 超时，53 / 67 个通过测试用例
public class Solution {
    private static final int HIGH_BIT = 30;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        Arrays.sort(nums);

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Trie root = new Trie();
            int x = 0;
            if (nums[0] > queries[i][1]) {
                ans[i] = -1;
            } else {
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] <= queries[i][1]) {
                        add(root, nums[j]);
                        x = Math.max(x, check(root, queries[i][0]));
                    }
                }
                ans[i] = x;
            }
        }
        return ans;
    }

    // 把每个nums[i]放入前缀树
    public void add(Trie root, int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                // 向左
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            } else {
                // 向右
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    // 将nums[i]在前缀树中遍历，得到最大值
    public int check(Trie root, int num) {
        Trie cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                // 由于bit = 0，说明nums第i为使0，0 XOR 1 == 1，要使值最大，应该向右走
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // bit = 1，说明nums第i位是1，1 XOR 0 == 1，要使结果最大，要往左走
                if (cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }
        }
        return x;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};

        int[] nums2 = {5, 2, 4, 6, 6, 3};
        int[][] queries2 = {{12, 4}, {8, 1}, {6, 3}};

        Solution solut = new Solution();
        int[] ans = solut.maximizeXor(nums2, queries2);
        for (int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
