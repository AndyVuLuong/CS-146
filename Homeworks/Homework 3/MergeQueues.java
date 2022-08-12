import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MergeQueues {
    // Return true if v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Merge and return the two sorted queues as a single sorted queue.
    private static Queue<Comparable> merge(Queue<Comparable> q1,
                                           Queue<Comparable> q2) {

        Queue<Comparable> q = new Queue<Comparable>(); //queue
        Iterator<Comparable> i1 = q1.iterator(); //First Iterator
        Iterator<Comparable> i2 = q2.iterator(); //Second Iterator
        Comparable c1 = null; //First comparable
        Comparable c2 = null; //Second comparable

        //Transfer iterator to comparable in beginning to use while loop
        if (i1.hasNext()) c1 = i1.next();
        if (i2.hasNext()) c2 = i2.next();

        // enqueue in order
        while (c1 != null && c2 != null) {
            //Decides which comparables goes first until iterators are empty
            if (less(c1, c2)) {
                q.enqueue(c1);
                if (i1.hasNext()) c1 = i1.next();
                else c1 = null;
            } else {
                q.enqueue(c2);
                if (i2.hasNext()) c2 = i2.next();
                else c2 = null;
            }
        }

        //Adds leftovers onto q
        //q1 elements
        while (i1.hasNext()) {
            c1 = i1.next();
            q.enqueue(c1);
        }
        //q2 elements
        while (i2.hasNext()) {
            c2 = i2.next();
            q.enqueue(c2);
        }
        return q;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Queue<Comparable> q1 = new Queue<Comparable>();
        Queue<Comparable> q2 = new Queue<Comparable>();
        for (String s : a) {
            if (StdRandom.bernoulli(0.5)) {
                q1.enqueue(s);
            } else {
                q2.enqueue(s);
            }
        }
        int s1 = q1.size(), s2 = q2.size();
        StdOut.println(merge(q1, q2));
        assert q1.size() == s1 && q2.size() == s2;
    }
}
