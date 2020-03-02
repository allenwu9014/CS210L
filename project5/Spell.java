public class Spell {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] lines = in.readAllLines();
        in.close();
        int counter = 0;
        // Create an ArrayST<String, String> object called st. 
        ArrayST<String, String> st = new ArrayST<String, String>();
        

        // For each line in lines, split it into two tokens using
        // "," as delimiter; insert into st the key-value pair
        // (token 1, token 2).
        for (int i = 0; i < lines.length; i++) {
        String line = lines[i];
        String[] tokens = line.split(",");
        String token1 = tokens[0];
        String token2 = tokens[1];
        st.put(token1, token2);
        }
            
        // Read from standard input one line at a time; increment
        // a line number counter; split the line into words using
        // "\\b" as the delimiter; for each word in words, if it
        // exists in st, write the (misspelled) word, its line number, and
        // corresponding value (correct spelling) from st.
        
        while (!StdIn.isEmpty()) {
        ++counter;
        String s = StdIn.readLine();
        String[] word = s.split("\\b");
        for (int i = 0; i < word.length; i++) {
        if (st.contains(word[i])) { StdOut.println(word[i] 
        + ":" + counter + " -> " + st.get(word[i])); }
        }
        
        }
    }
}
