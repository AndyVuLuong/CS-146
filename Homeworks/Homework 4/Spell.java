// Spell.java: Takes a command-line argument specifying the name of the file
// containing common misspellings (a line-oriented file with each
// comma-separated line containing a misspelled word and the correct spelling),
// then reads text from standard input and prints out the misspelled words in
// the text along with the line numbers where they occurred and their correct
// spellings.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Spell {
    public static void main(String[] args) {
        //Use In for file input
        In input = new In(args[0]);
        String[] misspellings = input.readAllLines();
        input.close();
        int counter = 0;
        ArrayST<String, String> st = new ArrayST<String, String>();

        //divides each line of misspellings.txt into two separate strings into st
        for (int i = 0; i < misspellings.length; i++) {
            String line = misspellings[i];
            String[] words = line.split(",");
            String wrong = words[0];
            String right = words[1];
            st.put(wrong, right);
        }

        //divides each line of war_and_peace.txt into separate strings including commas and periods into word
        //compares it to st
        while (!StdIn.isEmpty()) {
            ++counter;
            String war_and_peace = StdIn.readLine();
            String[] word = war_and_peace.split(" ");
            for (int i = 0; i < word.length; i++) {
                if (st.contains(word[i])) { StdOut.println(word[i] + ":" + counter + " -> " + st.get(word[i])); }
            }
        }
    }
}