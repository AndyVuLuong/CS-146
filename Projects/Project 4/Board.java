import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    int[][] tiles; //Tiles in the board, int[][] tiles
    int N; //Board size, int N
    int hamming; //Hamming distance to the goal board, int hamming
    int manhattan; //Manhattan distance to the goal board, int manhattan

    // Helper method that returns the position (in row-major order) of the
    // blank (zero) tile.
    private int blankPos() {
        int blankPos = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) blankPos = 1 + i * N + j;
            }
        }
        return blankPos;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int inversions = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        if ((k * N + 1) > (i * N + j) && tiles[i][j] != 0
                                && tiles[k][l] != 0 && tiles[i][j] > tiles[k][l]) {
                            inversions++;
                        }
                    }
                }
            }
        }
        return inversions;
    }

    // Helper method that clones the tiles[][] array in this board and
    // returns it.
    private int[][] cloneTiles() {
        int temp[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = tiles[i][j];
            }
        }
        return temp;
    }

    // Construct a board from an N-by-N array of tiles, where
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank
    // square.
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.N = tiles.length;
    }

    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    // Size of this board.
    public int size() {
        return N * N;
    }

    // Number of tiles out of place.
    public int hamming() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] == 0)
                    continue;
                if (this.tiles[i][j] != i * N + j + 1)
                    this.hamming++;
            }
        }
        return hamming;
    }

    public int manhattan() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != i * N + j + 1 && tiles[i][j] != 0) {
                    int tilei = (tiles[i][j] - 1) / N;
                    int tilej = (tiles[i][j] - 1) - (tilei * N);
                    this.manhattan += Math.abs(j - tilej) + Math.abs(i - tilei);
                }
            }
        }
        return manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        if (hamming() == 0) return true;
        else return false;
    }

    // Is this board solvable?
    public boolean isSolvable() {
        if (inversions() % 2 == 0) return true;
        else return false;
    }

    // Does this board equal that?
    public boolean equals(Board that) {
        if (this == that) return true;
        else return false;
    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {
        LinkedQueue<Board> q = new LinkedQueue<Board>();
        int[][] neighbor;
        int temp;
        int i = (blankPos() - 1) / N;
        int j = (blankPos() - 1) % N;

        //North
        if (i - 1 >= 0 && i - 1 < N - 1) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i - 1][j];
            neighbor[i - 1][j] = temp;
            Board board = new Board(neighbor);
            q.enqueue(board);
        }

        //South
        if (i + 1 > 0 && i + 1 < N) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i + 1][j];
            neighbor[i + 1][j] = temp;
            Board board = new Board(neighbor);
            q.enqueue(board);
        }

        //East
        if (j + 1 > 0 && j + 1 < N) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i][j + 1];
            neighbor[i][j + 1] = temp;
            Board board = new Board(neighbor);
            q.enqueue(board);
        }

        //West
        if (j - 1 >= 0 && j - 1 < N - 1) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i][j - 1];
            neighbor[i][j - 1] = temp;
            Board board = new Board(neighbor);
            q.enqueue(board);
        }
        return q;
    }

    // String representation of this board.
    public String toString() {
        String s = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += String.format("%2d", tiles[i][j]);
                if (j < N - 1) {
                    s += " ";
                }
            }
            if (i < N - 1) {
                s += "\n";
            }
        }
        return s;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        StdOut.println(board.isSolvable());
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
        }
    }
}

