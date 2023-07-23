package T842;

import java.util.ArrayList;
import java.util.List;

// 官方解答
public class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<Integer>();
        backtrack(list, S, S.length(), 0, 0, 0);
        return list;
    }

    // 回溯法
    public boolean backtrack(List<Integer> list, String S, int length, int index, int sum, int prev) {
        // index是遍历S的每个数字字符的索引，length是字符串S的长度
        if (index == length) {
            return list.size() >= 3;
        }
        // 当前数字的长度
        long currLong = 0;
        for (int i = index; i < length; i++) {
            // i>index的情况是这个数字至少是十位数，并且不能以0开头
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            currLong = currLong * 10 + S.charAt(i) - '0';
            // 2 ** 31 - 1
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) currLong;
            if (list.size() >= 2) {
                // 如果当前分割出来的数字小于前两个数字之和，那么还有往后分割的可能
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;  // 如果当前数字已经大于前两个数字之和，那么已经不可能了
                }
            }
            list.add(curr);
            // 递归调用
            if (backtrack(list, S, length, i + 1, prev + curr, curr)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String S = "123456579";

        Solution solut = new Solution();
        List<Integer> list = solut.splitIntoFibonacci(S);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}

// https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/solution/javahui-su-suan-fa-tu-wen-xiang-jie-ji-b-vg5z/
