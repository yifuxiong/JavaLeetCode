package 字符串超难题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // T187(字符串哈希 + 前缀和)
    // T686(字符串匹配)
    // T1044(T187的强化版，没有固定重复子串的长度)

    // 下面是T187的解法：【字符串哈希 + 前缀和】
    public List<String> findRepeatedDnaSequences(String s) {
        int N = (int) 1e5 + 10, P = 131313;
        // 与s等长的哈希数组h[]，次方数组p[]
        int[] h = new int[N], p = new int[N];

        // 由字符串预处理得到哈希数组h和次方数组p复杂度为O(n)。
        // 当我们需要计算子串s[i...j]的哈希值，只需要利用前缀和思想h[j]-h[i-1]*p[j-i+1]
        // 即可在O(1)的时间内得出哈希值（与子串长度无关）

        int n = s.length();
        List<String> ans = new ArrayList<>();

        // 构造哈希数组h[]和次方数组p[]
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);  // 这里直接获取s.charAt(i-1)的ASCII码值
            System.out.println(h[i]);
            p[i] = p[i - 1] * P;
        }

        Map<Integer, Integer> map = new HashMap<>();
        // 查找重复子串的长度，题目中已经固定为10
        for (int i = 1; i + 10 - 1 <= n; i++) {
            int j = i + 10 - 1;
            // 得到s[i...j]这一段字符串的哈希值
            int hash = h[j] - h[i - 1] * p[j - i + 1];

            int cnt = map.getOrDefault(hash, 0);
            if (cnt == 1) {
                ans.add(s.substring(i - 1, i + 10 - 1));
            }
            map.put(hash, cnt + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        String s2 = "AAAAAAAAAAAAAAAAAA";

        Solution solut = new Solution();
        System.out.println(solut.findRepeatedDnaSequences(s2));
    }
}
