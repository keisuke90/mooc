import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
      if (data == null) {
        throw new IllegalArgumentException();
      }
      
      size++;
      SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

      if (tail == null) {
        head = newNode;
        tail = newNode;
        return;
      }
      
      newNode.setNext(head);
      head = newNode;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
      if (data == null) {
        throw new IllegalArgumentException();
      }

      size++;
      SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data, null);

      if (head == null) {
        head = newNode;
        tail = newNode;
        return;
      }

      tail.setNext(newNode);
      tail = newNode;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
      if (head == null) {
        throw new NoSuchElementException();
      }
      
      T removedData = head.getData();

      if (head.getNext() == null) {
        head = null;
        tail = null;
      } else {
        head = head.getNext();
      }

      size--;
      return removedData;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
      if (head == null) {
        throw new NoSuchElementException();
      }

      T removedData = tail.getData();
      SinglyLinkedListNode<T> curr = head;
      
      if (head.getNext() == null) {
        head = null;
        tail = null;
      } else {
        while(curr.getNext() != null) {
          if(curr.getNext().getNext() == null) {
            break;
          }
          curr = curr.getNext();
        }
        curr.setNext(null);
        tail = curr;
      }

      size--;
      return removedData;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
