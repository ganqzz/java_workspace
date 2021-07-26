package demo.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDequeDemo {

    public static void main(String[] args) {
        Queue<String> myQueue = new ArrayDeque<String>();
        Deque<String> myStack = new ArrayDeque<String>();
        Deque<String> myDeque = new ArrayDeque<String>();

        myQueue.offer("a");
        myQueue.offer("b");
        myQueue.offer("c");
        System.out.println(myQueue);
        while (myQueue.peek() != null) {
            System.out.println(myQueue.poll());
        }

        System.out.println("---");

        // Queue操作とStack操作を混ぜるとわかりにい
        myStack.offer("a");
        myStack.offer("b");
        myStack.offer("c");
        System.out.println(myStack);
        while (myStack.peek() != null) {
            System.out.println(myStack.pop());
        }

        System.out.println("---");

        myDeque.push("a");
        myDeque.push("b");
        myDeque.push("c");
        System.out.println(myDeque);
        while (myDeque.peek() != null) {
            System.out.println(myDeque.pop());
        }

        System.out.println("---");
    }
}
