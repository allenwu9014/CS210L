public class Transpose {
 private static double[][] transpose(double[][] x) {
 double[][] t = new double[x.length][x[0].length];
 for (int m = 0; m < x.length; m++) {
  for (int n = 0; n < x[0].length; n++) {
  t[n][m] = x[m][n];
  }
 }
 return t;
 }
 
 public static void main(String[] args) {
 double[][] x = StdArrayIO.readDouble2D();
 StdArrayIO.print(transpose(x));
 }
 }
