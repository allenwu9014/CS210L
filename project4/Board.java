// Models a board in the 8-puzzle game or its generalization.

public class Board {
   private int[][] tiles;
   private int N;
    private int hamming;
    private int manhattan;
    
    // Construct a board from an N-by-N array of tiles, where 
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank 
    // square.
    public Board(int[][] tiles) {
        N = tiles[0].length;
        this.tiles = new int[N][N];
        this.tiles = tiles;
        for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
        if (tiles[i][j] == 0) continue;
         if (tiles[i][j] != i * N + j + 1) {
         hamming++;
       manhattan += Math.abs((tiles[i][j] - 1) / N - i) 
       + Math.abs((tiles[i][j] - 1) % N - j);
          }
        }
        
        }
    

    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }
    
    // Size of this board.
    public int size() {
        return N;
    }

    // Number of tiles out of place.
    public int hamming() {
        return hamming;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {
        return manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
      return blankPos() == N * N && inversions() == 0;
          
    }
    

    // Is this board solvable?
   public boolean isSolvable() {
   
        if (N % 2 != 0) {
        if (inversions() % 2 == 0) {
        return true;
       }
       return false;
       }
       else {
       if ((inversions() + (blankPos() - 1) / N) % 2 == 0)
      { return false;
      }
       return true;
       }
    }

    // Does this board equal that?
    public boolean equals(Board that) {
        return this.tiles == that.tiles;
    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {
    LinkedQueue<Board> q = new LinkedQueue<Board>();
     
     int a = (blankPos() - 1) / N;
     int b = (blankPos() - a * N) - 1; 
    
     if (a > 0) {
      int[][] item = cloneTiles();
      int t = item[a-1][b];
      item[a][b] = t;
      item[a-1][b] = 0;
     
      Board n = new Board(item);
      
      
      q.enqueue(n);

      
      }
      if (b < N - 1) {
      int[][] item = cloneTiles();
      int t = item[a][b+1];
      item[a][b] = t;
      item[a][b+1] = 0;
      
      Board n = new Board(item);
       
      q.enqueue(n);
      
      }
      if (a < N - 1) {
      int[][] item = cloneTiles();
      int t = item[a+1][b];
      item[a][b] = t;
      item[a+1][b] = 0;
      Board n = new Board(item);
      q.enqueue(n);
     
      }
     
      
    
     if (b > 0) {
     int[][] item = cloneTiles();
      int t = item[a][b-1];
      item[a][b] = t;
      item[a][b-1] = 0;
      Board n = new Board(item);
      q.enqueue(n); }
       
       return q; }
    
      

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

    // Helper method that returns the position (in row-major order) of the 
    // blank (zero) tile.
    private int blankPos() {
    int t = 0;
       for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
         if (tiles[i][j] == 0) {
         t = N * i + j + 1;
         }
       return t;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int inver = 0;
        int[] a = new int[N*N];
        int k = 0;
        for (int i = 0; i < N; i++) 
        for (int j = 0; j < N; j++) {
             a[k] = tiles[i][j]; 
             k++;  
        }
        for (int i = 0; i < N * N; i++)
         for (int j = i + 1; j < N * N; j++)
         { if (a[i] == 0 || a[j] == 0) continue;
         if (a[i] > a[j] && i < j) inver++;
         
         }
    
    return inver;
    }

    // Helper method that clones the tiles[][] array in this board and 
    // returns it.
    private int[][] cloneTiles() {
        int[][] ite = new int[N][N];
        for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
               ite[i][j] = tiles[i][j];
        return ite;
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
