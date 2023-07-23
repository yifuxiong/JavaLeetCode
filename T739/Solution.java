package T739;

import java.util.*;

public class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                hashMap.put(stack.pop(), i);
            }
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(hashMap);

        for (int i = 0; i < n; i++) {
            if (hashMap.containsKey(i)) {
                ans[i] = hashMap.get(i) - i;
            } else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] T2 = {30, 40, 50, 60};
        int[] T3 = {30, 60, 90};
        int[] T4 = {89, 62, 70, 58, 47, 47, 46, 76, 100, 70};

        Solution solut = new Solution();
        int[] ans = solut.dailyTemperatures(T4);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
