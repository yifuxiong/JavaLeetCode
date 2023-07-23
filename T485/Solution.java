package T485;

public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        int curLen = 0;
        for (int n : nums) {
            if (n == 1) {
                curLen++;
                if (curLen > maxLen){
                    maxLen = curLen;
                }
            }else{
                curLen = 0;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};

        Solution solut = new Solution();
        System.out.println(solut.findMaxConsecutiveOnes(nums));
    }
}
