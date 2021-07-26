package algorithms.ds;

/**
 * Todo: Re-balance : Tree Rotation
 *
 * @param <X>
 */
public class BasicBinaryTree<X extends Comparable<X>> {
    private Node<X> root;
    private int size;

    public BasicBinaryTree() {
        root = null;
    }

    public int size() {
        return size;
    }

    public void add(X item) {
        Node<X> node = new Node<>(item);

        //if this is the first item in the tree, set it as root
        if (root == null) {
            root = node;
            System.out.println("Set root: " + node.getItem());
            size++;
        }
        //otherwise we need to insert the item into the tree using the binary tree insert algorithm
        else {
            insert(root, node);
        }
    }

    public boolean contains(X item) {
        Node<X> currentNode = getNode(item);

        if (currentNode == null) {
            //didn't find it
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(X item) {
        boolean deleted = false;

        // make sure the root isn't null meaning the tree is empty
        if (root == null) {
            return false;
        }

        // find the node to delete
        Node<X> currentNode = getNode(item);

        if (currentNode != null) {
            // if the node to delete doesn't have any children, just delete it
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                unlink(currentNode, null);
                deleted = true;
            }
            // if the node to delete only has a right child, remove it in the hierarchy
            else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            }
            // if the node to delete only has a left child, remove it in the hierarchy
            else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            }
            // the node has both children, do a node swap to delete
            else {
                // you can swap out the node with the right most leaf node on the left side
                Node<X> child = currentNode.getLeft();
                while (child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }

                //we have the right most leaf node.  We can replace the current node with this node
                child.getParent().setRight(null); // remove the leaf node from it's current position

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;
            }
        }

        if (deleted) {
            size--;
        }

        return deleted;
    }

    private void unlink(Node<X> currentNode, Node<X> newNode) {
        // if this is the root node, we replace that a little differently
        if (currentNode == root) {
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            root = newNode;
        } else if (currentNode.getParent().getRight() == currentNode) {
            currentNode.getParent().setRight(newNode);
        } else {
            currentNode.getParent().setLeft(newNode);
        }
    }


    private Node<X> getNode(X item) {
        Node<X> currentNode = root;

        while (currentNode != null) {
            int val = item.compareTo(currentNode.getItem());

            //see if the node is a match
            if (val == 0) {
                return currentNode;
            }
            // if the val is less than 0 we go to the left side of the tree
            else if (val < 0) {
                currentNode = currentNode.getLeft();
            }
            // otherwise we go to the right side
            else {
                currentNode = currentNode.getRight();
            }
        }

        return null;
    }

    private void insert(Node<X> parent, Node<X> child) {
        //if the child is less than the parent, it goes on the left
        if (child.getItem().compareTo(parent.getItem()) < 0) {
            //if the left node is null, we've found our spot
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                size++;
            }
            //otherwise we need to call insert again and test for left or right (recursion)
            else {
                insert(parent.getLeft(), child);
            }
        }
        //if the child is greater than the parent, it goes on the right
        else if (child.getItem().compareTo(parent.getItem()) > 0) {
            //if the right node is null, we've found our spot
            if (parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                size++;
            }
            //otherwise we need to call insert again and test for left or right (recursion)
            else {
                insert(parent.getRight(), child);
            }
        }

        //if the parent and child happen to be equal, we don't do anything
        //data in a binary tree is assumed to be unique and the value already exists in the tree
    }

    private static class Node<X extends Comparable<X>> {
        private Node<X> left;
        private Node<X> right;
        private Node<X> parent;
        private X item;

        public Node(X item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node<X> getLeft() {
            return left;
        }

        public void setLeft(Node<X> left) {
            this.left = left;
        }

        public Node<X> getRight() {
            return right;
        }

        public void setRight(Node<X> right) {
            this.right = right;
        }

        public Node<X> getParent() {
            return parent;
        }

        public void setParent(Node<X> parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }
    }
}
