package T354;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution2 {
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o2[1] - o1[1];
            }
        });

        for (int[] env : envelopes) {
            System.out.print("<" + env[0] + ", " + env[1] + "> ");
        }
        System.out.println();

        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++){
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)){
                f.add(num);
            }else{
                //
                int index = thisTestBinarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    // 本题的二分查找。不用找相等的数，只是找这个数大概插在哪个位置
    public static int thisTestBinarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            // 这个与(low+high)/2是一样的
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // 一般的二分查找
    public static int binarySearch(int[] list, int num) {
        int low = 0, high = list.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (list[mid] < num) {
                low = mid + 1;
            } else if (list[mid] > num) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        // 没有找到
        return -1;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int[][] envelopes2 = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.maxEnvelopes2(envelopes));
    }
}
