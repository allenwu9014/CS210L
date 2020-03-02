public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        ResizingArrayRandomQueue<String> q = 
            new ResizingArrayRandomQueue<String>();
             while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
        for (int i = 1; i <= k; i++)
        {
        StdOut.println(q.dequeue());
        }
    }
}
