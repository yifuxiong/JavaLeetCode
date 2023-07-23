package T179;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] + "";
        }

        // 插入排序
        for (int i = 1; i < n; i++) {
            if (bigger(nums[i], nums[i - 1])) {
                int temp = nums[i];
                int k = i;
                for (int j = i - 1; j >= 0; j--) {
                    if (bigger(temp, nums[j])) {
                        nums[j + 1] = nums[j];
                    } else {
                        k = j + 1;
                        break;
                    }
                    if (j == 0) {
                        k = 0;
                    }
                }
                nums[k] = temp;
            }
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 生成字符串
        StringBuffer sb = new StringBuffer();
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    // 这个方法有个例子不同通过：[34323,3432]
    public boolean bigger(int x, int y) {
        // 从高位到低位，逐位比较
        Deque<Integer> queuea = new LinkedList<>();
        Deque<Integer> queueb = new LinkedList<>();

        int a = x, b = y;
        while (a > 0) {
            queuea.offer(a % 10);
            a /= 10;
        }
        while (b > 0) {
            queueb.offer(b % 10);
            b /= 10;
        }

        int aa = queuea.pollLast();
        int bb = queueb.pollLast();

        while (!queuea.isEmpty() && !queueb.isEmpty()) {
            if (aa == bb) {
                aa = queuea.pollLast();
                bb = queueb.pollLast();
            } else if (aa > bb) {
                return true;
            } else {
                return false;
            }
        }

        while (!queuea.isEmpty()) {
            if (aa == bb) {
                aa = queuea.pollLast();
            } else if (aa > bb) {
                return true;
            } else {
                return false;
            }
        }

        while (!queueb.isEmpty()) {
            if (aa == bb) {
                bb = queueb.pollLast();
            } else if (aa > bb) {
                return true;
            } else {
                return false;
            }
        }
        if (aa > bb) {
            return true;
        } else {
            return false;
        }
    }

    // 只能这样：先拼接再比较
    public String largestNumber2(int[] nums) {
        int n = nums.length;
        String[] strArr = new String[n];
        for (int i = 0; i < n; i++) {
            strArr[i] = "" + nums[i];
        }

        Arrays.sort(strArr, (a, b) -> {
            String ab = a + b, ba = b + a;
            return ba.compareTo(ab);
        });
        // 去掉首位的0
        if (strArr[0].equals("0")) {
            return "0";
        }

        StringBuilder ret = new StringBuilder();
        for (String str : strArr) {
            ret.append(str);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        int[] nums = {10, 2};
        int[] nums2 = {3, 30, 34, 5, 9};
        int[] nums3 = {0, 0, 10};
        int[] nums4 = {9, 3, 6};

        Solution solut = new Solution();
        System.out.println(solut.largestNumber2(nums3));
    }
}
