import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {
    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int lastSwappedIndex = 0;
            for (int j = 0; j < stopIndex; j++) {
                int cmp = comparator.compare(arr[j], arr[j + 1]);
                if (cmp > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    lastSwappedIndex = j;
                }
            }
            stopIndex = lastSwappedIndex;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        int lastIndex = arr.length - 1;
        for (int i = arr.length - 1; i > 0; i--) {
            int maxValIdx = lastIndex;
            for (int j = i - 1; j >= 0; j--) {
                int cmp = comparator.compare(arr[j], arr[maxValIdx]);
                if (cmp > 0) {
                    maxValIdx = j;
                }
            }
            T tmp = arr[lastIndex];
            arr[lastIndex] = arr[maxValIdx];
            arr[maxValIdx] = tmp;
            lastIndex--;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                int cmp = comparator.compare(arr[j - 1], arr[j]);
                if (cmp > 0) {
                    T tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length < 2) {
            return;
        }
        int rightLength = 0;
        if (arr.length % 2 == 0) {
            rightLength = arr.length / 2;
        } else {
            rightLength = arr.length / 2 + 1;
        }
        T[] leftArray = (T[]) new Object[arr.length / 2];
        T[] rightArray = (T[]) new Object[rightLength];
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = arr[i];
        }

        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = arr[i + leftArray.length];
        }

        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        int i = 0;
        int j = 0;

        while (i < leftArray.length && j < rightArray.length) {
            int cmp = comparator.compare(leftArray[i], rightArray[j]);
            if (cmp < 1) {
                arr[i + j] = leftArray[i];
                i++;

            } else {
                arr[i + j] = rightArray[j];
                j++;
            }
        }

        while (i < leftArray.length) {
            arr[i + j] = leftArray[i];
            i++;
        }

        while (j < rightArray.length) {
            arr[i + j] = rightArray[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        long max = 0;
        for (int v : arr) {
            long a = Math.abs((long) v);
            if (a > max) {
                max = a;
            }
        }

        @SuppressWarnings("unchecked") Queue<Integer>[] bucket = (Queue<Integer>[]) new Queue[19];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new LinkedList<>();
        }

        // exp is long so exp * 10 cannot overflow (max exp is 10^10).
        for (long exp = 1; max / exp > 0; exp *= 10) {
            for (int v : arr) {
                // (v / exp) % 10 is in [-9, 9]; +9 maps it to [0, 18].
                int digit = (int) ((v / exp) % 10) + 9;
                bucket[digit].add(v);
            }
            int idx = 0;
            for (Queue<Integer> q : bucket) {
                while (!q.isEmpty()) {
                    arr[idx++] = q.poll();
                }
            }
        }
    }
}
