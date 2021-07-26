package algorithms.ds;

public class BasicLinkedList<X> {
    private Node<X> first;
    private Node<X> last;
    private int nodeCount;

    public BasicLinkedList() {
        first = null;
        last = null;
        nodeCount = 0;
    }

    public void add(X item) {
        //this condition means we are adding something for the first time.
        if (first == null) {
            first = new Node<>(item);
            last = first;
        }
        //otherwise, we want to grab the last node and update it's value
        else {
            Node<X> newLastNode = new Node<>(item);
            last.setNextNode(newLastNode);
            last = newLastNode;
        }
        nodeCount++;
    }

    public void insert(X item, int position) {
        if (size() < position) {
            throw new IllegalStateException(
                    "The LinkedList is smaller than the position you are trying to insert at: " +
                    position);
        }

        Node<X> currentNode = first;

        //start at 1 because we are already on the first node
        for (int i = 1; i < position && currentNode != null; i++) {
            currentNode = currentNode.getNextNode();
        }

        //severs the link chain and reconnects with the new node
        Node<X> newNode = new Node<>(item);
        Node<X> nextNode = currentNode.getNextNode();
        currentNode.setNextNode(newNode);
        newNode.setNextNode(nextNode);
        nodeCount++;
    }

    public X removeAt(int position) {
        if (first == null) {
            throw new IllegalStateException(
                    "The LinkedList is empty and there are no items to remove");
        }

        Node<X> currentNode = first;
        Node<X> prevNode = first;

        //start at 1 because we are already on the first node
        for (int i = 1; i < position && currentNode != null; i++) {
            prevNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        //now update the pointers and throw away the old first
        X nodeItem = currentNode.getNodeItem();
        prevNode.setNextNode(currentNode.getNextNode());
        nodeCount--;
        return nodeItem;
    }

    // remove first element
    public X remove() {
        if (first == null) {
            throw new IllegalStateException(
                    "The LinkedList is empty and there are no items to remove");
        }

        X nodeItem = first.getNodeItem();

        //now update the first pointer and throw away the old first
        first = first.getNextNode();
        nodeCount--;
        return nodeItem;
    }

    public X get(int position) {
        if (first == null) {
            throw new IllegalStateException(
                    "The LinkedList is empty and there are no items to get");
        }

        Node<X> currentNode = first;
        for (int i = 1; i < size() && currentNode != null; i++) {
            if (i == position) {
                return currentNode.getNodeItem();
            }

            currentNode = currentNode.getNextNode();
        }

        //if we didn't find it then return null
        return null;
    }

    public int find(X item) {
        if (first == null) {
            throw new IllegalStateException(
                    "The LinkedList is empty and there are no items to find");
        }

        Node<X> currentNode = first;
        for (int i = 1; i < size() && currentNode != null; i++) {
            if (currentNode.getNodeItem().equals(item)) {
                return i;
            }

            currentNode = currentNode.getNextNode();
        }

        //if we didn't find it, then return -1
        return -1;
    }

    //useful for pretty print
    public String toString() {
        StringBuilder contents = new StringBuilder();
        Node<X> curNode = first;

        while (curNode != null) {
            contents.append(curNode.getNodeItem());
            curNode = curNode.getNextNode();

            if (curNode != null) {
                contents.append(", ");
            }
        }
        return contents.toString();
    }


    public int size() {
        return nodeCount;
    }

    private static class Node<X> {
        private Node<X> nextNode;
        private X nodeItem;

        public Node(X item) {
            this.nextNode = null;
            this.nodeItem = item;
        }

        public void setNextNode(Node<X> nextNode) {
            this.nextNode = nextNode;
        }

        public Node<X> getNextNode() {
            return nextNode;
        }

        public X getNodeItem() {
            return nodeItem;
        }
    }
}
