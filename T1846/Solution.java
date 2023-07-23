package T1846;

import java.util.Arrays;

public class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        if (n == 1){
            return 1;
        }
        Arrays.sort(arr);

        arr[0] = 1;
        for (int i = 1; i < n; i++){
            if (arr[i] > arr[i-1] + 1){
                arr[i] = arr[i-1] + 1;
            }
        }
        return arr[n-1];
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 2, 1};  // 2
        int[] arr2 = {100, 1, 1000};  // 3
        int[] arr3 = {1, 2, 3, 4, 5};  // 5
        int[] arr4 = {1, 1, 1, 1, 1, 1, 5};  // 2

        Solution solut = new Solution();
        System.out.println(solut.maximumElementAfterDecrementingAndRearranging(arr4));
    }

}
