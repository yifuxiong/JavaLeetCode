package T447;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int dis = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                    hashMap.put(dis, hashMap.getOrDefault(dis, 0) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> distance : hashMap.entrySet()) {
                int disCount = distance.getValue();
                ans += disCount * (disCount - 1);
            }
            hashMap.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        int[][] points2 = {{1, 1}, {2, 2}, {3, 3}};
        int[][] points3 = {{1, 1}};
        int[][] points4 = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Solution solut = new Solution();
        System.out.println(solut.numberOfBoomerangs(points4));
    }
}
