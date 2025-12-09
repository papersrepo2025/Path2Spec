
public class ReLUSeq {
    //@ requires x >= 0;
    //@ ensures \result == x;
    public static double computeReLU(double x) {
        return ((x >= 0) ? x : 0);
    }
}
