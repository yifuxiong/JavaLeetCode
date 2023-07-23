package T80;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n != nums[i - 2]) {
                nums[i] = n;
                i++;
            }
        }
        return i;
    }

    // 双指针【负雪明烛】
    public int removeDuplicates2(int[] nums) {
        int slow = 0, fast = 0;

        while (fast < nums.length){
            if (slow - 2 >= 0 && nums[slow - 2] == nums[fast]){
                ;
            }else{
                // 不相等再赋值
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }



    public static void printNums(int[] nums){
        for (int num: nums){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 3, 3};

        Solution solut = new Solution();
        // System.out.println(solut.removeDuplicates(nums));
        System.out.println(solut.removeDuplicates2(nums2));
    }
}
