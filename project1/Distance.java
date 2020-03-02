public class Distance {
  private static double distance(double[] x, double[] y) {
  double d = 0;
  for (int i = 0; i < (x.length); i++) {
    d = d + (y[i]-x[i])*(y[i]-x[i]);
  }
    return Math.sqrt(d);
  }
  
  public static void main(String[] args) {
    double[] x = StdArrayIO.readDouble1D();
  double[] y = StdArrayIO.readDouble1D();
  StdOut.println(distance(x, y));
  }
  
}
