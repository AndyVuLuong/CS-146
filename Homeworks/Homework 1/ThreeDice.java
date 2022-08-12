// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class ThreeDice {
    public static void main(String[] args) {
        Random rand = new Random();
        int a = rand.nextInt(6) + 1; //range from 1-6
        int b = rand.nextInt(6) + 1;
        int c = rand.nextInt(6) + 1;
        int d = a + b + c; //add values
        StdOut.println(d);
    }
}

