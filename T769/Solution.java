package T769;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        // 栈中存放的是索引
        stack.addLast(0);

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[stack.peekLast()] < arr[i]) {
                stack.addLast(i);
                continue;
            }
            int max = stack.peekLast();
            while (!stack.isEmpty() && arr[stack.peekLast()] > arr[i]) {
                stack.removeLast();
            }
            stack.addLast(max);
        }
        // System.out.println(stack);
        return stack.size();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1, 0};
        int[] arr2 = {1, 0, 2, 3, 4};
        int[] arr3 = {1, 2, 0, 3};
        int[] arr4 = {0, 2, 1};

        Solution solut = new Solution();
        System.out.println(solut.maxChunksToSorted(arr3));
    }
}
