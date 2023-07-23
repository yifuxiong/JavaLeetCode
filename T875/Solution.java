package T875;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1_000_000_000;

        while (left < right){
            int mid = (left + right) / 2;
            int hours = 0;

            for (int pile: piles){
                if (pile < mid){
                    hours++;
                }else if (pile % mid == 0){
                    hours+=(pile / mid);
                }else{
                    hours+=((pile / mid) + 1);
                }
            }

            if (hours <= h){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int H = 8;

        int[] piles2 = {30, 11, 23, 4, 20};
        int H2 = 5;

        int[] piles3 = {30, 11, 23, 4, 20};
        int H3 = 6;

        int[] piles4 = {332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
        int H4 = 823855818;

        Solution solut = new Solution();
        System.out.println(solut.minEatingSpeed(piles4, H4));
    }
}
