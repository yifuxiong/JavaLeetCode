package T1104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new ArrayList<>();

        int row = 1;
        int n = 2;
        while (label > n - 1) {
            row++;
            n *= 2;
        }

        path.add(label);
        while (row > 1) {
            label = getReverse(row, label);
            label = label >> 1;
            path.add(label);
            row--;
        }

        Collections.reverse(path);
        return path;
    }

    public int getReverse(int row, int label){
        // row == 4, label == 14
        // (1 << row - 1) == 8
        // (1 << row) -1 == 15
        // return 8 + 15 - 14 == 9
        return (1 << row - 1) + (1 << row) - 1 - label;
    }


    public static void main(String[] args) {
        int label = 14;

        Solution solut = new Solution();
        System.out.println(solut.pathInZigZagTree(label));
    }
}
