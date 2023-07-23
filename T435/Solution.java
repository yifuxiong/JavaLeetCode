package T435;

import java.util.Arrays;

public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        printNums(intervals);

        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            // 如果每个区间的左端大于right
            if (intervals[i][0] >= right) {
                ans++;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }

    public void printNums(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[0].length; j++) {
                System.out.print(intervals[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        int[][] intervals3 = {{1, 2}, {2, 3}};

        Solution solut = new Solution();
        System.out.println(solut.eraseOverlapIntervals(intervals));
    }
}
