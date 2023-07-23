package T852;

public class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 找出峰值在左半边还是右半边
            if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                // 在右半边
                left = mid + 1;
            } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {
                // 在左半边
                right = mid;
            }else{
                // 刚好就是峰值
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0};
        int[] arr2 = {0, 2, 1, 0};
        int[] arr3 = {0, 10, 5, 2};
        int[] arr4 = {3, 4, 5, 1};
        int[] arr5 = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};

        Solution solut = new Solution();
        System.out.println(solut.peakIndexInMountainArray(arr5));
    }
}
