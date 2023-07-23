package Hanoi;

public class Solution {
    public void hanoi(int n) {
        if (n > 0) {
            lTor(n, "left", "mid", "right");
        }
    }

    //移动过程
    public void lTor(int n, String left, String mid, String right) {
        if (n == 1) {
            System.out.println("move  " + n + "  " + left + "  To  " + right);
        } else {
            lTor(n - 1, left, right, mid);
            lTor(1, left, mid, right);
            lTor(n - 1, mid, left, right);
        }
    }
    //该方法为递归调用实现,因为函数栈的关系，有额外空间复杂度N

    public static void main(String[] args){
        Solution solut = new Solution();
        solut.hanoi(3);
    }
}
