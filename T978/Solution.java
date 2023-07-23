package T978;

public class Solution {
    // 找最大滑动窗口
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int left = 0, right = 0;
        int ret = 1;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right] < arr[right + 1] && arr[right] < arr[right - 1]) {
                    right++;
                } else if (arr[right] > arr[right + 1] && arr[right] > arr[right - 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        int[] arr2 = {4, 8, 12, 16};
        int[] arr3 = {100};
        int[] arr4 = {0, 1, 1, 0, 1, 0, 1, 1, 0, 0};

        Solution solut = new Solution();
        System.out.println(solut.maxTurbulenceSize(arr4));
    }
}
