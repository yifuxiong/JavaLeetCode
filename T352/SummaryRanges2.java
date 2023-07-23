package T352;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 暴力法
public class SummaryRanges2 {
    List<Integer> list;

    public SummaryRanges2() {
        list = new ArrayList<>();
    }

    public void addNum(int val) {
        list.add(val);
    }

    public int[][] getIntervals() {
        if (list.size() == 1) {
            return new int[][]{{list.get(0), list.get(0)}};
        }

        Collections.sort(list);
        List<int[]> values = new ArrayList<>();
        int start = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - list.get(i - 1) > 1) {
                values.add(new int[]{start, list.get(i - 1)});
                start = list.get(i);
            }
        }
        values.add(new int[]{start, list.get(list.size() - 1)});
        return values.toArray(new int[values.size()][]);
    }
}
