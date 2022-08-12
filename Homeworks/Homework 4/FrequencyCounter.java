// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.security.Key;

public class FrequencyCounter {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int maxCounter = N;
        int totalWords = 0;
        ArrayST<String, Integer> wordFrequency = new ArrayST<>(); //Must use ArrayST

        //Asks for words, checks how much duplicates, and updates maxCounter
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            totalWords++;
            if (wordFrequency.contains(word)) {
                wordFrequency.put(word, wordFrequency.get(word) + 1);
                if (maxCounter < wordFrequency.get(word)) {
                    maxCounter = wordFrequency.get(word);
                }
            }else{
                wordFrequency.put(word, 1);
            }
        }

        //Gets multiple words that have the same maxCounter
        for (String word : wordFrequency.keys()) {
            if (wordFrequency.get(word) == maxCounter) {
                System.out.print(word + " be ");
            }
        }

        //Prints maxCounter, distinct words, and total words
        System.out.print(maxCounter + "\n");
        System.out.println("distinct = " + (wordFrequency.size() - 1));
        System.out.println("words = " + (totalWords));
    }
}

