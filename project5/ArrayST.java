public class ArrayST<Key, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int N;

    // Create a symbol table with INIT_CAPACITY.
    public ArrayST() {
        keys = (Key[]) new Comparable[INIT_CAPACITY];
        values = (Value[]) new Object[INIT_CAPACITY];
    }

    // Create a symbol table with given capacity.
    public ArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    // Return the number of key-value pairs in the table.
    public int size() {
        return N;
    }

    // Return true if the table is empty and false otherwise.
    public boolean isEmpty() {
       return size() == 0;
    }

    // Return true if the table contains key and false otherwise.
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // Return the value associated with key, or null.
    public Value get(Key key) {
       for (int i = 0; i < N; i++)
         if (keys[i].equals(key)) return values[i];
      return null;
    }

    // Put the key-value pair into the table; remove key from table 
    // if value is null.
    public void put(Key key, Value value) {
        delete(key);
        if (N >= values.length) resize(2*N);
        keys[N] = key;
        values[N] = value;
        N++;
        
    }

    // Remove key (and its value) from table.
    public void delete(Key key) {
       for (int i = 0; i < N; i++) { 
               if (key.equals(keys[i])) {
               keys[i] = keys[N-1];
               values[i] = values[N-1];
               keys[N-1] = null;
               values[N-1] = null;
               N--;
               if (N > 0 && N == keys.length/4) resize(keys.length/2);
               return;
               }
        }
    }

    // Return all the keys in the table.
    public Iterable<Key> keys()  {
        Queue<Key> q = new Queue<Key>();
        for (int i = 0; i < N; i++) {
        q.enqueue(keys[i]); 
        }
        return q;
        
    }

    // Resize the internal arrays to capacity.
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            st.put(s, ++count);
        }
        for (String s : args) {
            st.delete(s);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
