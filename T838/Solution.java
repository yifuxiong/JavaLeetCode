package T838;

public class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        if (n == 1) {
            return dominoes;
        }
        // 左边和右边添加一个虚拟字符
        dominoes = "L" + dominoes + "R";
        StringBuffer sb = new StringBuffer();
        int left = 0;
        for (int right = 1; right < dominoes.length(); right++) {
            if (dominoes.charAt(right) == '.') {
                continue;
            }
            if (left != 0) {
                // 除了left和right之间的牌做调整之后放入sb
                // 从左到右，经过的左侧的牌也放入sb中
                sb.append(dominoes.charAt(left));
            }

            if (dominoes.charAt(left) == dominoes.charAt(right)) {
                for (int i = left + 1; i < right; i++) {
                    sb.append(dominoes.charAt(left));
                }
            } else if (dominoes.charAt(left) == 'L' && dominoes.charAt(right) == 'R') {
                for (int i = left + 1; i < right; i++) {
                    sb.append('.');
                }
            } else {
                int len = right - left - 1;
                for (int i = 0; i < len / 2; i++) {
                    sb.append('R');
                }
                if (len % 2 == 1) {
                    sb.append('.');
                }
                for (int i = 0; i < len / 2; i++) {
                    sb.append('L');
                }
            }
            left = right;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String dominoes = "RR.L";  // RR.L
        String dominoes2 = ".L.R...LR..L..";  // LL.RR.LLRRLL..
        String dominoes3 = "RR.....LL.RRRRRRRRRR";

        Solution solut = new Solution();
        System.out.println(solut.pushDominoes(dominoes));
    }
}
