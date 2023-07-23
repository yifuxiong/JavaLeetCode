package T278;

public class Solution {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while(left < right){
            int mid = left + (right - left) / 2;
            if (!isBadVersion(mid)){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    public boolean isBadVersion(int n){
        if (n == 4){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        int n = 5;

        Solution solut = new Solution();
        System.out.println(solut.firstBadVersion(n));
    }
}
