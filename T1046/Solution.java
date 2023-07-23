package T1046;

import java.util.PriorityQueue;

// 这题堆排序取topK，或者快速排序取前2个
public class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (Integer i : stones) {
            queue.offer(i);
        }
        System.out.println(queue);

        while (queue.size() > 1) {
            int n1 = queue.poll();
            int n2 = queue.poll();
            queue.offer(Math.abs(n1 - n2));
        }

        return queue.isEmpty() ? 0 : queue.poll();
    }

    // 小根堆，从上往下调
    public void adjust(int[] heap, int low, int high) {
        int temp = heap[low];
        int i = low;
        int j = i * 2 + 1;

        while (j <= high) {
            if (j + 1 <= high && heap[j] > heap[j + 1]) {
                j += 1;
            }
            if (temp > heap[j]) {
                heap[i] = heap[j];
                i = j;
                j = i * 2 + 1;
            } else {
                break;
            }
        }
        heap[i] = temp;
    }

    public int[] topK(int[] list, int k) {
        // 先初始化一个k个大小的数组，里面存放list的前k个数字
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = list[i];
        }

        int n = list.length;
        // 第一次调整k个数
        // i从k/2-1的根节点开始，即右下角，左下角，上升一层...
        // 小根堆调整，下标从0到k-1一共k个数
        for (int i = k / 2 - 1; i >= 0; i--) {
            adjust(heap, i, k - 1);
        }

        // 这时候前k个数大小的小根堆就排好了，现在开始从k开始，每次比较&插入一个新数再调整
        for (int i = k; i < n; i++) {
            // 记住这是筛选最大的k个数字，所以有数字比heap[0]大就进入这个堆重新排
            if (heap[0] < list[i]) {
                heap[0] = list[i];
                adjust(heap, 0, k - 1);
            }
        }

        // 逆序
        for (int i = k - 1; i >= 0; i--) {
            int tmp = heap[0];
            heap[0] = heap[i];
            heap[i] = tmp;
            // 这里high = i不行，i=k-1的时候，前面已经排了，不需要再排一次
            adjust(heap, 0, i - 1);
        }

        return heap;
    }


    // 打印测试
    public void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};

        Solution solut = new Solution();
        System.out.println(solut.lastStoneWeight(stones));
    }
}
