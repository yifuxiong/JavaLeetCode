package T1337;

import java.util.*;

public class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[k];

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int k1 = o1[0], k2 = o2[0];
                int v1 = o1[1], v2 = o2[1];

                if (v1 < v2){
                    return -1;
                }else if (v1 == v2){
                    if (k1 < k2){
                        return -1;
                    }else {
                        return 1;
                    }
                }else{
                    return 1;
                }
            }
        });

        for (int i = 0; i < m; i++){
            int[] a = new int[2];
            a[0] = i;
            a[1] = Arrays.stream(mat[i]).sum();
            queue.offer(a);
        }

        for (int l = 0; l < k; l++){
            int[] a = queue.poll();
            ans[l] = a[0];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k = 3;

        Solution solut = new Solution();
        int[] ans = solut.kWeakestRows(mat, k);
        for (int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
