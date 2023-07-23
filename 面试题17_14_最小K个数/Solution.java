package 面试题17_14_最小K个数;

public class Solution {
    public int[] smallestK(int[] arr, int k) {
        int n = arr.length;
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = arr[i];  // 先将arr中前k个数复制到heap中
        }

        // 1. 对前k个节点
        // 从叶子节点开始向根节点，逐步扩大二叉树的规模
        for (int i = k / 2 - 1; i >= 0; i--) {
            adjust(heap, i, k - 1);
        }

        // 2. 从第k个到最后一个节点，查看有无漏掉的情况
        for (int i = k; i <= n - 1; i++) {
            if (heap[0] > arr[i]) {
                heap[0] = arr[i];
                adjust(heap, 0, k - 1);
            }
        }

        // 3. 顺序输出（这一步可以省略，因为题目并不要求顺序输出）
        for (int i = k - 1; i >= 0; i--) {
            int tmp = heap[0];
            heap[0] = heap[i];
            heap[i] = tmp;
            adjust(heap, 0, i - 1);
        }

        return heap;
    }

    // 大根堆
    public void adjust(int[] heap, int low, int high) {
        int root = heap[low];
        int i = low;
        int j = i * 2 + 1;  // 左子树

        while (j <= high) {
            if (j + 1 <= high && heap[j] < heap[j + 1]) {
                j += 1;  // 选择较大者
            }
            if (root < heap[j]) {
                heap[i] = heap[j];
                i = j;
                j = i * 2 + 1;
            } else {
                break;  // 否则不需要调整
            }
        }
        heap[i] = root;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;

        Solution solut = new Solution();
        int[] ans = solut.smallestK(arr, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
