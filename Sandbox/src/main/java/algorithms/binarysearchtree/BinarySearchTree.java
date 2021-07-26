package algorithms.binarysearchtree;

public class BinarySearchTree {

    public Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node n) {
        if (n != null) {
            inOrder(n.left);
            System.out.println(n.getData());
            inOrder(n.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node n) {
        if (n != null) {
            System.out.println(n.getData());
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    public int min() {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.getData();
    }

    public int max() {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.getData();
    }

    public void insert(int data) {
        Node newNode = new Node();
        newNode.data = data;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (data < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        break;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        break;
                    }
                }
            }
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        Node() {
            left = null;
            right = null;
        }

        int getData() {
            return data;
        }
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(8);
    }
}
