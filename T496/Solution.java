package T496;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peekLast() < num) {
                hashMap.put(stack.pop(), num);
            }
            stack.addLast(num);
        }
        System.out.println(stack);
        System.out.println(hashMap);

        int n = nums1.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = hashMap.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
        int[] nums12 = {2, 4}, nums22 = {1, 2, 3, 4};
        int[] nums13 = {1, 3, 5, 2, 4}, nums23 = {6, 5, 4, 3, 2, 1, 7};

        Solution solut = new Solution();
        int[] ans = solut.nextGreaterElement(nums12, nums22);
        for (int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
