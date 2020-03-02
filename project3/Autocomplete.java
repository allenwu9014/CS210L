import java.util.Arrays;
import java.util.Comparator;

// A data type that provides autocomplete functionality for a given set of 
// string and weights, using Term and BinarySearchDeluxe. 
public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
     if (terms == null)
      throw new NullPointerException();
       this.terms = terms;
       Arrays.sort(terms);
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
    if (prefix == null)
      throw new NullPointerException();
          Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int n = i == -1 && j == -1 ? 0 : j - i + 1;
        Term[] matches = new Term[n];
      
       for (int k = 0; i <= j; i++) {
       matches[k] = terms[i];
       k++;
       }
       Arrays.sort(matches, Term.byReverseWeightOrder());
       return matches;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
     if (prefix == null)
      throw new NullPointerException();
          Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int n = i == -1 && j == -1 ? 0 : j - i + 1;
        return n;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query.trim(), weight); 
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
