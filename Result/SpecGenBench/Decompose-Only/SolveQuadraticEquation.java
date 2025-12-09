import java.lang.Math;

public class SolveQuadraticEquation {
    //@ requires a != 0;
    //@ requires -23170 <= a && a <= 23170;
    //@ requires -46340 <= b && b <= 46340;
    //@ requires -23170 <= c && c <= 23170;
    //@ requires b * b - 4 * a * c > 0;
    //@ ensures \result != null;
    //@ ensures \result.length == 2;
    //@ ensures \result[1] == 0.0;
    public static double[] solve(int a, int b, int c) {
        double[] res = new double[2];
        double delta = b * b - 4 * a * c;
        //@ assert delta > 0;
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