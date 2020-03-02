public class PrimeCounter
{
 private static boolean isPrime(int x)
 {
 boolean j = true;
 if (x == 2 || x == 3) 
 { j = true;
 }
 else {
 for (int i = 2; i <= Math.sqrt(x);)
 {
 if (x % i != 0)
 {
 i++;
 }
 else 
 { j = false;
  break;
 }
 }
     }
      return j;
 }
 
 private static int primes(int N)
 {
 int pc = 0;
 for (int i = 2; i <= N; i++) {
 if (isPrime(i)) {
 pc++;
 }
 }
 return pc;
 }
 public static void main(String[] args) { 
    int N = Integer.parseInt(args[0]);
    StdOut.println(primes(N));
 }
}
