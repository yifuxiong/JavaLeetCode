package T386;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 官方递推
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        int i = 0;
        while (i < n) {
            ans.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                // 如果是相同前缀的最后一个数字
                // 或者 当前数字越界
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num += 1;
            }
            i++;
        }
        return ans;
    }

    // 递归
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> ans = new ArrayList<>();
        dfs(0, n, ans);
        return ans;
    }

    public void dfs(int index, int n, List<Integer> ans){
        if (index > n){
            return;
        }
        if (index > 0){
            ans.add(index);
        }

        index *= 10;
        for (int i = (index == 0?1:0); i < 10; i++){
            dfs(index + i, n, ans);
        }
    }

    public static void main(String[] args) {
        int n = 13;

        Solution solut = new Solution();
        System.out.println(solut.lexicalOrder(n));
        System.out.println(solut.lexicalOrder2(n));
    }
}
