// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    private int T;
   private double[] p;

    // Performs T independent experiments (Monte Carlo simulations) on an 
    // N-by-N grid.
    public PercolationStats(int N, int T) {
    this.T = T;
   p = new double[T];
    int x = 0;
    int i = 0;
    int j = 0;
        for (int a = 1; a <= T; a++) {
       Percolation test = new Percolation(N);
     while (!test.percolates()) {
     i = StdRandom.uniform(0, N);
      j = StdRandom.uniform(0, N);
     if (!test.isOpen(i, j)) {
     test.open(i, j);
     }
       }
     p[x] = (double) (test.numberOfOpenSites()) / (N * N);
     
     x++; }
 
    }
    
    // Returns sample mean of percolation threshold.
    public double mean() {
    double sum = 0;
    for (int i = 0; i < T; i++) {
    sum += p[i];
        }
    return sum/T;
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
    double ave = mean();
     double sum = 0;
    for (int i = 0; i < T; i++) {
    sum += (p[i] - ave) * (p[i] - ave);
    }
    return Math.sqrt(sum / (T - 1));
       
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
      return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
         return mean() + (1.96 * stddev() / Math.sqrt(T));
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
