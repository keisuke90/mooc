import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
      if (data == null) {
        throw new IllegalArgumentException();
      }

      if (backingArray.length == size + 1) {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; i++) {
          newArray[i] = backingArray[i];
        }
        backingArray = newArray;
      }
      
      int curr = size + 1;
      backingArray[curr] = data;
      swapParent(curr);
      size++;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
      if (size == 0) {
        throw new NoSuchElementException();
      }
      
      T removed = backingArray[1];
      backingArray[1] = backingArray[size];
      backingArray[size] = null;
      size--;
      if (size > 1) {
        swapChild(1);
      }
      return removed;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    private void swapParent(int curr) {
      if (curr == 1) return;

      if (backingArray[curr].compareTo(backingArray[curr/2]) < 0) {
        swap(curr, curr/2);
        swapParent(curr/2);
      }
    }

    private void swapChild(int curr) {
      if (curr * 2 > size) {
        return;
      } else if (curr * 2 == size) {
        if (backingArray[curr].compareTo(backingArray[curr*2]) > 0) {
          swap(curr, curr*2);
          swapChild(curr*2);
        }
      } else {
        if (backingArray[curr*2].compareTo(backingArray[curr*2+1]) > 0) {
          if (backingArray[curr].compareTo(backingArray[curr*2+1]) > 0) {
            swap(curr, curr*2+1);
            swapChild(curr*2+1);
          }
        } else {
          if (backingArray[curr].compareTo(backingArray[curr*2]) > 0) {
            swap(curr, curr*2);
            swapChild(curr*2);
          }
        }
      }
    }

    private void swap(int idx1, int idx2) {
      T tmp = backingArray[idx1];
      backingArray[idx1] = backingArray[idx2];
      backingArray[idx2] = tmp;
    }
}
