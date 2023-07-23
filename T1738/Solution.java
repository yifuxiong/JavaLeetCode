package T1738;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i][j - 1] ^ pre[i - 1][j] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                result.add(pre[i][j]);
            }
        }

        // 排序
        Collections.sort(result, (a, b) -> b - a);
        return result.get(k - 1);
    }

    // 改进：排序那一段换成快速排序
    public int kthLargestValue2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i][j - 1] ^ pre[i - 1][j] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                result.add(pre[i][j]);
            }
        }

        // 快速排序
        nthElement(result, 0, k - 1, result.size() - 1);
        return result.get(k - 1);
    }

    public void nthElement(List<Integer> result, int left, int kth, int right) {
        if (left == right) {
            return;
        }
        // left和right之间的一个index
        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(result, pivot, right);

        // 三路划分
        int sepl = left - 1, sepr = left - 1;
        for (int i = left; i <= right; i++) {
            if (result.get(i) > result.get(right)) {
                swap(result, ++sepr, i);
                swap(result, ++sepl, sepr);
            } else if (result.get(i) == result.get(right)) {
                swap(result, ++sepr, i);
            }
        }

        if (sepl < left + kth && left + kth <= sepr) {
            return;
        } else if (left + kth <= sepl) {
            nthElement(result, left, kth, sepl);
        } else {
            nthElement(result, sepr + 1, kth - (sepr - left + 1), right);
        }
    }

    public void swap(List<Integer> result, int index1, int index2) {
        int temp = result.get(index1);
        result.set(index1, result.get(index2));
        result.set(index2, temp);
    }

    public static void main(String[] args) {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 3;

        Solution solut = new Solution();
        System.out.println(solut.kthLargestValue(matrix, k));
        System.out.println(solut.kthLargestValue2(matrix, k));
    }
}
