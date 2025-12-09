
public class ReLU {
    //@ requires x >= 0;
    //@ ensures \result == x;
    //@ also
    //@ requires x < 0;
    //@ ensures \result == 0.0;
    public static double computeReLU(double x) {
        if(x >= 0) {
            return x;
        }
        return 0.0;
    }
}
