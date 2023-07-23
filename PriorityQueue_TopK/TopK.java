package PriorityQueue_TopK;

import java.util.PriorityQueue;

// T1046
public class TopK {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
    }
}
