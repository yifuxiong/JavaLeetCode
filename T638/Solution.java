package T638;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // 宫水三叶【完全背包】
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int[] g = new int[n + 1];
        g[0] = 1;
        for (int i = 1; i <= n; i++) {
            g[i] = g[i - 1] * (needs.get(i - 1) + 1);
        }

        int mask = g[n];
        int[] f = new int[mask];
        int[] cnt = new int[n];
        for (int state = 1; state < mask; state++) {
            f[state] = 0x3f3f3f3f;
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; i++) {
                cnt[i] = state % g[i + 1] / g[i];
            }
            for (int i = 0; i < n; i++) {
                if (cnt[i] > 0) {
                    f[state] = Math.min(f[state], f[state - g[i]] + price.get(i));
                }
            }
            out:
            for (List<Integer> x : special) {
                int cur = state;
                for (int i = 0; i < n; i++) {
                    if (cnt[i] < x.get(i)) {
                        continue out;
                    }
                    cur -= x.get(i) * g[i];
                }
                f[state] = Math.min(f[state], f[cur] + x.get(n));
            }
        }

        return f[mask - 1];
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>();
        price.addAll(Arrays.asList(2, 5));

        List<List<Integer>> special = new ArrayList<>();
        List<Integer> s1 = new ArrayList<>();
        s1.addAll(Arrays.asList(3, 0, 5));
        special.add(s1);
        List<Integer> s2 = new ArrayList<>();
        s2.addAll(Arrays.asList(1, 2, 10));
        special.add(s2);

        List<Integer> needs = new ArrayList<>();
        needs.addAll(Arrays.asList(3, 2));

        Solution solut = new Solution();
        System.out.println(solut.shoppingOffers(price, special, needs));
    }
}
