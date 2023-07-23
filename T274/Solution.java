package T274;

import java.util.Arrays;

public class Solution {
    // 投机取巧
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int ans = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (n - i > citations[i]) {
                ans = n - 1 - i;
                break;
            } else {
                ans = n - i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        int[] citations2 = {1, 3, 1};
        int[] citations3 = {11, 15};
        int[] citations4 = {1};

        Solution solut = new Solution();
        System.out.println(solut.hIndex(citations));
    }
}
