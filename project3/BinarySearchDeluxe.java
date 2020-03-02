import java.util.Arrays;
import java.util.Comparator;


// Implements binary search for clients that may want to know the index of 
// either the first or last key in a (sorted) collection of keys.
public class BinarySearchDeluxe {
    // The index of the first key in a[] that equals the search key, 
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c) {
      if (a == null || key == null || c == null)
      throw new NullPointerException();
       int l = 0;
       int h = a.length;
       int mid = (l + a.length) / 2;
       int index = -1;
       int i = -1;
      while (l < h)
       { if (c.compare(a[mid], key) < 0) { 
       l = mid + 1; 
       mid = (h - mid) / 2 + l;
       }
       else if (c.compare(a[mid], key) > 0) {
       h = mid - 1;
       mid = (mid - l) / 2 + l;
       }
       else {
       i = mid;
      
       break;
      } 
      }
       while (c.compare(a[i], key) == 0) {
        
        index = i;
        i--;
      
       }
                     
       return index;
    }

    // The index of the last key in a[] that equals the search key, 
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
      if (a == null || key == null || c == null)
      throw new NullPointerException();
       int l = 0;
       int h = a.length;
       int mid = (l + a.length) / 2;
       int index = -1;
       int i = -1;
      while (l < h)
       { if (c.compare(a[mid], key) < 0) { 
       l = mid + 1; 
       mid = (h - mid) / 2 + l;
       }
       else if (c.compare(a[mid], key) > 0) {
       h = mid - 1;
       mid = (mid - l) / 2 + l;
       }
       else {
       i = mid;
       break;
      } 
      }
        while (c.compare(a[i], key) == 0) {
        index = i;
        i++;
         
       }
      return index;
    }
    

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query.trim(), weight); 
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println(count);
    }
}
