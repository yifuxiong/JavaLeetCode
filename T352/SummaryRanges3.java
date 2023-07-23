package T352;

import java.util.Arrays;

// 并查集
public class SummaryRanges3 {
    int[] nums;

    public SummaryRanges3() {
        nums = new int[10002];
    }

    public void addNum(int val) {
        if (nums[val] == 0) {
            nums[val] = val + 1;
        }
        find(val);
    }

    public int[][] getIntervals() {
        int[][] ans = new int[10001][2];
        int idx = 0;
        for (int i = 0; i < 10001; ) {
            if (nums[i] != 0) {
                ans[idx][0] = i;
                ans[idx][1] = find(nums[i]) - 1;
                i = ans[idx++][1] + 1;
            } else {
                i++;
            }
        }
        return Arrays.copyOfRange(ans, 0, idx);
    }

    private int find(int x) {
        if (nums[x] == 0) {
            return x;
        }
        return find(nums[x]);
    }
}
