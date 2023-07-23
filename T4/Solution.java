package T4;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int i = 0, j = 0, k = 0;
        int[] cnums = new int[m + n];

        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                cnums[k++] = nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                cnums[k++] = nums2[j++];
            } else {
                cnums[k++] = nums1[i++];
                cnums[k++] = nums2[j++];
            }
        }
        while (i < n) {
            cnums[k++] = nums1[i++];
        }
        while (j < m) {
            cnums[k++] = nums2[j++];
        }

        if ((m + n) % 2 != 0) {
            return cnums[(m + n) / 2];
        } else {
            return ((double)cnums[(m + n) / 2 - 1] + (double)cnums[(m + n) / 2]) / 2;
        }
    }

    public static void printNums(int[] nums){
        for(int num: nums){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3}, nums2 = {2};
        int[] nums12 = {1, 2}, nums22 = {3, 4};
        int[] nums13 = {0, 0}, nums23 = {0, 0};
        int[] nums14 = {}, nums24 = {1};
        int[] nums15 = {2}, nums25 = {};

        Solution solut = new Solution();
        System.out.println(solut.findMedianSortedArrays(nums1, nums2));
    }
}
