package T825;

import java.util.Arrays;

public class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0;
        int left = 0, right = 0;
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * ages[i] + 7) {
                left++;
            }
            while (right + 1 < ages.length && ages[right + 1] <= ages[i]) {
                right++;
            }
            ans += (right - left);
        }
        return ans;
    }

    // 计数排序 + 前缀和
    public int numFriendRequests2(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            cnt[age]++;
        }

        // 前缀和
        int[] sum = new int[121];
        for (int i = 1; i < 121; i++) {
            sum[i] = sum[i - 1] + cnt[i];
        }

        int ans = 0;
        for (int i = 15; i < 121; i++) {
            if (cnt[i] != 0) {  // cnt[i]为年龄为i的人
                int bound = (int) (0.5 * i) + 8;
                ans += cnt[i] * (sum[i] - sum[bound - 1] - 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ages = {16, 16};
        int[] ages2 = {16, 17, 18};
        int[] ages3 = {20, 30, 100, 110, 120};

        Solution solut = new Solution();
        System.out.println(solut.numFriendRequests(ages3));
        System.out.println(solut.numFriendRequests2(ages3));
    }
}
