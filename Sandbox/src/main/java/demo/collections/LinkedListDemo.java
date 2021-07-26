package demo.collections;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class LinkedListDemo {
    public static void main(String[] args) {

        LinkedList<String> states = new LinkedList<>();
        states.add("Alaska");
        states.add("Arizona");
        states.add("Arkansas");
        states.add("Pennsylvania");
        states.add("California");
        states.add("Colorado");
        states.add("Connecticut");

        states.addFirst("Alabama");
        System.out.println(states);
        System.out.println("last state in my list: " + states.getLast());

        System.out.println("---");

        ListIterator<String> iterator = states.listIterator(states.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        System.out.println("---");

        Queue<String> myQueue = new LinkedList<String>();
        Deque<String> myStack = new LinkedList<String>();

        myQueue.offer("a");
        myQueue.offer("b");
        myQueue.offer("c");
        while (myQueue.peek() != null) {
            System.out.println(myQueue.poll());
        }
        //System.out.println(myQueue.size());

        myStack.push("a");
        myStack.push("b");
        myStack.push("c");
        while (myStack.peek() != null) {
            System.out.println(myStack.pop());
        }
        //System.out.println(myStack.size());

        //
        System.out.println();
        System.out.println("---");

        LinkedList<String> names = new LinkedList<String>();
        names.addLast("Cynthia");
        names.addLast("Raymond");
        names.addLast("David");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println();
        names.removeFirst();
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println();
        names.addLast("Clayton");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("Size of queue: " + names.size());
        if (!names.isEmpty()) {
            names.removeFirst();
        }
        System.out.println("Size of queue: " + names.size());
        for (String name : names) {
            System.out.println(name);
        }
    }
}
