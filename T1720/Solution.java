package T1720;

public class Solution {
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] arr = new int[n + 1];
        arr[0] = first;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = arr[i] ^ encoded[i];
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] encoded = {1, 2, 3};
        int first = 1;

        int[] encoded2 = {6, 2, 7, 3};
        int first2 = 4;

        Solution solut = new Solution();
        int[] arr = solut.decode(encoded2, first2);

        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
