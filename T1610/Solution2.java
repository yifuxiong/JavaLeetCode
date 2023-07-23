package T1610;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution2 {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int x = location.get(0), y = location.get(1);
        List<Double> list = new ArrayList<>();
        int cnt = 0;
        double pi = Math.PI, t = angle * pi / 180;

        for (List<Integer> p : points) {
            int a = p.get(0), b = p.get(1);
            if (a == x && b == y && ++cnt >= 0) continue;
            list.add(Math.atan2(b - y, a - x) + pi);
        }
        // 按角度排序
        Collections.sort(list);

        int n = list.size(), max = 0;
        for (int i = 0; i < n; i++) {
            list.add(list.get(i) + 2 * pi);
        }

        for (int i = 0, j = 0; j < 2 * n; j++) {
            while (i < j && list.get(j) - list.get(i) > t) {
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return cnt + max;
    }

    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        List<Integer> point1 = new ArrayList<>();
        point1.add(2);
        point1.add(1);
        points.add(point1);
        List<Integer> point2 = new ArrayList<>();
        point2.add(2);
        point2.add(2);
        points.add(point2);
        List<Integer> point3 = new ArrayList<>();
        point3.add(3);
        point3.add(3);
        points.add(point3);
//        List<Integer> point4 = new ArrayList<>();
//        point4.add(0);
//        point4.add(0);
//        points.add(point4);

        int angle = 90;
        List<Integer> location = new ArrayList<>();
        location.add(1);
        location.add(1);

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.visiblePoints(points, angle, location));
    }
}
