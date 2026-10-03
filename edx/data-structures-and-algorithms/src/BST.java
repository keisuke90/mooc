import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        root = rAdd(root, data);
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        BSTNode<T> removed = new BSTNode<>(null);
        root = rRemove(root, data, removed);
        return removed.getData();
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        //<C-D-S><C-D-S> DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the subtree rooted at node with data added as a new leaf,
     * or unchanged if data is already present.
     */
    private BSTNode<T> rAdd(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        }

        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(rAdd(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(rAdd(node.getRight(), data));
        }
        return node;
    }

    /**
     * Returns the subtree rooted at node with data removed. The data stored
     * in the tree is written into removed.
     */
    private BSTNode<T> rRemove(BSTNode<T> node, T data, BSTNode<T> removed) {
        if (node == null) {
            throw new NoSuchElementException("Data is not in the tree.");
        }

        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(rRemove(node.getLeft(), data, removed));
            return node;
        }
        if (cmp > 0) {
            node.setRight(rRemove(node.getRight(), data, removed));
            return node;
        }

        removed.setData(node.getData());
        size--;

        // Zero or one child: replace the node with its only child (or null).
        if (node.getLeft() == null) {
            return node.getRight();
        }
        if (node.getRight() == null) {
            return node.getLeft();
        }

        // Two children: replace the data with the successor's.
        BSTNode<T> successor = new BSTNode<>(null);
        node.setRight(removeSuccessor(node.getRight(), successor));
        node.setData(successor.getData());
        return node;
    }

    /**
     * Returns the subtree rooted at node with its minimum removed. The
     * minimum's data is written into successor.
     */
    private BSTNode<T> removeSuccessor(BSTNode<T> node, BSTNode<T> successor) {
        if (node.getLeft() == null) {
            successor.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(removeSuccessor(node.getLeft(), successor));
        return node;
    }
}
