package T135;

public class Solution {
    public int candy(int[] ratings) {
        // 满足从左到右的规则
        int[] left = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i - 1 >= 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        // 再来一遍，满足从右到左的规则
        int[] right = new int[ratings.length];
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i + 1 < ratings.length && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        // 取两次遍历中的较大值
        int sum = left.length;
        for (int i = 0; i < left.length; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        int[] ratings2 = {1, 2, 2};
        int[] ratings3 = {1, 2, 87, 87, 87, 2, 1};
        int[] ratings4 = {1, 3, 2, 2, 1};

        Solution solut = new Solution();
        System.out.println(solut.candy(ratings4));
    }
}
