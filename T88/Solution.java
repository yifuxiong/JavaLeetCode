package T88;


public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m + n];

        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                tmp[k++] = nums2[j++];
            } else {
                tmp[k++] = nums1[i++];
                tmp[k++] = nums2[j++];
            }
        }
        while (i < m) {
            tmp[k++] = nums1[i++];
        }
        while (j < n) {
            tmp[k++] = nums2[j++];
        }

        for (int t = 0; t < (m + n); t++) {
            nums1[t] = tmp[t];
        }
        // printNums(nums1);
    }

    public static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 0};
        int m = 1;
        int[] nums2 = {1};
        int n = 1;

        Solution solut = new Solution();
        solut.merge(nums1, m, nums2, n);
    }
}
