package T401;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();

        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m > 9 ? "" : "0") + m);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int turnedOn = 3;

        Solution solut = new Solution();
        System.out.println(solut.readBinaryWatch(turnedOn));
    }
}
