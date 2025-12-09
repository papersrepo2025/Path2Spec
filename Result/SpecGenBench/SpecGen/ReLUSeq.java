public class ReLUSeq {
    //@ ensures \result == ((x >= 0) ? x : 0);
    public static /*@ pure @*/ double computeReLU(double x) {
        return ((x >= 0) ? x : 0);
    }
}