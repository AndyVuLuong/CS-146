import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    //mean variable has to be inside its method
    double stddev; //stddev method
    double confidenceLow; //confidenceLow method
    double confidenceHigh; //confidenceHigh method
    double[] arr; //total experiments
    int T; //Constructor and independent experiments
    Percolation percolation; //Constructor based on percolation class

    // Perform T independent experiments (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
    	if(N <= 0 || T <= 0) throw new IllegalArgumentException("Value not valid");
        arr = new double[T]; //arr of size T
        this.T = T; //Instantiate
        int counter = 0; //for loop

        //Creates percolation and random
        for (int i = 0; i < T; i++) {
            counter = 0;
            int row, col = 0; //int counter, row, col
            percolation = new Percolation(N); //Creats percolation objects
            while (!percolation.percolates()) { //is bottom open?
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);
                if (!percolation.isOpen(row, col)) {
                    counter++;
                    percolation.open(row, col);
                }
            }
            arr[i] = counter / (double) (N * N);
        }
    }

    // Sample mean of percolation threshold.
    public double mean() {
        double mean = 0;
        for (int i = 0; i < arr.length; i++) mean += arr[i];
        return mean / T;
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        stddev = StdStats.stddev(arr); //StdStats can handle stddev
        return stddev;
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        confidenceLow = (mean() - ((1.96 * stddev()) / Math.sqrt(T)));
        return confidenceLow;
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        confidenceHigh = (mean() + ((1.96 * stddev()) / Math.sqrt(T)));
        return confidenceHigh;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
