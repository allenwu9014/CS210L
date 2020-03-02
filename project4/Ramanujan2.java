public class Ramanujan2 {
    // A data type that encapsulates a pair of numbers (i, j) 
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
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
        MinPQ<Pair> pq = new MinPQ<Pair>();
        int i = 0;
        int count = 0;
        while (i < Math.cbrt(N)) {
        Pair pair = new Pair(i, i+1);
        pq.insert(pair);
        i++;
     }
     
        while (pq.size() > 1) {
       
        Pair prep = pq.delMin();
        if (pq.min().compareTo(prep) == 0 && pq.min().sumOfCubes <= N) {
        StdOut.println(pq.min().sumOfCubes + " = " + prep.i + "^3 + "
         + prep.j + "^3 = " + pq.min().i + "^3 + " + pq.min().j + "^3");
        
         } 
        if (pq.min().j < Math.cbrt(N)) {
       
        pq.insert(new Pair(pq.min().i, pq.min().j + 1));
        }
       }
       
    }
}
