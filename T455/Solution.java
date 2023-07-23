package T455;

import java.util.Arrays;

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        // printNum(g);
        // printNum(s);

        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
            }else{
                j++;
            }
        }
        return i;
    }

    public static void printNum(int[] nums){
        for (int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] g = {1, 2, 3};
        int[] s = {1, 1};

        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};

        int[] g3 = {10, 9, 8, 7};
        int[] s3 = {8, 7, 6, 5};

        Solution solut = new Solution();
        System.out.println(solut.findContentChildren(g3, s3));
    }
}
