import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
//importing WeightedQuickUnionUF allows methods like find and union to be used

// Models an N-by-N percolation system.
public class Percolation {
    private int N; //for constructor and how big the grid is
    private int numOpenSites; //for method numberOfOpenSites
    private WeightedQuickUnionUF unionFindO;
    private WeightedQuickUnionUF unionFind;
    private boolean[][] grid;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        this.N = N; //Instantiate
        if (N <= 0) {
            throw new IllegalArgumentException("Value too small");
        }

        unionFindO = new WeightedQuickUnionUF(N * N + 1);
        unionFind = new WeightedQuickUnionUF(N * N + 2); //Original always bigger than open
        grid = new boolean[N][N]; //creates the grid of false or true of size N

        //Helps to find open sites and if it percolates
        for (int j = 0; j <= N - 1; j++) {
            unionFind.union(0, encode(0, j)); //first row to source
            unionFind.union((N * N) + 1, encode((N - 1), j)); //last row does down
        }
    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) throws IllegalArgumentException{
        if (!isOpen(row, col)) grid[row][col] = true;
        numOpenSites++;

        //down
        if ((col - 1) >= 0 && (col - 1) < N && isOpen(row, col - 1)) {
            unionFind.union(encode(row, col - 1), encode(row, col));
            unionFindO.union(encode(row, col - 1), encode(row, col));
        }

        //up
        if ((col + 1) >= 0 && (col + 1) < N && isOpen(row, col + 1)) {
            unionFind.union(encode(row, col + 1), encode(row, col));
            unionFindO.union(encode(row, col + 1), encode(row, col));
        }

        //left
        if ((row - 1) >= 0 && (row - 1) < N && isOpen(row - 1, col)) {
            unionFind.union(encode(row - 1, col), encode(row, col));
            unionFindO.union(encode(row - 1, col), encode(row, col));
        }

        //right
        if ((row + 1) >= 0 && (row + 1) < N && isOpen(row + 1, col)) {
            unionFind.union(encode(row + 1, col), encode(row, col));
            unionFindO.union(encode(row + 1, col), encode(row, col));
        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) throws IllegalArgumentException{
        return grid[row][col]; //Checks if row or col are within range
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        return (unionFind.find(encode(row, col)) == unionFind.find(0)) && isOpen(row, col)
                && (unionFindO.find(encode((N - 1), col)) == unionFindO.find(0));
        //returns if unionFind and unionFindO is connected to 0 and open
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return numOpenSites; //The method open will determine how many
    }

    // Does the system percolate?
    public boolean percolates() {
        return unionFind.find(0) == unionFind.find((N * N) + 1); //is there a open site in the bottom row?
    }

    // An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {
        return (N * row) + col + 1; //Each site has a unique ID based on its location
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
