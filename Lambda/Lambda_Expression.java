package Lambda;

import java.util.Arrays;
import java.util.Comparator;

// T300, T354, T435, T692, [T786]
public class Lambda_Expression {
    public static void main(String[] ars) {
        // T354
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return b[1] - a[1];
        });

        // 或者
//        Arrays.sort(envelopes, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] != o2[0]) return o1[0] - o2[0];
//                return o2[1] - o1[1];
//            }
//        });

        for (int[] env : envelopes) {
            System.out.print("<" + env[0] + ", " + env[1] + "> ");
        }
        System.out.println();
    }
}
