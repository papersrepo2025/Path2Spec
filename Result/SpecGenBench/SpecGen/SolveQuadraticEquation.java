import java.lang.Math;

public class SolveQuadraticEquation {
    //@ requires (2 * a) != 0;
    //@ ensures \result != null && \fresh(\result) && \result.length == 2;
    //@ ensures (b*b - 4*a*c) >= 0 ==> (\result[0] == ((-((double)b) - Math.sqrt((double)(b*b - 4*a*c))) / (double)(2*a)) && \result[1] == 0.0);
    //@ ensures (b*b - 4*a*c) < 0 ==> (\result[0] == (double)((-b) / (2*a)) && \result[1] == (Math.sqrt(-((double)(b*b - 4*a*c))) / (double)(2*a)));
    public static double[] solve(int a, int b, int c) {
        double[] res = new double[2];
        double delta = b * b - 4 * a * c;
        if(delta >= 0) {
            res[0] = (-b - Math.sqrt(delta)) / (2 * a);
            res[1] = 0;
        }
        else {
            res[0] = -b / (2*a);
            res[1] = Math.sqrt(-delta) / (2*a);
        }
        return res;
    } 
}