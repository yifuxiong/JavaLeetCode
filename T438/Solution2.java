package T438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 一年后再做一遍
public class Solution2 {
    // 滑动窗口+数组
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (n < m){
            return ans;
        }

        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        // 先把第一段加进去
        for (int i = 0; i < m; i++){
            sCnt[s.charAt(i) - 'a']++;
            pCnt[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCnt, pCnt)){
            ans.add(0);
        }

        // 滑动窗口开始滑
        for (int i = m; i < n; i++){
            // 最左侧的字符去掉
            sCnt[s.charAt(i - m) - 'a']--;
            // 最右边的字符添加进来
            sCnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCnt, pCnt)){
                ans.add(i - m + 1);
            }
        }
        return ans;
    }

    // 滑动窗口+双指针（在java里面，双指针更快一点；python里面数组更快一点）
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (n < m){
            return ans;
        }

        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        // 先把第一段加进去
        for (int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a']++;
        }

        // 用双指针代替前一个方法中的数组
        int left = 0;
        for (int right = 0; right < n; right++){
            // 当前最右字符对应的索引
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;

            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft]--;
                left++;
            }

            if (right - left + 1 == m){
                ans.add(left);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.findAnagrams(s, p));
        System.out.println(solut2.findAnagrams2(s, p));
    }
}
