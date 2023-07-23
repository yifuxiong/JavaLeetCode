package T1610;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int[] loc = new int[2];
        loc[0] = location.get(0);
        loc[1] = location.get(1);

        int cnt = 0;
        List<Double> polars = new ArrayList<>();
        for (List<Integer> point : points) {
            int x = point.get(0), y = point.get(1);
            if (x == loc[0] && y == loc[1] && ++cnt >= 0) {
                continue;
            }
            double polar = descartesToPolar(loc, x, y);
            // System.out.println(polar);
            polars.add(polar);
        }

        Collections.sort(polars);
        int n = polars.size();
        // polar数组头接尾
        for (int i = 0; i < n; i++) {
            polars.add(polars.get(i) + 360);
        }
        // 开始滑动
        int maxNum = 0;
        for (int i = 0, j = 0; j < 2 * n; j++) {
            while (i < j && polars.get(j) - polars.get(i) > angle) {
                i++;
            }
            maxNum = Math.max(maxNum, j - i + 1);
        }

        return maxNum + cnt;
    }


    /**
     * 笛卡尔坐标转换成极坐标：
     * 1.如果以坐标原点为基准
     * y = role * sin(theta)
     * x = role * cos(theta)
     * role^2 = x^2 + y^2
     * theta = arctan(y/x)
     * <p>
     * 2.如果以(1,1)为基准
     * y - 1 = role * sin(theta)
     * x - 1 = role * cos(theta)
     * role^2 = (x-1)^2 + (y-1)^2
     * theta = arcsin((y-1)/role)
     * 注意：arctan(1/2)不能计算出来，所以这里用arcsin计算theta
     *
     * @param x
     * @param y
     * @return
     */
    public double descartesToPolar(int[] loc, int x, int y) {
        double[] polar = new double[2];
        if (x - loc[0] == 0) {
            if (y - loc[1] == 0) {
                polar[0] = 0;
                polar[1] = 0;
            } else if (y - loc[1] > 0) {
                polar[0] = 90;
                polar[1] = (y - loc[1]) * (y - loc[1]);
            } else {  // y - loc[1] < 0
                polar[0] = 270;
                polar[1] = (y - loc[1]) * (y - loc[1]);
            }
        } else if (x - loc[0] > 0) {
            if (y - loc[1] == 0) {
                polar[0] = 0;
                polar[1] = (x - loc[0]) * (x - loc[0]);
            } else if (y - loc[1] > 0) {
                polar[1] = (x - loc[0]) * (x - loc[0]) + (y - loc[1]) * (y - loc[1]);
                polar[0] = Math.toDegrees(Math.asin((y - loc[1]) / Math.sqrt(polar[1])));
            } else {  // y - loc[1] < 0
                polar[1] = (x - loc[0]) * (x - loc[0]) + (y - loc[1]) * (y - loc[1]);
                polar[0] = 360 + Math.toDegrees(Math.asin((y - loc[1]) / Math.sqrt(polar[1])));
            }
        } else {  // x - loc[0] < 0
            if (y - loc[1] == 0) {
                polar[0] = 180;
                polar[1] = (x - loc[0]) * (x - loc[0]);
            } else if (y - loc[1] > 0) {
                polar[1] = (x - loc[0]) * (x - loc[0]) + (y - loc[1]) * (y - loc[1]);
                polar[0] = 180 - Math.toDegrees(Math.asin((y - loc[1]) / Math.sqrt(polar[1])));
            } else {  // y - loc[1] < 0
                polar[1] = (x - loc[0]) * (x - loc[0]) + (y - loc[1]) * (y - loc[1]);
                polar[0] = 180 - Math.toDegrees(Math.asin((y - loc[1]) / Math.sqrt(polar[1])));
            }
        }
        return polar[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        List<Integer> point1 = new ArrayList<>();
        point1.add(1);
        point1.add(0);
        points.add(point1);
        List<Integer> point2 = new ArrayList<>();
        point2.add(2);
        point2.add(1);
        points.add(point2);
//        List<Integer> point3 = new ArrayList<>();
//        point3.add(3);
//        point3.add(4);
//        points.add(point3);
//        List<Integer> point4 = new ArrayList<>();
//        point4.add(1);
//        point4.add(1);
//        points.add(point4);

        int angle = 13;
        List<Integer> location = new ArrayList<>();
        location.add(1);
        location.add(1);
        Solution solut = new Solution();
        System.out.println(solut.visiblePoints(points, angle, location));
    }
}
