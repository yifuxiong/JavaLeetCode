package T1004;

public class Solution {
    // 超时，不过代码是对的
    public int longestOnes(int[] A, int K) {
        int n = A.length;

        int maxLen = 0;
        int left = 0, right = 0, k = K;
        while (right < n) {
            if (A[right] == 1) {
                right++;
            } else {
                // 如果还可以填充1，那么right继续向右
                if (k > 0) {
                    k--;
                    right++;
                } else {  // 如果填充1的次数用完了
                    // System.out.println("left=" + left + ", right=" + right);
                    maxLen = Math.max(maxLen, right - left);
                    left++;
                    right = left;
                    k = K;
                }
            }
        }
        maxLen = Math.max(maxLen, right - left);
        return maxLen;
    }

    public static void main(String[] args) {
        int[] A = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int K = 2;

        int[] A2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int K2 = 3;

        int[] A3 = {0, 0, 0, 1};
        int K3 = 4;

        Solution solut = new Solution();
        System.out.println(solut.longestOnes(A, K));
    }
}
