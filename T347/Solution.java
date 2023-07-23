package T347;

// 两种方法：1.桶排序，2.堆排序
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] ret = new int[k];




        return ret;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] nums2 = {1};
        int k2 = 1;

        Solution solut = new Solution();
        int[] ret = solut.topKFrequent(nums, k);
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
    }
}
