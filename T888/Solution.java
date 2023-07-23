package T888;

import java.util.Arrays;
import java.util.HashSet;

// 哈希表
public class Solution {
    // sumA - x + y == sumB + x - y
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] ans = new int[2];

        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            hashSet.add(A[i]);
        }

        for (int i = 0; i < B.length; i++) {
            int x = B[i] + (sumA - sumB) / 2;
            if (hashSet.contains(x)) {
                ans[0] = x;
                ans[1] = B[i];
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] A = {1, 1};
        int[] B = {2, 2};

        int[] A2 = {1, 2};
        int[] B2 = {2, 3};

        int[] A3 = {2};
        int[] B3 = {1, 3};

        int[] A4 = {1, 2, 5};
        int[] B4 = {2, 4};

        Solution solut = new Solution();
        int[] ans = solut.fairCandySwap(A4, B4);
        for (int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
