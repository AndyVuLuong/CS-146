// Distance.java: reads two position vectors from standard input, and then
// computes and writes the Manhattan distance between the two.

import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdOut;

public class ManhattanDistance {
    // Returns the Euclidean distance between the position vectors x and y.
    private static double distance(double[] x, double[] y) {
        //Given Formula using indexes from 0-5
        double a = Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);

        //Increment by 2
        double b = Math.abs(x[2] - y[2]) + Math.abs(x[3] - y[3]);
        double c = Math.abs(x[4] - y[4]) + Math.abs(x[5] - y[5]);

        //Add distances together
        double d = a + b + c;
        return d;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[] x = StdArrayIO.readDouble1D();
        double[] y = StdArrayIO.readDouble1D();
        StdOut.println(distance(x, y));
    }
}
