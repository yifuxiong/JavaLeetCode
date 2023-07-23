package Heap_sort;

/**
 * 只记得一句话：大根堆找最小的K个数；小根堆找最大的K个数。
 */

// 面试题17_14, T1046

public class HeapSort {
    // 大根堆，从上往下调整
    public void adjust(int[] heap, int low, int high) {
        // 记录当前作为根节点的值和索引
        int tempRoot = heap[low];
        int i = low;
        // 左孩子
        int j = i * 2 + 1;

        // 循环退出的两个条件：
        // 1.孩子节点索引超出堆的索引范围
        // 2.不需要调整
        while (j <= high) {
            if (j + 1 <= high && heap[j] < heap[j + 1]) {
                j += 1;  // 选择较大者
            }
            if (tempRoot < heap[j]) {
                heap[i] = heap[j];
                i = j;
                j = i * 2 + 1;
            } else break;  // 如果根节点比较大者还要大，那么这一段不需要调整
        }
        heap[i] = tempRoot;
    }

    // 小根堆，从上往下调整
    public void adjust_inv(int[] heap, int low, int high) {
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
            } else break;
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
            adjust_inv(heap, i, k - 1);
        }

        // 前k个排好了，后面从k到n-1个数再排
        for (int i = k; i < n; i++) {
            if (heap[0] < list[i]) {  // 这里改成">"，adjust函数就改成大根堆
                heap[0] = list[i];
                // 小根堆调整
                adjust_inv(heap, 0, k - 1);
            }
        }

        // 最后将整个堆逆序
        for (int i = k - 1; i >= 0; i--) {
            int tmp = heap[0];
            heap[0] = heap[i];
            heap[i] = tmp;
            adjust_inv(heap, 0, i - 1);
        }
        return heap;
    }

    public static void main(String[] args) {
        int[] heap = {3, 5, 1, 8, 2, 7, 9, 0, 4, 6};

        HeapSort heapSort = new HeapSort();
        int[] ret = heapSort.topK(heap, 3);
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
    }
}
