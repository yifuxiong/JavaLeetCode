package T1208;


public class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();

        // cost数组，存放每对开销
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
            // System.out.print(cost[i] + " ");
        }
        // System.out.println();

        int curCost = 0, maxLen = 0;
        int left = 0, right = 0;
        while (right < n) {
            if (curCost > maxCost || curCost + cost[right] > maxCost) {
                // 向右移动
                curCost -= cost[left];
                left++;
            }
            curCost += cost[right];
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String t = "bcdf";
        int cost = 3;

        String s2 = "abcd";
        String t2 = "cdef";
        int cost2 = 3;

        String s3 = "abcd";
        String t3 = "acde";
        int cost3 = 0;

        String s4 = "krrgw";
        String t4 = "zjxss";
        int cost4 = 19;

        Solution solut = new Solution();
        System.out.println(solut.equalSubstring(s4, t4, cost4));
    }
}
