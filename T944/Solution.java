package T944;

public class Solution {
    public int minDeletionSize(String[] A) {
        int num = 0;

        for (int j = 0; j < A[0].length(); j++) {
            for (int i = 1; i < A.length; i++) {
                if (A[i].charAt(j) < A[i - 1].charAt(j)) {
                    num++;
                    break;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        String[] A = {"cba", "daf", "ghi"};
        String[] A2 = {"a", "b"};
        String[] A3 = {"zyx", "wvu", "tsr"};

        Solution solut = new Solution();
        System.out.println(solut.minDeletionSize(A));
    }
}
