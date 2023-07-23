package Quick_Sort;

// 改进的快排：T1738



public class QuickSort {
    // 快速排序
    public static void quickSort(int[] n, int low, int high) {
        if (low < high) {
            int pos = partition(n, low, high);
            quickSort(n, low, pos - 1);
            quickSort(n, pos + 1, high);
        }
    }

    public static int partition(int[] n, int low, int high) {
        int key = n[low];
        while (low < high) {
            while (low < high && key <= n[high])
                high--;
            n[low] = n[high];
            while (low < high && n[low] <= key)
                low++;
            n[high] = n[low];
        }
        n[low] = key;
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 9, 1, 5, 9};

        quickSort(nums, 0, nums.length - 1);
        for (int i: nums){
            System.out.print(i + " ");
        }
    }
}
