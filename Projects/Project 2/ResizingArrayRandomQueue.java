import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    //Instance Variables; Has to be q and N because of resize method
    private Item[] q;
    private int N;

    @SuppressWarnings("unchecked")
    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        q = (Item[]) new Object[2]; //create q with an initial capacity of 2
        N = 0;
    }

    // Is the queue empty?
    public boolean isEmpty() {
        return N == 0; //Return whether the queue is empty or not
    }

    // The number of items on the queue.
    public int size() {
        return N; //return the size of the queue
    }


    // Add item to the queue.
    public void enqueue(Item item) {
        //Corner Case 1
        if (item == null) throw new NullPointerException();
        if (N == q.length) resize(2 * q.length); //if q is at full capacity, resize it to twice its current capacity
        q[N] = item; //Insert the given item in q at index N
        N++; //Increment N by one

    }

    // Remove and return a random item from the queue.
    public Item dequeue() {
        //Corner Case 2
        if (isEmpty()) throw new NoSuchElementException();
        int r = StdRandom.uniform(0, N); //r is a random integer from the interval [0,N)
        Item item = q[r]; //Save q[r] in item

        //Removes item from queue
        q[r] = q[N - 1]; //Set q[r] to q[N- 1 ]
        q[N - 1] = null; //and q[N - 1] to null
        //If q is at quarter capacity, resize it to half its current capacity
        if (N == q.length / 4) {
            resize(q.length / 2);
        }
        N--; //Decrement N by one
        return item; //Return item
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int r = StdRandom.uniform(0, N); //r is a random integer from the interval [0, N)
        return q[r]; //Return q[r]
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator(); //Return an object of type RandomQueueIterator
    }

    @SuppressWarnings("unchecked")
    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] items; //Array to store te item of q, Item[] item
        private int current; //Index of the current item in items, int current

        RandomQueueIterator() {
            items = (Item[]) new Object[N]; // Create items with the same capacity as q
            //Copy the items of q into items
            for (int i = 0; i < N; i++) {
                items[i] = q[i];
            }
            StdRandom.shuffle(items); //Shuffle items
            current = 0; //Initialize current appropriately
        }

        public boolean hasNext() {
            return current < N; //Return whether the iterator has more items to iterate or not
        }

        //Corner Case 3
        public void remove() {
            throw new UnsupportedOperationException();
        }

        //Corner Case 4
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[current++]; //Return the item in items at index current and advance current by one

        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    @SuppressWarnings("unchecked")
    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
                new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}

