package T274;

public class Solution2 {
    // 计数排序
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counter = new int[n + 1];

        // 小于等于n的，全部对应counter[i]++；大于n的，全部counter[n]++
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) {
                counter[n]++;
            } else {
                counter[citations[i]]++;
            }
        }

        int tot = 0;
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        int[] citations2 = {1, 3, 1};
        int[] citations3 = {11, 15};
        int[] citations4 = {1};

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.hIndex(citations));
    }
}
