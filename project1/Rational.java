public class Rational {
private long x;
private long y;
public Rational(long x) {
this.x = x;
this.y = 1;
}
public Rational(long x, long y) {
this.x = x / gcd(x, y);
this.y = y / gcd(x, y);
}
public Rational add(Rational that) {
this.x = this.x * that.y + that.x * this.y;
this.y = this.y * that.y;
Rational sum = new Rational(this.x, this.y);
return sum;
}
public Rational multiply(Rational that) {
this.x = this.x * that.x;
this.y = this.y * that.y;
Rational multi = new Rational(this.x, this.y);
return multi;
}
public boolean equals(Rational that) {
 if ((this.x == that.x) && (this.y == that.y)) {
 return true;
 }  return false;
 }
public String toString() {
long a = x, b = y;
if (a == 0 || b == 1) {
return a + "";
}
if (b < 0) {
a *= -1;
b *= -1;
}
return a+"/"+b;
}
private static long gcd(long p, long q) {
return q == 0 ? p : gcd(q, p % q);
}
public static void main(String[] args) {
int n = Integer.parseInt(args[0]);
Rational total = new Rational(0);
Rational term = new Rational(1);
for (int i = 1; i <= n; i++) {
total = total.add(term);
term = term.multiply(new Rational(1, 2));
}
Rational expected = new Rational((long) Math.pow(2, n)-1, 
                                (long) Math.pow(2, n-1));
    StdOut.println(total.equals(expected));
    
}
}
