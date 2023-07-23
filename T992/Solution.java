package T992;

public class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * @param A
     * @param K
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        // 替代哈希set
        int[] freq = new int[len + 1];

        int left = 0, right = 0;
        // count是[left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            // 只出现一次，相当于哈希set
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            // 不同数字大于K
            while (count > K) {
                // 窗口最左侧的数字剔除
                freq[A[left]]--;
                // 因为踢掉了窗口最左侧的数字，不同数字个数减少了
                if (freq[A[left]] == 0) {
                    count--;
                }
                // 窗口左移
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 1, 2, 3};
        int K = 2;

        int[] A2 = {1, 2, 1, 3, 4};
        int K2 = 3;

        int[] A3 = {2, 1, 2, 1, 2};
        int K3 = 2;

        Solution solut = new Solution();
        System.out.println(solut.subarraysWithKDistinct(A2, K2));
    }
}
