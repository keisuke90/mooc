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
      if (data == null) throw new IllegalArgumentException();
      
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
      if (data == null) throw new IllegalArgumentException();
      BSTNode<T> dummy = new BSTNode<>(null); 
      root = rRemove(root, data, dummy);
      
      return dummy.getData();
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

    private BSTNode<T> rAdd(BSTNode<T> currentNode, T data) {
      if (currentNode == null) {
        size++;
        return new BSTNode<T>(data);
      }

      int compareResult = currentNode.getData().compareTo(data);
      if (compareResult < 0) {
        currentNode.setRight(rAdd(currentNode.getRight(), data));
      } else if (compareResult > 0) {
        currentNode.setLeft(rAdd(currentNode.getLeft(), data));
      }

      return currentNode;
    }

    private BSTNode<T> rRemove(BSTNode<T> currentNode, T data, BSTNode<T> dummy) {
      if (currentNode == null) throw new NoSuchElementException();
      
      int compareResult = currentNode.getData().compareTo(data);
      if (compareResult < 0) {
        currentNode.setRight(rRemove(currentNode.getRight(), data, dummy));
      } else if (compareResult > 0) {
        currentNode.setLeft(rRemove(currentNode.getLeft(), data, dummy));
      } else {
        dummy.setData(currentNode.getData());
        size--;

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
          return null;
        } else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
          return currentNode.getLeft();
        } else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
          return currentNode.getRight();
        } else {
          BSTNode<T> dummy2 = new BSTNode<>(null);
          currentNode.setRight(removeSuccessor(currentNode.getRight(), dummy2));
          currentNode.setData(dummy2.getData());
        }
      }

      return currentNode;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> currentNode, BSTNode<T> dummy) {
      if (currentNode.getLeft() == null) {
        dummy.setData(currentNode.getData());
        return currentNode.getRight();
      } else {
        currentNode.setLeft(removeSuccessor(currentNode.getLeft(), dummy));
        return currentNode;
      }
    }
}
