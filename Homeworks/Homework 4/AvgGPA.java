// AvgGPA.java: Reads from standard input a list of letter grades and computes
// and prints the average GPA (the average of the numbers corresponding to
// the grades).

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AvgGPA {
    public static void main(String[] args) {
        String letters[] = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
        double scores[] = {4.33, 4.0, 3.67, 3.33, 3.0, 2.67, 2.33, 2.0, 1.67, 1.0, 0};
        double total = 0.0;
        int counter = 0;
        double GPA = 0.0;
        String letter = "";

        //Asks for letter grades, converts them into scores, and records how many scores and their total
        while (!StdIn.isEmpty()) {
            letter = StdIn.readString();
            for (int i = 0; i < letters.length; i++) {
                if (letter.equals(letters[i])) {
                    total += scores[i];
                    counter += 1;
                }
            }
        }

        if (counter != 0) GPA = total / counter; //Answer not possible if counter is 0
        System.out.println(GPA);
    }
}
