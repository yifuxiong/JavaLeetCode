package T630;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public int scheduleCourse(int[][] courses) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            } else {
                if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for (int[] course : courses) {
            if (course[1] >= course[0]) {
                queue.offer(course);
            }
        }

        int ans = 0;
        long time = 0;
        int[] last = new int[2];
        while (!queue.isEmpty()) {
            int[] course = queue.poll();
            // System.out.println(course[0] + "," + course[1]);

            if (time + course[0] <= course[1]) {
                time += course[0];
                ans += 1;
                last = course;
            } else {
                // 用当前课程替换掉上一个课程
                if (time - last[0] + course[0] <= course[1]) {
                    time = time - last[0] + course[0];
                    last = course;
                }
            }
        }
        return ans;
    }

    // 官方：贪心 + 优先队列
    public int scheduleCourse2(int[][] courses) {
        // 结课日期按照从早到晚排列
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // 课程时长按照从大到小排列
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);

        // 优先队列中所有课程的总时间
        int total = 0;
        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                // 下面这句相当于total = total - q.poll() + ti;
                // 把课程时长最长的拿出来，选择当前的课程
                total -= (q.poll() - ti);
                q.offer(ti);
            }
        }
        return q.size();
    }

    public static void main(String[] args) {
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        int[][] courses2 = {{100, 200}, {300, 1300}, {1000, 1250}, {1200, 2200}};
        int[][] courses3 = {{5, 15}, {3, 19}, {6, 7}, {2, 10}, {5, 16}, {8, 14}, {10, 11}, {2, 19}};

        Solution solut = new Solution();
        System.out.println(solut.scheduleCourse(courses3));
        System.out.println(solut.scheduleCourse2(courses3));
    }
}
