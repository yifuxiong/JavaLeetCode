package T187;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // 方法一：暴力法
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n < 10) {
            return ans;
        }

        Map<String, Integer> hashMap = new HashMap<>();
        int left = 0, right = left + 10;
        while (right <= n) {
            String str = s.substring(left, right);
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
            left++;
            right++;
        }
        // System.out.println(hashMap);

        for (String str : hashMap.keySet()) {
            if (hashMap.get(str) > 1) {
                ans.add(str);
            }
        }
        return ans;
    }

    // 方法二：字符串哈希 + 前缀和
    int N = (int) 1e5 + 10, P = 131313;
    int[] h = new int[N], p = new int[N];

    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i + 10 <= n + 1; i++) {
            int j = i + 10 - 1;
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            int cnt = map.getOrDefault(hash, 0);
            if (cnt == 1) {
                ans.add(s.substring(i - 1, i + 9));
            }
            map.put(hash, cnt + 1);
        }
        return ans;
    }

    // 方法三：哈希表 + 滑动窗口 + 位运算
    static final int L = 10;
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public List<String> findRepeatedDnaSequences3(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        // 最左边的9个字符的二进制结合起来

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            // System.out.println(x);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        String s2 = "AAAAAAAAAAAAA";
        String s3 = "AAAAAAAAAAA";

        Solution solut = new Solution();
//        List<String> ans = solut.findRepeatedDnaSequences(s3);
//        System.out.println(ans);
//
//        List<String> ans2 = solut.findRepeatedDnaSequences2(s3);
//        System.out.println(ans2);

        List<String> ans3 = solut.findRepeatedDnaSequences3(s);
        System.out.println(ans3);
    }
}
