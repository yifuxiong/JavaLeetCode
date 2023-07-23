package T421;

class Trie {
    // left表示0的子节点
    Trie left = null;
    // right表示1的子节点
    Trie right = null;
}

// 官方题解：
public class Solution2 {
    // 字典树
    Trie root = new Trie();
    // 最高位的二进制编号为30
    static final int HIGH_BIT = 30;

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int x = 0;
        for (int i = 1; i < n; i++) {
            // 将nums[i-1]放入字典树，此时nums[0 ... i-1]都在字典树中
            add(nums[i - 1]);
            // 将nums[i]看做a_i，找出最大的x更新答案
            x = Math.max(x, check(nums[i]));
        }
        return x;
    }

    // 把每个nums[i-1]放入字典树
    public void add(int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    // 遍历a_i，找出最大值
    public int check(int num) {
        Trie cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                // a_i的第k个二进制位为0，应当往表示1的子节点right走
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // a_i的第k个二进制位为1，应当往表示0的子节点left走
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
        int[] nums = {3, 10, 5, 25, 2, 8};
        int[] nums2 = {0};
        int[] nums3 = {2, 4};
        int[] nums4 = {8, 10, 2};
        int[] nums5 = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.findMaximumXOR(nums));
    }
}
