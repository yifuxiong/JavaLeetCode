package T263;

public class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0 || n % 3 == 0 || n % 5 == 0){
            if (n % 2 == 0) {
                n = n / 2;
            }
            if (n % 3 == 0){
                n = n / 3;
            }
            if (n % 5 == 0){
                n = n / 5;
            }
        }

        if (n == 1){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        int n = 0;

        Solution solut = new Solution();
        System.out.println(solut.isUgly(n));
    }
}
