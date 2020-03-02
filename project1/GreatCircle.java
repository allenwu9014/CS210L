public class GreatCircle
{
    public static void main(String[] args)
    {
    double x1 = Double.parseDouble(args[0]);
    x1 = Math.toRadians(x1);
    double y1 = Double.parseDouble(args[1]);
    double x2 = Double.parseDouble(args[2]);
    x2 = Math.toRadians(x2);
    double y2 = Double.parseDouble(args[3]);
    double c = Math.toRadians(y1-y2);
    double e = Math.sin(x1)*Math.sin(x2)+Math.cos(x1)*Math.cos(x2)*Math.cos(c);
    double s = Math.acos(e);
    double d = 111* s;
    d = Math.toDegrees(d);
       StdOut.println(d);
    }
}
