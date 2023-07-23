package T697;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        /**
         * hashMap中的结构是：
         * Integer -> nums[i]
         * int[] -> int[0]: left, int[1]: right, int[2]: count
         */
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (hashMap.containsKey(nums[i])){
                hashMap.get(nums[i])[1] = i;  // right
                hashMap.get(nums[i])[2]++;  // count
            }else{
                hashMap.put(nums[i], new int[]{i, i, 1});  // left, right, count
            }
        }

        int ans = 0, maxCount = 0;
        for (Map.Entry<Integer, int[]> entry: hashMap.entrySet()){
            int[] arr = entry.getValue();
            if (maxCount < arr[2]){
                maxCount = arr[2];
                ans = arr[1] - arr[0] + 1;
            }else if (maxCount == arr[2] && ans > arr[1] - arr[0] + 1){
                // 如果有相同度的数，并且子数组长度较小，那就取这个较小的
                ans = arr[1] - arr[0] + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1};
        int[] nums2 = {1, 2, 2, 3, 1, 4, 2};

        Solution solut = new Solution();
        System.out.println(solut.findShortestSubArray(nums2));
    }
}
