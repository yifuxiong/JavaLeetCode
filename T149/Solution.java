package T149;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) {
            // n == 1 or n == 2
            return n;
        }

        // 最终返回结果
        int ans = 0;

        for (int i = 0; i < n; i++) {
            // 斜率，点的个数
            Map<Double, Integer> hashMap = new HashMap<>();
            // 第一个点
            int[] a = points[i];
            for (int j = i + 1; j < n; j++) {
                // 第二个点
                int[] b = points[j];

                // deltaX和deltaY
                double xDiff = b[0] - a[0];
                double yDiff = b[1] - a[1];

                // 斜率
                double lineK;
                if (yDiff == 0){
                    lineK = 0;
                }else{
                    if (xDiff != 0) {
                        lineK = yDiff / xDiff;
                    } else {
                        lineK = (double) Integer.MAX_VALUE;
                    }
                }

                hashMap.put(lineK, hashMap.getOrDefault(lineK, 1) + 1);
            }
            // System.out.println(hashMap);

            for (int v : hashMap.values()) {
                ans = Math.max(ans, v);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        int[][] points2 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] points3 = {{0, 0}, {4, 5}, {7, 8}, {8, 9}, {5, 6}, {3, 4}, {1, 1}};
        int[][] points4 = {{2, 3}, {3, 3}, {-5, 3}};

        Solution solut = new Solution();
        System.out.println(solut.maxPoints(points));
    }
}
