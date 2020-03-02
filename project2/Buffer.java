// A data type representing a text editor buffer.
public class Buffer {
    private Stack<Character> left;  // chars left of cursor
    private Stack<Character> right; // chars right of cursor
   private int countsize = 0;
    // Create an empty buffer.
    public Buffer() {
   left = new Stack<Character>();
   right = new Stack<Character>();
    }

    // Insert c at the cursor position.
    public void insert(char c) {
       left.push(c);
       countsize++;
      
    }
    
    // Delete and return the character at the cursor.
    public char delete() {
        right.pop();
        countsize--;
        return left.peek();
    }

    // Move the cursor k positions to the left.
    public void left(int k) {
    for (int i = 0; i < k; i++) {
       if (!left.isEmpty()) {
       right.push(left.peek());
        left.pop();
        }
        else break;
        }
    }

    // Move the cursor k positions to the right.
    public void right(int k) {
       for (int i = 0; i < k; i++) {
        if (!right.isEmpty()) {
        left.push(right.peek());
        right.pop();
        }
        else break;
        }
    }

    // Return the number of characters in the buffer.
    public int size() {
       return countsize;
    }

    // Return a string representation of the buffer with
    // a "|" character (not part of the buffer) at the
    // cursor position.
    public String toString() {
        StringBuilder sb = new StringBuilder();
       Stack<Character> s = new Stack<Character>();
        // Push chars from left into a temporary stack.
        for (char a :left) {
        s.push(a);
            }
        // Append chars from temporary stack to sb.
      for (char a : s) {
        sb.append(a);
        }
           
        // Append "|" to sb.
        sb.append("|");
        
        // Append chars from right to sb.
        for (char a : right) {
        sb.append(a);
        }
            
        // Return the string from sb.
       return sb.toString();
    }
    
    // Test client (DO NOT EDIT).
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        String s = "There is grandeur in this view of life, "
            + "with its several powers, having been originally "
            + "breathed into a few forms or into one; and that, "
            + "whilst this planet has gone cycling on according "
            + "to the fixed law of gravity, from so simple a "
            + "beginning endless forms most beautiful and most " 
            + "wonderful have been, and are being, evolved. ~ " 
            + "Charles Darwin, The Origin of Species";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
              
        buf.left(buf.size());
        
       
        buf.right(97);
        s = "by the Creator ";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.right(228);
        buf.delete();
        buf.insert('-');
        buf.insert('-');
        buf.left(342);
        StdOut.println(buf);
    }
}
