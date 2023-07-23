package T1004;

public class Solution2 {
    // 通过是通过了，但是用时还是太多(从1801ms优化到264ms)
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
                    System.out.println("left=" + left + ", right=" + right);
                    maxLen = Math.max(maxLen, right - left);
                    while (A[left] == 1) {
                        left++;
                    }
                    left++;
                    k++;
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

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.longestOnes(A, K));
    }
}
