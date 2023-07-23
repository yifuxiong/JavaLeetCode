package T374;

public class Solution {
    int n = 3, pick = 2;

    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == -1) {
                // 猜的数字比pick大
                right = mid;
            } else if (guess(mid) == 1) {
                // 猜的数字比pick小
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public int guess(int num) {
        if (pick < num) {
            return -1;
        } else if (pick > num) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution solut = new Solution();
        System.out.println(solut.guessNumber(solut.n));
    }
}
