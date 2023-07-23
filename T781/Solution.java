package T781;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 我自己的思路
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : answers) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        System.out.println(map);

        int ans = 0;
        if (map.isEmpty()) {
            return 0;
        } else {
            for (Map.Entry<Integer, Integer> m : map.entrySet()) {
                int k = 0;
                if (m.getValue() <= (m.getKey() + 1)) {
                    k = m.getKey() + 1;
                } else {
                    if (m.getValue() % (m.getKey() + 1) == 0) {
                        k = m.getValue();
                    } else {
                        k = (m.getKey() + 1) * (m.getValue() / (m.getKey() + 1) + 1);
                    }
                }
                ans += k;
            }
        }
        return ans;
    }

    // 官方的解法（思路和我一模一样），写的代码不一样
    public int numRabbits2(int[] answers) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int y : answers) {
            count.put(y, count.getOrDefault(y, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int y = entry.getKey(), x = entry.getValue();
            ans += (x + y) / (y + 1) * (y + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] answer = {1, 1, 2};
        int[] answer2 = {10, 10, 10};
        int[] answer3 = {};
        int[] answer4 = {1, 0, 1, 0, 0};
        int[] answer5 = {0, 0, 1, 1, 1};

        Solution solut = new Solution();
        System.out.println(solut.numRabbits(answer5));
        System.out.println(solut.numRabbits2(answer5));
    }
}
