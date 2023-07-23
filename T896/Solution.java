package T896;

public class Solution {
    public boolean isMonotonic(int[] A) {
        int n = A.length;
        if (n == 2 || n == 1) {
            return true;
        }

        int flag = A[1] - A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] - A[i - 1] == 0) {
                continue;
            } else {
                if (flag == 0){
                    // 更新flag
                    flag = A[i] - A[i - 1];
                }
                if (flag < 0 && A[i] - A[i - 1] > 0 || flag > 0 && A[i] - A[i - 1] < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 2, 3};
        int[] A2 = {6, 5, 4, 4};
        int[] A3 = {1, 3, 2};
        int[] A4 = {1, 2, 4, 5};
        int[] A5 = {1, 1, 1};
        int[] A6 = {11, 11, 9, 4, 3, 3, 3, 1, -1, -1, 3, 3, 3, 5, 5, 5};

        Solution solut = new Solution();
        System.out.println(solut.isMonotonic(A));
    }
}
