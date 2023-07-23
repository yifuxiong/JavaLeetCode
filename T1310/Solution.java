package T1310;

public class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int a = 0;
            int left = queries[i][0];
            int right = queries[i][1];
            for (int j = left; j <= right; j++) {
                a ^= arr[j];
            }
            ans[i] = a;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {16};
        int[][] queries = {{0, 0}, {0, 0}, {0, 0}};

        Solution solut = new Solution();
        int[] ans = solut.xorQueries(arr, queries);

        for (int a: ans){
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
