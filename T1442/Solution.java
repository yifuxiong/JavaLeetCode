package T1442;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 2重循环
    public int countTriplets(int[] arr) {
        int count = 0;
        int n = arr.length;

        int[] table = new int[n + 1];
        // table[i]仅仅作为哨兵
        // table[1]才是arr[0], table[2]是arr[0]^arr[1], ...
        for (int i = 0; i < n; i++) {
            table[i + 1] = table[i] ^ arr[i];
        }

        /*
        * for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j; k < n; ++k) {
                    if (table[i] == table[k + 1]) {
                        ++count;
                    }
                }
            }
        }
        // 说到底j=i+1,说明此时i=j-1,step自然是1;
        // 但此时s不一定是j，可能是j+2...，用k来算step，就是k-i了。
        * */
        // 将上面的3层循环优化成2层
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (table[i] == table[k + 1]) {
                    count += (k - i);
                }
            }
        }
        return count;
    }

    // 1重循环：哈希表
    public int countTriplets2(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }

        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        Map<Integer, Integer> total = new HashMap<Integer, Integer>();

        int ans = 0;
        for (int k = 0; k < n; k++) {
            if (cnt.containsKey(s[k + 1])) {
                ans += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
            }
            cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
            total.put(s[k], total.getOrDefault(s[k], 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 7};
        int[] arr2 = {1, 1, 1, 1, 1};
        int[] arr3 = {2, 3};
        int[] arr4 = {1, 3, 5, 7, 9};
        int[] arr5 = {7, 11, 12, 9, 5, 2, 7, 17, 22};

        Solution solut = new Solution();
        System.out.println(solut.countTriplets(arr));
        System.out.println(solut.countTriplets2(arr));
    }
}
