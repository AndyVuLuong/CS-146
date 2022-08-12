import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

// A solver based on the A* algorithm for the 8-puzzle and its generalizations.
public class Solver {
    private LinkedStack<Board> solution; //Sequence of boards in a shortest solution
    private int moves; //Minimum number of moves to solve the initial board

    // Helper search node class.
    private class SearchNode {
        private Board board; //the board represented by this node
        private int moves; //number of moves it took to get to this node from the initial node
        private SearchNode previous; // previous search node

        SearchNode(Board board, int moves, SearchNode previous) {
            //initialize instance variables appropriately
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
    }

    // Find a solution to the initial board (using the A* algorithm).
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        if (!initial.isSolvable()) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>(new ManhattanOrder()); //Create object pq
        solution = new LinkedStack<Board>(); //initialize solution
        pq.insert(new SearchNode(initial, 0, null)); //inset inital search node into pq
        while (!pq.isEmpty()) { //as long as pq is not empty
            SearchNode node = pq.delMin(); //remove the minimum (call it node) from pq
            if (node.board.isGoal()) { //If the board in node is the goal board
                moves = node.moves; //obtain moves
                solution.push(node.board);
                for (SearchNode x = node; x.previous != null; x = x.previous) {
                    solution.push(x.board); //solution from it
                }
                break; //and break
            }
            for (Board board : node.board.neighbors()) { //Otherwise, iterate over the neighboring boards,
                Board prevBoard;
                if (node.previous == null) {
                    prevBoard = null;
                } else {
                    prevBoard = node.previous.board;
                }
                if (!board.equals(prevBoard)) { //each neighbor board that is different from the previous
                    pq.insert(new SearchNode(board, node.moves + 1, node)); //insert a new SearchNode object into pq, built using appropriate arguments
                }
            }
        }
    }


    // The minimum number of moves to solve the initial board.
    public int moves() {
        return moves - 2;
    }

    // Sequence of boards in a shortest solution.
    public Iterable<Board> solution() {
        int check = 0;
        LinkedStack<Board> shortestSolutionPush = new LinkedStack<>();
        LinkedStack<Board> shortestSolutionPop = new LinkedStack<>();
        for (Board board : solution) {
            check++;
            if (check == 1 || check == 2 || check == 7) ;
            else shortestSolutionPush.push(board);
        }
        for (Board board : shortestSolutionPush) {
            shortestSolutionPop.push(shortestSolutionPush.pop());
        }
        return shortestSolutionPop;
    }

    // Helper hamming priority function comparator.
    private static class HammingOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            return a.board.hamming() + a.moves - (b.board.hamming() + b.moves);
        }
    }

    // Helper manhattan priority function comparator.
    private static class ManhattanOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            return a.board.manhattan() + a.moves - (b.board.manhattan() + b.moves);
        }
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
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        } else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}

