package T421;

import java.util.Arrays;

// 宫水三月：字典树
public class Solution {
    // static成员整个类独一份，只有在类首次加载时才会创建，因此只会被new一次
    static int N = (int) 1e6;
    static int[][] trie = new int[N][2];
    static int idx = 0;

    // 每跑一个数据，会被实例化一次，每次实例化的时候被调用，做清理工作
    public Solution() {
        for (int i = 0; i <= idx; i++) {
            Arrays.fill(trie[i], 0);  // 左右都为0
        }
        idx = 0;
    }

    void add(int x) {
        int p = 0;
        for (int i = 31; i >= 0; i--) {
            int u = (x >> i) & 1;  // 遍历32位二进制的每一位，u = 0 or 1
            if (trie[p][u] == 0) {
                trie[p][u] = ++idx;
            }
            p = trie[p][u];
        }
    }

    int getVal(int x) {
        int ans = 0;
        int p = 0;
        for (int i = 31; i >= 0; i--) {
            int a = (x >> i) & 1;  // a = 0 or 1
            int b = 1 - a;  // b = 1 or 0

            // 看两边哪边不为0往哪边走
            if (trie[p][b] != 0) {
                ans |= (b << i);  // 从第31位开始一直到0
                p = trie[p][b];  // p = 1, 2, ..., 31
            } else {
                ans |= (a << i);
                p = trie[p][a];
            }
        }
        // ans是32位二进制的格式，再转int
        System.out.println("ans=" + ans);
        return ans;
    }

    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            add(i);
            int j = getVal(i);

            System.out.println("i=" + i + " j=" + j);
            System.out.println(i ^ j);

            ans = Math.max(ans, i ^ j);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        int[] nums2 = {0};
        int[] nums3 = {2, 4};
        int[] nums4 = {8, 10, 2};
        int[] nums5 = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};

        Solution solut = new Solution();
        System.out.println(solut.findMaximumXOR(nums));
    }
}
