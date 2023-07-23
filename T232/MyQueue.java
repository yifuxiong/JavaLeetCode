package T232;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        inStack.offerLast(x);
        if (!inStack.isEmpty()) {
            outStack.offerLast(inStack.pollLast());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return outStack.pollFirst();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return outStack.peekFirst();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        if (inStack.isEmpty() && outStack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        MyQueue que = new MyQueue();
        que.push(1);
        System.out.println("queue is " + que.inStack + ", " + que.outStack);
        que.push(2);
        System.out.println("queue is " + que.inStack + ", " + que.outStack);
        System.out.println("return " + que.peek());
        System.out.println("return " + que.pop() + ", queue is " + que.inStack + ", " + que.outStack);
        System.out.println("return " + que.empty());

    }
}
