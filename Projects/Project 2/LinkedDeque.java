import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {

    //Create a node
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    //Instance variables
    private int size;
    private Node first;
    private Node last;

    // Construct an empty deque.
    public LinkedDeque() {
        this.size = 0; //Size of the deque
        this.first = null; // Pointer to the head of the deque
        this.last = null; // Pointer to the tail of the deque
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        return first == null; //returns whether the deque is empty or not
    }

    // The number of items on the deque.
    public int size() {
        return size; //return the size of the deque
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        //Corner Case 1
        if (item == null) throw new NullPointerException();

        //Create a node that has item
        Node node = new Node();
        node.item = item;

        //Add the given item at the head end of the deque
        if (first != null) {
            node.next = first; //new node has current node in front
            first.prev = node; //current has new node behind it
        }
        first = node;
        if (last == null) last = first;

        size++; //Increment N by one
    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        //Corner Case 1
        if (item == null) throw new NullPointerException();

        //Create a node that has item
        Node node = new Node();
        node.item = item;

        //Add the given item at the tail end of the deque
        if (last != null) {
            node.prev = last;
            last.next = node;
        }
        last = node;
        if (first == null) first = last;

        size++; //Increment N by one
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        //Corner Case 2
        if (first == null) throw new NoSuchElementException();

        Node node = first;
        first = first.next;

        //Remove and return the item at the head end of the deque
        if (first == null) last = null;
        else first.prev = null;

        size--; //Decrement N by one
        return node.item;
    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        //Corner Case 2
        if (first == null) throw new NoSuchElementException();

        Node node = last;
        last = node.prev;

        //Remove and return the item at the tail end of the deque
        if (last == null) first = null;
        else last.next = null;

        size--; //Decrement N by one
        return node.item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        return new DequeIterator(); //Return an object of type DequeIterator
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        private Node current; //Pointer to current node in the iterator, Node current

        //Initialize instance variable appropriately
        DequeIterator() {
            current = first;
        }

        //Return whether the iterator has more items to iterate or not
        public boolean hasNext() {
            return current != null;
        }

        //Corner Case 3
        public void remove() {
            throw new UnsupportedOperationException();
        }

        //Return the item in current and advance current to the next node
        public Item next() {
            if (current == null) throw new NoSuchElementException(); //Corner Case 4
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
