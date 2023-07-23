package LCP07传递信息;

import java.util.*;

public class Solution {
    private int ans = 0;

    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        for (int i = 0; i < relation.length; i++) {
            List<Integer> list = hashMap.getOrDefault(relation[i][0], new ArrayList<>());
            list.add(relation[i][1]);
            hashMap.put(relation[i][0], list);
        }
        System.out.println(hashMap);
        dfs(hashMap, 0, n - 1, k, 0);

        return ans;
    }

    public void dfs(Map<Integer, List<Integer>> hashMap, int start, int end, int k, int step) {
        if (step == k) {
            if (start == end) {
                ans++;
            }
            return;
        }
        List<Integer> list = hashMap.get(start);
        if (list == null) {
            return;
        }
        for (int next : list) {
            dfs(hashMap, next, end, k, step + 1);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        int k = 3;

        int n2 = 3;
        int[][] relation2 = {{0, 2}, {2, 1}};
        int k2 = 2;

        Solution solut = new Solution();
        System.out.println(solut.numWays(n2, relation2, k2));
    }
}
