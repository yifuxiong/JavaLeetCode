package BackTrace;

import java.util.ArrayList;
import java.util.List;

public class T282 {
    int n;
    String num;
    int target;
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        this.n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new ArrayList<>();

        StringBuffer expr = new StringBuffer();
        backtrack(expr, 0, 0, 0);
        return ans;
    }

    public void backtrack(StringBuffer expr, int i, long res, long mul) {
        // expr 为当前构建出的表达式；
        // i 表示当前的枚举到了 num 的第 i 个数字；
        // res 为当前表达式的计算结果；
        // mul 为表达式最后一个连乘串的计算结果。
        if (i == n) {
            if (res == target) {
                ans.add(expr.toString());
            }
            return;
        }

        int signIndex = expr.length();
        if (i > 0) {
            expr.append(0);  // 占位，下面填充符号
        }
        long val = 0;
        // 枚举截取的数字长度（取多少位），注意数字可以是单个0但不能有前导0
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); ++j) {
            val = val * 10 + num.charAt(j) - '0';
            expr.append(num.charAt(j));

            if (i == 0) {
                // 表达式开头不能添加符号
                backtrack(expr, j + 1, val, val);
            } else {
                // 枚举符号
                expr.setCharAt(signIndex, '+');
                backtrack(expr, j + 1, res + val, val);
                expr.setCharAt(signIndex, '-');
                backtrack(expr, j + 1, res - val, -val);
                expr.setCharAt(signIndex, '*');
                backtrack(expr, j + 1, res - mul + mul * val, mul * val);
            }
        }
        expr.setLength(signIndex);
    }
}
