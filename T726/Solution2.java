package T726;

import java.util.*;

public class Solution2 {
    private int i, n;
    private String formula;

    public String countOfAtoms(String formula) {
        this.i = 0;
        this.n = formula.length();
        this.formula = formula;

        Deque<Map<String, Integer>> stack = new LinkedList<>();
        stack.push(new HashMap<String, Integer>());

        while (i < n) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                i++;
                // 将一个空的哈希表压入栈中，准备统计括号内的原子数量
                stack.push(new HashMap<String, Integer>());
            } else if (ch == ')') {
                i++;
                // 括号右侧数字
                int num = parseNum();
                // 弹出括号内的原子数量
                Map<String, Integer> popMap = stack.pop();
                // topMap是popMap上一级括号的map
                Map<String, Integer> topMap = stack.peek();

                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    // 将括号内的原子数量乘上 num，加到上一层的原子数量中
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num);
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                // 统计原子数量
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
            }
        }

        Map<String, Integer> map = stack.pop();
        TreeMap<String, Integer> treeMap = new TreeMap<>(map);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            int count = entry.getValue();
            sb.append(atom);
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    public String parseAtom() {
        StringBuffer sb = new StringBuffer();
        // 首字母
        sb.append(formula.charAt(i++));
        while (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }

    public int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            // 不是数字，视作1
            return 1;
        }
        int num = 0;
        while (i < n && Character.isDigit(formula.charAt(i))) {
            num = num * 10 + formula.charAt(i++) - '0';
        }
        return num;
    }

    public static void main(String[] args) {
        String formula = "H2O2";
        String formula2 = "Mg(OH)2";
        String formula3 = "K4(ON(SO3)2)2";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.countOfAtoms(formula2));
    }
}
