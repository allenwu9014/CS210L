public class BrutePointST<Value> implements PointST<Value> {
   private RedBlackBST<Point2D, Value> bst;

    // Construct an empty symbol table of points.
    public BrutePointST() {
        bst = new RedBlackBST<Point2D, Value>();
    }

    // Is the symbol table empty?
    public boolean isEmpty() { 
        return size() == 0;
    }

    // Number of points in the symbol table.
    public int size() {
        return bst.size();
    }

    // Associate the value val with point p.
    public void put(Point2D p, Value val) {
        if (p == null) { throw new IllegalArgumentException(); }
        bst.put(p, val);
    }

    // Value associated with point p.
    public Value get(Point2D p) {
     if (p == null) { throw new IllegalArgumentException(); }
     return bst.get(p);
    }

    // Does the symbol table contain the point p?
    public boolean contains(Point2D p) {
        if (p == null) { throw new IllegalArgumentException(); }
        return bst.contains(p);
    }

    // All points in the symbol table.
    public Iterable<Point2D> points() {
      return bst.keys();
    }

    // All points in the symbol table that are inside the rectangle rect.
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D pt: bst.keys()) {
        if (rect.contains(pt)) {
        q.enqueue(pt);
        }
        }
        return q;
    }

    // A nearest neighbor to point p; null if the symbol table is empty.
    public Point2D nearest(Point2D p) {
        if (isEmpty()) { throw new IllegalArgumentException(); }
        return nearest(p, 1).iterator().next();
    }

    // k points that are closest to point p.
    public Iterable<Point2D> nearest(Point2D p, int k) {
       if (isEmpty()) { throw new IllegalArgumentException(); }
       MinPQ<Point2D> pq = new MinPQ<Point2D>(size(), p.distanceToOrder());
       for (Point2D pt: bst.keys()) {
       if (pt.equals(p))
       continue;
       pq.insert(pt);
       }
     Queue<Point2D> q = new Queue<Point2D>();
     for (int i = 0; i < k; i++) {
     Point2D pt = pq.delMin();
     q.enqueue(pt);
     }
     return q;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        BrutePointST<Integer> st = new BrutePointST<Integer>();
        double qx = Double.parseDouble(args[0]);
        double qy = Double.parseDouble(args[1]);
        double rx1 = Double.parseDouble(args[2]);
        double rx2 = Double.parseDouble(args[3]);
        double ry1 = Double.parseDouble(args[4]);
        double ry2 = Double.parseDouble(args[5]);
        int k = Integer.parseInt(args[6]);
        Point2D query = new Point2D(qx, qy);
        RectHV rect = new RectHV(rx1, ry1, rx2, ry2);
        int i = 0;
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            st.put(p, i++);
        }
        StdOut.println("st.empty()? " + st.isEmpty());
        StdOut.println("st.size() = " + st.size());
        StdOut.println("First " + k + " values:");
        i = 0;
        for (Point2D p : st.points()) {
            StdOut.println("  " + st.get(p));
            if (i++ == k) {
                break;
            }
        }
        StdOut.println("st.contains(" + query + ")? " + st.contains(query));
        StdOut.println("st.range(" + rect + "):");
        for (Point2D p : st.range(rect)) {
            StdOut.println("  " + p);
        }
        StdOut.println("st.nearest(" + query + ") = " + st.nearest(query));
        StdOut.println("st.nearest(" + query + ", " + k + "):");
        for (Point2D p : st.nearest(query, k)) {
            StdOut.println("  " + p);
        }
    }
}
