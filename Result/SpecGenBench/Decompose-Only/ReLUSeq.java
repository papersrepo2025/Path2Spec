
public class ReLUSeq {
    //@ requires x >= 0;
    //@ ensures \result == x;
    //@ also
    //@ requires x < 0;
    //@ ensures \result == 0;
    //@ also
    //@ requires !(x >= 0) && !(x < 0);
    //@ ensures \result == 0;
    public static double computeReLU(double x) {
        return ((x >= 0) ? x : 0);
    }
}