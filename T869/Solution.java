package T869;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 打表
    static Set<Integer> hashSet = new HashSet<>();

    static {
        for (int i = 1; i < (int) 1e9 + 10; i *= 2) {
            hashSet.add(i);
        }
    }

    public boolean reorderedPowerOf2(int n) {
        int level = 0;
        int[] cnt = new int[10];
        while (n != 0) {
            cnt[n % 10]++;
            n /= 10;
            level++;
        }
        return backtrack(cnt, 0, level, 0);
    }

    public boolean backtrack(int[] cnt, int curLevel, int level, int curr) {
        System.out.println(curr);
        if (curr != 0 && curLevel == level) {
            return hashSet.contains(curr);
        }

        for (int i = 0; i < 10; i++) {
            // 剪枝
            if (curr == 0 && i == 0) {
                // 上一次的curr==0
                // 这一次curr*10+i还是等于0
                // 避免重复计算
                continue;
            }

            if (cnt[i] != 0) {
                cnt[i]--;
                curr = curr * 10 + i;
                if (backtrack(cnt, curLevel + 1, level, curr)) {
                    return true;
                }
                curr /= 10;
                cnt[i]++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 256;

        Solution solut = new Solution();
        System.out.println(solut.reorderedPowerOf2(n));
    }
}
