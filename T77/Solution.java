package T77;

import java.util.*;

public class Solution {
    List<List<Integer>> ans;

    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        backTrack(1, n, k, new ArrayList<>());
        return ans;
    }

    public void backTrack(int start, int n, int k, List<Integer> tmp) {
        if (tmp.size() == k) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i <= n; i++) {
            tmp.add(i);
            start += 1;
            backTrack(start, n, k, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 2;

        Solution solut = new Solution();
        System.out.println(solut.combine(n, k));
    }
}
