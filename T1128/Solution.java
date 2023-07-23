package T1128;

public class Solution {
    // 二元组表示 + 计数
    public int numEquivDominoPairs(int[][] dominoes) {
        int len = dominoes.length;
        if (len < 2) {
            return 0;
        }

        int sum = 0;
        int[] array = new int[100];
        for (int[] domino : dominoes) {
            int num = domino[0] > domino[1] ? domino[1] * 10 + domino[0] : domino[0] * 10 + domino[1];
            // 第一次出现这个数，单个不能凑成对
            sum += array[num];
            array[num]++;
            // 再出现一次才凑成一对
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] dominoes = {{1, 2}, {2, 1}, {3, 4}, {5, 6}};
        int[][] dominoes2 = {{1, 2}, {1, 2}, {2, 1}, {2, 1}, {2, 3}, {3, 4}, {3, 6}, {5, 6}};

        Solution solut = new Solution();
        System.out.println(solut.numEquivDominoPairs(dominoes2));
    }
}
