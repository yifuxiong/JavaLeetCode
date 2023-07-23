package T738;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 我的思路，从右到左，从低位到高位
    public int monotoneIncreasingDigits(int N) {
        List<Integer> list = new ArrayList<>();
        while (N > 0) {
            list.add(N % 10);
            N = N / 10;
        }
        System.out.println("before: " + list);

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) <= 0 || list.get(i) < list.get(i + 1)) {
                list.set(i, 9);
                list.set(i + 1, list.get(i + 1) - 1);
            }
        }
        System.out.println("after: " + list);

        // 检查一遍，填充9
        for (int i = 0; i < list.lastIndexOf(9); i++) {
            list.set(i, 9);
        }
        System.out.println("checked: " + list);

        // 转换成int类型返回
        int ret = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            ret = ret * 10 + list.get(i);
        }
        return ret;
    }


    // 改进，从左到右
//    public int monotoneIncreasingDigits2(int N) {
//        List<Integer> list = new ArrayList<>();
//
//        while (N > 0) {
//            list.add(0, N % 10);
//            N = N / 10;
//        }
//        System.out.println(list);
//
//        int pos = 0;
//        for (int i = 0; i < list.size() - 1; i++) {
//            if (list.get(i) > list.get(i + 1)) {
//                list.set(i, list.get(i) - 1);
//                list.set(i + 1, 9);
//                pos = i;
//                break;
//            }
//        }
//        // 检查高位
//        if (pos != list.size() - 1) {
//            if (list.get(0) != 0) list.set(0, list.get(0) - 1);
//            for (int i = 1; i <= pos; i++) {
//                list.set(i, 9);
//            }
//        }
//        // 填充低位
//        for (int i = pos + 1; i < list.size(); i++) {
//            list.set(i, 9);
//        }
//        System.out.println(list);
//
//        // 再转换成int类型返回
//        int ret = 0;
//        for (int i = 0; i < list.size(); i++) {
//            ret = ret * 10 + list.get(i);
//        }
//        return ret;
//    }

    public static void main(String[] args) {
        int N = 855832;
        int N2 = 99765;
        int N3 = 1000;

        Solution solut = new Solution();
        // System.out.println(solut.monotoneIncreasingDigits(N));
        // System.out.println(solut.monotoneIncreasingDigits2(N3));
    }
}
