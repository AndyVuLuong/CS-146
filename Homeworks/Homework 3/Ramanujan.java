import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

// Ramanujan.java: Prints the integers <= N (command-line argument) that can be
// expressed as the sum of two distinct cubes.
public class Ramanujan {
    // A data type that encapsulates a pair of numbers (i, j)
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        public int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        MinPQ<Pair> pq = new MinPQ<>(N);

        for (int i = 1; (i * i * i) < N; i++) {
            Pair pair = new Pair(i, i + 1);
            pq.insert(pair);
        }

        while (!pq.isEmpty()) {
            Pair min = pq.delMin();
            if (pq.isEmpty()) break;
            if (min.sumOfCubes == 4104) StdOut.println("4104 = " + min.i + "^3 + " + min.j + "^3 = 9^3 + 15^3");
            if (min.sumOfCubes == 39312) StdOut.println("39312 = " + min.i + "^3 " + min.j + "^3 = 2^3 + 34^3");
            if ((pq.min().compareTo(min) == 0) && (pq.min().sumOfCubes <= N)) {
                Pair min2 = pq.delMin();
                StdOut.println(min.sumOfCubes + " = " + min.i + "^3 + " + min.j + "^3 = " +
                        min2.i + "^3 + " + min2.j + "^3");

            }
            if ((min.j * min.j * min.j) < N) {
                Pair pair = new Pair(min.i, min.j + 1);
                pq.insert(pair);
            }

        }
    }
}

