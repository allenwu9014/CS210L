
public class Percolation {
private boolean[][] open;
private int openSites = 0;
private int N;
private WeightedQuickUnionUF uf;

    public Percolation(int N) {
    this.N = N;
    open = new boolean[N][N];
    for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++)
    { open[i][j] = false;   }
    }
    uf = new WeightedQuickUnionUF(N * N + 2);
    for (int i = 1; i <= N; i++) {
    uf.union(0, i);
    }
  
    }
    public void open(int i, int j) {
          open[i][j] = true;
         openSites++;
         if ((j-1) >= 0) {
        if (isOpen(i, j-1)) {
        uf.union(encode(i, j), encode(i, j-1));
        }
        }
        if ((j+1) < N) {
         if (isOpen(i, j+1)) {
        uf.union(encode(i, j), encode(i, j+1));
        } }
         if ((i-1) >= 0) {
         if (isOpen(i-1, j)) {
        uf.union(encode(i, j), encode(i-1, j));
        } }
         if ((i+1) < N) {
         if (isOpen(i+1, j)) {
        uf.union(encode(i, j), encode(i + 1, j));
        }
        }
    }
     public boolean isOpen(int i, int j) {
    if (open[i][j]) {
    return true;
   }
    return false;
    }
    public boolean isFull(int i, int j) {
    if (uf.connected(0, encode(i, j)) && isOpen(i, j))
    { return true; }
    return false;
                     }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
    for (int j = 0; j < N; j++) {
    if (uf.find(encode(N-1, j)) == uf.find(0)) {
        uf.union(encode(N-1, j), N * N + 1); }
    }
    if (uf.connected(0, N * N + 1)) { return true; }
     return false;
    
    }

    private int encode(int i, int j) {
        return N * i + j + 1;
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
        }
        else {
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
