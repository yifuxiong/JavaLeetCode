package T860;

import java.util.Vector;

public class Solution {
    // 自测
    public boolean lemonadeChange(int[] bills) {
        Vector changes = new Vector();
        for (int i : bills) {
            if (i == 5) {
                // 直接收钱
                changes.add(i);
            } else if (i == 10) {
                if (changes.contains(5)) {
                    // 收钱
                    changes.add(10);
                    // 找钱
                    changes.removeElement(5);
                } else return false;
            } else if (i == 20) {
                // 如果有一个5块和一个10块
                if (changes.contains(5) && changes.contains(10)) {
                    // 收钱
                    changes.add(20);
                    // 找钱
                    changes.removeElement(5);
                    changes.removeElement(10);
                    // 如果只有5块，没有10块
                } else if (changes.contains(5) && !changes.contains(10)) {
                    for (int j = 0; j < 3; j++) {
                        if (changes.contains(5)) {
                            changes.removeElement(5);
                        } else return false;
                    }
                } else return false;
            }
        }
        return true;
    }

    // 官方

    /**
     * 5美元，由于柠檬水的价格也为5美元，因此我们直接收下即可。
     * <p>
     * 10美元，我们需要找回5美元，如果没有5美元面值的钞票，则无法正确找零。
     * <p>
     * 20美元，我们需要找回15美元，此时有两种组合方式，
     * 一种是一张10美元和5美元的钞票，一种是3张5美元的钞票，
     * 如果两种组合方式都没有，则无法正确找零。
     * 当可以正确找零时，两种找零的方式中我们更倾向于第一种，
     * 即如果存在5美元和10美元，我们就按第一种方式找零，否则按第二种方式找零，
     * 因为需要使用5美元的找零场景会比需要使用10美元的找零场景多，
     * 我们需要尽可能保留5美元的钞票。
     * <p>
     * 基于此，我们维护两个变量five和ten表示当前手中拥有的5美元和10美元钞票的张数，
     * 从前往后遍历数组分类讨论即可。
     */
    public boolean lemonadeChange2(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5)
                five++;
            else if (bill == 10) {
                if (five <= 0)
                    return false;
                else {
                    five--;
                    ten++;
                }
            } else if (bill == 20) {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (ten <= 0 && five >= 3) {
                    five -= 3;
                } else return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        int[] bills2 = {5, 5, 10};
        int[] bills3 = {10, 10};
        int[] bills4 = {5, 5, 10, 10, 20};
        int[] bills5 = {5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5};

        Solution solut = new Solution();
        System.out.println(solut.lemonadeChange(bills5));
        System.out.println(solut.lemonadeChange2(bills5));
    }
}
