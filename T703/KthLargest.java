package T703;

import java.util.Arrays;

public class KthLargest {
    private int k;
    private int[] list;
    private int curLen;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.curLen = nums.length;
        this.list = new int[10000];
        Arrays.fill(this.list, -10001);
        for (int i = 0; i < this.curLen; i++) {
            this.list[i] = nums[i];
        }
    }

    public int add(int val) {
        this.list[this.curLen++] = val;

        // 堆排序取出topK并返回
        int[] heap = topK(this.list, this.k);
        System.out.println(heap[k - 1]);
        // 打印看看结果
//        for (int n: heap){
//            System.out.print(n + ", ");
//        }
//        System.out.println();
        return heap[k - 1];
    }

    // 小根堆，从上往下调整
    public void adjust(int[] heap, int low, int high) {
        // 临时存储
        int tempRoot = heap[low];
        int i = low;
        int j = i * 2 + 1;

        while (j <= high) {
            // 左右节点取较小那个
            if (j + 1 <= high && heap[j] > heap[j + 1]) {
                j += 1;
            }
            if (tempRoot > heap[j]) {
                heap[i] = heap[j];
                i = j;
                j = i * 2 + 1;
            } else {
                break;
            }
        }
        heap[i] = tempRoot;
    }

    public int[] topK(int[] list, int k) {
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = list[i];
        }
        int n = list.length;

        // 创建一个大小为k的堆，这里i是从堆末尾的根节点开始
        for (int i = k / 2 - 1; i >= 0; i--) {
            // 小根堆调整
            adjust(heap, i, k - 1);  // 大小为k的堆，k-1是最后一个索引
        }

        // 前k个排好了，后面从k到n-1个数再排
        for (int i = k; i < n; i++) {
            if (heap[0] < list[i]) {
                heap[0] = list[i];
                // 小根堆调整，整个堆都要调整
                adjust(heap, 0, k - 1);
            }
        }

        // ***最后将整个堆逆序
        for (int i = k - 1; i >= 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            adjust(heap, 0, i - 1);
        }
        return heap;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = {4, 5, 8, 2};
        KthLargest obj = new KthLargest(k, nums);
        int param_1 = obj.add(3);
        int param_2 = obj.add(5);
        int param_3 = obj.add(10);
        int param_4 = obj.add(9);
        int param_5 = obj.add(4);
    }
}
