package T1711;

import java.util.*;

public class Solution {
    // 哈希表，还是超时了
    public int countPairs(int[] deliciousness) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int d : deliciousness) {
            if (!list.contains(d)) {
                list.add(d);
            }
            hashMap.put(d, hashMap.getOrDefault(d, 0) + 1);
        }
        // System.out.println(list);
        // System.out.println(hashMap);

        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            int aNum = hashMap.get(a);
            for (int j = i; j < list.size(); j++) {
                int b = list.get(j);
                int bNum = hashMap.get(b);
                if (a == b && aNum == 1){
                    // 如果当前数字数量为1，表示a取了该数字之后，b应该取不到
                    continue;
                }
                int count = a + b;
                if (count != 0 && (count & (count - 1)) == 0) {
                    if (a == b) {
                        // aNum > 1。求组合问题C(aNum)(2)
                        int num = (aNum * (aNum - 1)) / 2;
                        ans = (ans + num) % (int) (10e9 + 7);
                    } else {
                        ans = (ans + aNum * bNum) % (int) (10e9 + 7);
                    }
                }
            }
        }
        return ans;
    }

    // 暴力法
    public int countPairs2(int[] deliciousness) {
        int ans = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            int a = deliciousness[i];
            for (int j = i + 1; j < deliciousness.length; j++) {
                int b = deliciousness[j];
                int sum = a + b;
                if (sum != 0 && (sum & (sum - 1)) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] deliciousness = {1, 3, 5, 7, 9};
        int[] deliciousness2 = {1, 1, 1, 3, 3, 3, 7};
        int[] deliciousness3 = {1, 1, 3, 7, 15, 31, 63, 127, 255, 511};
        int[] deliciousness4 = {1, 1, 3};
        int[] deliciousness5 = {64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64};

        Solution solut = new Solution();
        System.out.println(solut.countPairs(deliciousness2));
        System.out.println(solut.countPairs2(deliciousness2));
    }
}
