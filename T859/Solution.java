package T859;

import java.util.*;

public class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int n = s.length();
        if (n == 1) {
            return false;
        }

        Set<Character> hashSet = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hashSet.add(s.charAt(i));
            if (s.charAt(i) != goal.charAt(i)) {
                list.add(i);
            }
        }
        // 如果s和goal相等，并且每个字符只有唯一一个
        if (s.equals(goal) && hashSet.size() == n) {
            return false;
        }
        if (list.size() == 0) {
            return true;
        }
        if (list.size() == 2 && s.charAt(list.get(0)) == goal.charAt(list.get(1)) && s.charAt(list.get(1)) == goal.charAt(list.get(0))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String goal = "dbac";
        Solution solut = new Solution();
        System.out.println(solut.buddyStrings(s, goal));
    }
}
