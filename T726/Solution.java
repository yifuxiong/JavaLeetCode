package T726;

public class Solution {
    public String countOfAtoms(String formula) {
        // 第一步：添加1
        StringBuffer sb = new StringBuffer();
        // 添加数字
        for (int i = 0; i < formula.length(); i++) {
            if (i + 1 < formula.length()) {
                if (formula.charAt(i) >= 'A' && formula.charAt(i) <= 'Z' || formula.charAt(i) >= 'a' && formula.charAt(i) <= 'z') {
                    // 如果当前字符是字母
                    sb.append(formula.charAt(i));
                    if (formula.charAt(i + 1) >= 'A' && formula.charAt(i + 1) <= 'Z' || formula.charAt(i + 1) == '(') {
                        sb.append(1);
                    }
                } else if (formula.charAt(i) >= '1' && formula.charAt(i) <= '9') {
                    sb.append(formula.charAt(i));
                } else {
                    sb.append(formula.charAt(i));
                }
            }
        }

        if (formula.charAt(formula.length() - 1) > '0' && formula.charAt(formula.length() - 1) <= '9') {
            sb.append(formula.charAt(formula.length() - 1));
        } else {
            sb.append(1);
        }

        // 第二步：逆序遍历，乘数字，去括号


        return sb.toString();
    }

    public static void main(String[] args) {
        String formula = "H2O2";
        String formula2 = "Mg(OH)2";
        String formula3 = "K4(ON(SO3)2)2";

        Solution solut = new Solution();
        System.out.println(solut.countOfAtoms(formula3));
    }
}
