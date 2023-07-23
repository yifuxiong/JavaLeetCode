package T1713;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 和T1143一样的解法
    // 二维dp，内存超了
    public int minOperations(int[] target, int[] arr) {
        int m = arr.length, n = target.length;
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        // 行
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        // 列
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i - 1] == target[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return n - dp[m][n];
    }

    //
    public int minOperations2(int[] target, int[] arr) {
        int m = arr.length, n = target.length;
        // value, index
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetMap.put(target[i], i);
        }

        for (int i = 0; i < m; i++) {
            arr[i] = targetMap.getOrDefault(arr[i], -1);
        }

//        // 打印
//        for (int i = 0; i < m; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();

        // 类似T300
        int[] temp = new int[m + 1];
        int total = 0;  // 最长子串字符总数
        for (int i = 0; i < m; i++) {
            int num = arr[i];
            if (num == -1) {
                continue;
            }
            int left = 0, right = total;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (temp[mid] < num) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            temp[left + 1] = num;
            total = Math.max(left + 1, total);
        }

        return n - total;
    }

    public static void main(String[] args) {
        int[] target = {5, 1, 3}, arr = {9, 4, 2, 3, 4};  // 2
        int[] target2 = {6, 4, 8, 1, 3, 2}, arr2 = {4, 7, 6, 2, 3, 8, 6, 1};  // 3

        Solution solut = new Solution();
        System.out.println(solut.minOperations(target2, arr2));
        System.out.println(solut.minOperations2(target2, arr2));
    }
}
