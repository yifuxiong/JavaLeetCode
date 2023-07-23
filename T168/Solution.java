package T168;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String convertToTitle(int columnNumber) {
        List<Integer> list = new ArrayList<>();

        int n = columnNumber, yu = 0;
        while (n > 26) {
            yu = n % 26;
            n /= 26;
            if (yu == 0) {
                list.add(26);
                n -= 1;
            } else {
                list.add(yu);
            }
        }
        list.add(n);
        // 打印list看看，注意这里是从后往前看
        // System.out.println(list);

        StringBuffer sb = new StringBuffer();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append((char)(list.get(i) + 64));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int columnNumber = 260;

        Solution solut = new Solution();
        System.out.println(solut.convertToTitle(columnNumber));
    }
}
