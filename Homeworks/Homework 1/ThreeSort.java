// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.


import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        StdOut.println(Math.min(Math.min(a, b), c));

        //Getting med number requires if statements, check b then a then c
        if ((a < b && b < c) || (c < b && b < a)) StdOut.println(b);
        else if ((b < a && a < c) || (c < a && a < b)) StdOut.println(a);
        else StdOut.println(c);

        StdOut.println(Math.max(Math.max(a, b), c));
    }
}

