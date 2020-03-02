public class KdTreePointST<Value> implements PointST<Value> {
    private Node root;
    private int N;
    
    // 2d-tree (generalization of a BST in 2d) representation.
    private class Node {
        private Point2D p;   // the point
        private Value val;   // the symbol table maps the point to this value
        private RectHV rect; // the axis-aligned rectangle corresponding to 
                             // this node
        private Node lb;     // the left/bottom subtree
        private Node rt;     // the right/top subtree

        // Construct a node given the point, the associated value, and the 
        // axis-aligned rectangle corresponding to the node.
        Node(Point2D p, Value val, RectHV rect) {
            this.p = p;
            this.val = val;
            this.rect = rect;
        }
    }

    // Construct an empty symbol table of points.
    public KdTreePointST() {
     this.root = null;
     this.N = 0;
    }

    // Is the symbol table empty?
    public boolean isEmpty() { 
        return size() == 0;
    }

    // Number of points in the symbol table.
    public int size() {
        return N;
    }

    // Associate the value val with point p.
    public void put(Point2D p, Value val) {
     if (p == null) { throw new IllegalArgumentException(); }
        RectHV rectangle = new RectHV(Double.NEGATIVE_INFINITY, 
        Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
        Double.POSITIVE_INFINITY); 
        // [-infinity, +infinity]X[-infinity, +infinity]
        root = put(root, p, val, rectangle, true);
    }

    // Helper for put(Point2D p, Value val).
    private Node put(Node x, Point2D p, Value val, RectHV rect, boolean lr) {
       if (x == null) { 
       N++; 
        return new Node(p, val, rect);   
           
       }
       if (x.p.equals(p)) {  
       x.val = val;
       }    //insert x depending on 4 situations;
       else if ((lr && p.x() < x.p.x()) || (!lr && p.y() < x.p.y())) {  
        if (lr) {
        RectHV sr = 
        new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
        x.lb = put(x.lb, p, val, sr, !lr); }
           else {
          RectHV sr = 
         new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
         x.lb = put(x.lb, p, val, sr, !lr); }
          }
        else  {
        if (lr) {
        RectHV sr = 
        new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
         x.rt = put(x.rt, p, val, sr, !lr);
        }
        else {
       RectHV sr = 
         new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
         x.rt = put(x.rt, p, val, sr, !lr);
        } 
        }
    return x;
       
    }

    // Value associated with point p.
    public Value get(Point2D p) {
     if (p == null) { throw new IllegalArgumentException(); }
        return get(root, p, true);
    }

    // Helper for get(Point2D p).
    private Value get(Node x, Point2D p, boolean lr) {
       if (x == null) {
       return null;
       }
       if (x.p.equals(p)) {
       return x.val;
       } 
       else if ((lr && p.x() < x.p.x()) || (!lr && p.y() < x.p.y())) {
        return get(x.lb, p, !lr);
        } 
        return get(x.rt, p, !lr);
        
    }

    // Does the symbol table contain the point p?
    public boolean contains(Point2D p) {
     if (p == null) { throw new IllegalArgumentException(); }
        return get(p) != null;
    }

    // All points in the symbol table, in level order.
    public Iterable<Point2D> points() {
        Queue<Node> q1 = new Queue<Node>(); 
        q1.enqueue(root);
        Queue<Point2D> q2 = new Queue<Point2D>(); //get all n.p out and insert 
        while (!q1.isEmpty()) {
        Node n = q1.dequeue();
          if (n.lb != null) {
           q1.enqueue(n.lb);
          } 
          if (n.rt != null) {
          q1.enqueue(n.rt);
          }
         q2.enqueue(n.p);  
        }                     // an alternative mothed is using a recursion to
                              // look for left and right sides separately
                            // but because we can not add any function
                            // it can not be used 
        return q2;
    }

    // All points in the symbol table that are inside the rectangle rect.
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> q = new Queue<Point2D>();
        range(root, rect, q);
        return q;
    }

    // Helper for public range(RectHV rect).
    private void range(Node x, RectHV rect, Queue<Point2D> q) {
        if (x == null) return;
        if  (rect.contains(x.p)) {
        q.enqueue(x.p);
        }
        range(x.lb, rect, q);
        range(x.rt, rect, q);
    }

    // A nearest neighbor to point p; null if the symbol table is empty.
    public Point2D nearest(Point2D p) {
      if (isEmpty()) { throw new IllegalArgumentException(); }
        return nearest(root, p, null, Double.POSITIVE_INFINITY, true);
    }
    
    // Helper for public nearest(Point2D p).
    private Point2D nearest(Node x, Point2D p, Point2D nearest, 
                            double nearestDistance, boolean lr) {
        if (x == null) return nearest;                   
        if (!x.p.equals(p) && x.p.distanceSquaredTo(p) < nearestDistance) {
        nearest = x.p;
        nearestDistance = x.p.distanceSquaredTo(p);
        }
        
        if ((lr && p.x() >= x.p.x()) || (!lr && p.y() >= x.p.y())) {
        nearest = nearest(x.rt, p, nearest, nearestDistance, !lr);
        return nearest(x.lb, p, nearest, p.distanceSquaredTo(nearest), !lr);
        }
        else {
        nearest = nearest(x.lb, p, nearest, nearestDistance, !lr);
        return nearest(x.rt, p, nearest, p.distanceSquaredTo(nearest), !lr);
        }
    }

    // k points that are closest to point p.
    public Iterable<Point2D> nearest(Point2D p, int k) {
     if (isEmpty()) { throw new IllegalArgumentException(); }
       MaxPQ<Point2D> pq = new MaxPQ<Point2D>(p.distanceToOrder());
       nearest(root, p, k, pq, true);
       return pq;
    }

    // Helper for public nearest(Point2D p, int k).
    private void nearest(Node x, Point2D p, int k, MaxPQ<Point2D> pq, 
                         boolean lr) {
        if (x == null || pq.size() >= k 
        && pq.max().distanceSquaredTo(p) 
        < x.rect.distanceSquaredTo(p)) return;
        if (!x.p.equals(p)) pq.insert(x.p);
        if (pq.size() > k) pq.delMax();
        
        boolean l;
        if (lr && p.x() < x.p.x() || !lr && p.y() < x.p.y()) {
         nearest(x.lb, p, k, pq, !lr);
         l = true;
         } else {
        nearest(x.rt, p, k, pq, !lr);
        l = false;
        }
        if (l) nearest(x.rt, p, k, pq, !lr);
        else nearest(x.lb, p, k, pq, !lr);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        KdTreePointST<Integer> st = new KdTreePointST<Integer>();
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
