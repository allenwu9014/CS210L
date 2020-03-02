public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap
    // and false otherwise.
    private static boolean maxOrderedHeap(Comparable[] a) {
        int N = a.length;

        // For each node 1 <= i <= N / 2, if i is less than
        // either of its children, return false, meaning a[]
        // does not represent a maximum-ordered heap.
        // Otherwise, return true.
       for (int i = 1; i <= ((N-2)/2); i++) {
       if (less(a[i], a[i*2 + 1]) && (2*i + 1) < N) return false;
        if (i == ((N-2)/2) && N % 2 == 0) continue;      
       if (less(a[i], a[i*2 + 2]) && (2*i + 2) < N) return false;
      
       }
       return true; 
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}
