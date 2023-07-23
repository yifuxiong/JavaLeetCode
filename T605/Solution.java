package T605;

public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length % 2 == 0 && flowerbed.length / 2 < n) {
            return false;
        }
        if (flowerbed.length % 2 == 1 && flowerbed.length / 2 + 1 < n) {
            return false;
        }

        int remain = n;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1 || (i - 1 >= 0 && flowerbed[i - 1] == 1) || (i + 1 < flowerbed.length && flowerbed[i + 1] == 1)) {
                continue;
            } else {
                if (remain <= 0)
                    break;
                // 插入
                flowerbed[i] = 1;
                remain--;
            }
        }
        if (remain == 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;

        int[] flowerbed2 = {1, 0, 0, 0, 1};
        int n2 = 2;

        int[] flowerbed3 = {0, 1, 0, 0, 1};
        int n3 = 1;

        int[] flowerbed4 = {0};
        int n4 = 2;

        int[] flowerbed5 = {1, 0, 0, 0, 0, 1};
        int n5 = 2;


        int[] flowerbed6 = {0, 0, 1, 0, 0};
        int n6 = 1;

        Solution solut = new Solution();
        System.out.println(solut.canPlaceFlowers(flowerbed6, n6));
    }
}
