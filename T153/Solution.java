package T153;

public class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int left = 0, right = n - 1;
        int mid;
        int minValue = Math.min(nums[left], nums[right]);

        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < minValue) {
                minValue = nums[mid];
            }

            if (nums[left] > nums[mid]) {
                // 左非升序段
                right = mid - 1;
            } else if (nums[mid] > nums[right]) {
                // 右非升序段
                left = mid + 1;
            } else if (nums[left] <= nums[right]) {
                // 升序
                right--;
            }
        }
        return minValue;
    }

    // 官方
    public int findMin2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                // 这里只能是右边界，因为有顺序序列[1, 2, 3, 4]这种，找最小值
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums3 = {11, 13, 15, 17};
        int[] nums4 = {2, 1};

        Solution solut = new Solution();
        System.out.println(solut.findMin(nums3));
        System.out.println(solut.findMin2(nums3));
    }
}
