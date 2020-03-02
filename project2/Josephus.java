public class Josephus {
    public static void main(String[] args) {
        // Get N and M from command line as ints.
       
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
       
        // Create a queue q and enqueue integers
        // 1, 2, ... N.
        Queue<Integer> q = new Queue<Integer>();
        
        for (int i = 1; i <= N; i++) {
        q.enqueue(i);
        }
        int i = 0;
        // As long as q is not empty: increment i;
        // dequeue an element pos; if M divides i,
        // write pos, otherwise enqueue pos.
        while (!q.isEmpty()) {
       i++;
       int pos = q.dequeue();
        
        if (i % M == 0) {
         
        StdOut.println(pos);
        }
        else {
        q.enqueue(pos);
        }
    }
}
}
