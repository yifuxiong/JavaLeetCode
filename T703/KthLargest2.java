package T703;

import java.util.PriorityQueue;

public class KthLargest2 {
    private int k;
    private PriorityQueue<Integer> queue;
    public KthLargest2(int k, int[] nums) {
        this.k = k;
        // queue = new PriorityQueue<>((a, b) -> b - a);
        queue = new PriorityQueue<>();
        for (int n: nums){
            queue.offer(n);
        }
    }

    public int add(int val) {
        queue.offer(val);
        while (queue.size() > k){
            queue.poll();
        }
        System.out.println(queue.peek());
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = {4, 5, 8, 2};
        KthLargest2 obj = new KthLargest2(k, nums);
        int param_1 = obj.add(3);
        int param_2 = obj.add(5);
        int param_3 = obj.add(10);
        int param_4 = obj.add(9);
        int param_5 = obj.add(4);
    }
}
