package T769;

import java.util.ArrayDeque;
import java.util.Deque;

// 官方思路：
// 当遍历到第i个位置时，如果可以切分为块，那前i个位置的最大值一定等于i；
// 否则，一定有比i小的数划分到后面的块，那块排序后，一定不满足升序。
public class Solution2 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1, 0};
        int[] arr2 = {1, 0, 2, 3, 4};
        int[] arr3 = {1, 2, 0, 3};
        int[] arr4 = {0, 2, 1};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.maxChunksToSorted(arr3));
    }
}
